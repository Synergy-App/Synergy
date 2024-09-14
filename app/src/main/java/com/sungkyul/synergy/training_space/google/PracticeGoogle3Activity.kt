package com.sungkyul.synergy.training_space.google

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeGoogle3Binding
import com.sungkyul.synergy.training_space.call.problem.ExamCallProblem2Activity

class PracticeGoogle3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeGoogle3Binding
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 300000
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가

    private var selectedGender: String = ""
    private var selectedMonth: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeGoogle3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        remainingTimeInMillis = intent.getLongExtra("remainingTimeInMillis", 30000)

        startTimer()

        // "다음" 버튼 클릭 리스너 설정
        val defaultinfoNextButton = findViewById<Button>(R.id.defaultinfo_next_button)
        defaultinfoNextButton.setOnClickListener {
            val intent = Intent(this, PracticeGoogle4Activity::class.java)
            intent.putExtra("remainingTimeInMillis", remainingTimeInMillis) // 남은 시간 전달
            startActivity(intent)
        }

        // 다이얼로그를 표시하기 위한 에딧텍스트 가져오기
        val showMonthSelectionDialogEditText = findViewById<EditText>(R.id.month_edittext)
        val showGenderSelectionDialogEditText = findViewById<EditText>(R.id.gender_edittext)

        // 에딧텍스트 클릭 리스너 설정
        showMonthSelectionDialogEditText.setOnClickListener {
            // 월 선택 다이얼로그 표시
            showMonthSelectionDialog()
        }

        showGenderSelectionDialogEditText.setOnClickListener {
            // 성별 선택 다이얼로그 표시
            showGenderSelectionDialog()
        }

        // 성별 입력 칸에 포커스를 주지 않음
        showGenderSelectionDialogEditText.isFocusable = false
        showGenderSelectionDialogEditText.isClickable = true

        // yearEdittext의 텍스트 변경 감지
        binding.yearEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("test", "onTextChanged")
                if (s.toString().isNotEmpty()) {
                    binding.eduScreen.onAction("year_input")
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // monthEdittext의 텍스트 변경 감지
        binding.monthEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty()) {
                    binding.eduScreen.onAction("month_input")
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // dayEdittext의 텍스트 변경 감지
        binding.dayEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("test", "onTextChanged")
                if (s.toString().isNotEmpty()) {
                    binding.eduScreen.onAction("day_input")
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // genderEdittext의 텍스트 변경 감지
        binding.genderEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("test", "onTextChanged")
                if (s.toString().isNotEmpty()) {
                    binding.eduScreen.onAction("gender_input")
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // 타이머 초기화
        timer = object : CountDownTimer(remainingTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                binding.timerTextView.text = secondsLeft.toString() // 초를 텍스트뷰에 표시
            }

            override fun onFinish() {
                if (!success) { // 성공하지 않았을 때만 실패로 저장
                    binding.timerTextView.text = "0" // 타이머 종료 시 "0"으로 표시
                    // saveResult(false) // 실패 결과 저장
                }
            }
        }

        // 문제보기 클릭 시 다이얼로그 띄우기
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }

        timer.start() // 액티비티가 생성되면 타이머 시작
        isTimerRunning = true
    }

    override fun onPause() {
        super.onPause()
        if (isTimerRunning) {
            pausedTimeInMillis = remainingTimeInMillis
            timer.cancel() // 타이머를 취소하여 불필요한 시간 감소를 막음
            isTimerRunning = false
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isTimerRunning) {
            startTimer(pausedTimeInMillis)
        }
    }

    private fun startTimer(startTimeInMillis: Long = remainingTimeInMillis) {
        timer = object : CountDownTimer(startTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                binding.timerTextView.text = secondsLeft.toString()
            }

            override fun onFinish() {
                if (!success) { // 성공하지 않았을 때만 실패로 저장
                    binding.timerTextView.text = "0"
                    // saveResult(false) // 실패 결과 저장
                }
            }
        }

        // 타이머 시작
        timer.start()
        isTimerRunning = true // 타이머가 실행 중임을 나타냄
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun showProblemDialog() {
        val dialogBuilder = AlertDialog.Builder(this)

        // 커스텀 레이아웃을 설정하기 위한 레이아웃 인플레이터
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialoglayout, null)

        dialogBuilder.setView(dialogView)

        val alertDialog = dialogBuilder.create()

        // 다이얼로그 메시지 텍스트뷰 설정
        val numberTextView = dialogView.findViewById<TextView>(R.id.dialogNumber)
        numberTextView.text = "문제 1."

        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        messageTextView.text = "구글 계정 생성을 완료하시오."
        messageTextView.textSize = 20f

        // 확인 버튼 설정
        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            alertDialog.dismiss() // 다이얼로그 닫기

            // 문제 풀이 성공으로 표시
            success = true

            // 타이머를 재시작
            startTimer(remainingTimeInMillis) // 남은 시간을 사용하여 타이머 재시작
        }

        alertDialog.show()

        // 다이얼로그가 나타나면 타이머 멈춤
        timer.cancel()
        isTimerRunning = false
    }

    private fun returnToHomeScreen() {
        val intent = Intent(this, ExamCallProblem2Activity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.stay, R.anim.stay)
    }

    // 새로운 함수 - 월 선택 다이얼로그 표시
    private fun showMonthSelectionDialog() {
        val builder = AlertDialog.Builder(this)
        val dialogView =
            LayoutInflater.from(this).inflate(R.layout.activity_google_month_selection_dialog, null)
        builder.setView(dialogView)

        // 월 선택 라디오 그룹 설정
        val radioGroup = dialogView.findViewById<RadioGroup>(R.id.month_radio_group)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = dialogView.findViewById<RadioButton>(checkedId)
            selectedMonth = radioButton.text.toString()
            binding.monthEdittext.setText(selectedMonth) // 선택한 월을 에딧텍스트에 표시
        }

        // 다이얼로그 생성 및 표시
        val dialog = builder.create()
        dialog.show()

        // 다이얼로그 외부 클릭 시 닫기
        dialogView.setOnTouchListener { _, _ ->
            dialog.dismiss()
            false
        }
    }

    // 새로운 함수 - 성별 선택 다이얼로그 표시
    private fun showGenderSelectionDialog() {
        val builder = AlertDialog.Builder(this)
        val dialogView =
            LayoutInflater.from(this).inflate(R.layout.activity_google_gender_selection_dialog, null)
        builder.setView(dialogView)

        // 성별 선택 라디오 그룹 설정
        val radioGroup = dialogView.findViewById<RadioGroup>(R.id.gender_radio_group)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = dialogView.findViewById<RadioButton>(checkedId)
            selectedGender = radioButton.text.toString()
            binding.genderEdittext.setText(selectedGender) // 선택한 성별을 에딧텍스트에 표시
        }

        // 다이얼로그 생성 및 표시
        val dialog = builder.create()
        dialog.show()

        // 다이얼로그 외부 클릭 시 닫기
        dialogView.setOnTouchListener { _, _ ->
            dialog.dismiss()
            false
        }
    }
}
