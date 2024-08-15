package com.sungkyul.synergy.training_space.google

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import com.sungkyul.synergy.R
import com.sungkyul.synergy.courses.accountedu.GoogleMailCourse
import com.sungkyul.synergy.databinding.ActivityGoogleMailBinding
import com.sungkyul.synergy.databinding.ActivityPracticeGoogle4Binding
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.learning_space.accountedu.GooglePasswordActivity
import com.sungkyul.synergy.training_space.call.problem.ExamCallProblem2Activity

class PracticeGoogle4Activity : AppCompatActivity() {

    private lateinit var binding: ActivityPracticeGoogle4Binding

    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 300000
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeGoogle4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        remainingTimeInMillis = intent.getLongExtra("remainingTimeInMillis", 30000)

        startTimer()

        // 뷰 초기화
        binding.radioButtonTemp3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showNewMailEditText()
                binding.eduScreen.onAction("mail_click")
            } else {
                hideNewMailEditText()
            }
        }
        binding.mailNextButton.setOnClickListener {
            // GooglePasswordActivity 로 이동하는 인텐트 생성
//            val nextIntent = Intent(this, PracticeGoogle5Activity::class.java)
//            intent.putExtra("remainingTimeInMillis", remainingTimeInMillis)
//
//            // 값을 전달한다.
//            nextIntent.putExtras(intent)
//            nextIntent.putExtra("email", when (binding.mailRadioGroup.checkedRadioButtonId) {
//                binding.radioButtonTemp1.id -> binding.radioButtonTemp1.text.toString()
//                binding.radioButtonTemp2.id -> binding.radioButtonTemp2.text.toString()
//                else -> binding.newMailAddressEdittext.text.toString()
//            })
//            startActivity(nextIntent) // 화면 전환
            val intent = Intent(this, PracticeGoogle5Activity::class.java)
            intent.putExtra("remainingTimeInMillis", remainingTimeInMillis) // 남은 시간 전달
            startActivity(intent)
        }

        // newMailAddressEditText의 텍스트 변경 감지
        binding.newMailAddressEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 필요시 구현
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("test", "onTextChanged")
                if (s.toString().isNotEmpty()) {
                    // 사용자가 텍스트를 입력한 경우
                    binding.eduScreen.onAction("mail_input")
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // 필요시 구현
            }
        })

        // 타이머 초기화
        timer = object : CountDownTimer(remainingTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                binding.timerTextView.text = secondsLeft.toString() // 초를 텍스트뷰에 표시
            }

            override fun onFinish() {
                binding.timerTextView.text = "0" // 타이머 종료 시 "0"으로 표시
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
        pausedTimeInMillis = remainingTimeInMillis
        timer.cancel() // 타이머를 취소하여 불필요한 시간 감소를 막음
        isTimerRunning = false
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
                findViewById<TextView>(R.id.timerTextView).text = secondsLeft.toString()
            }

            override fun onFinish() {
                if (!success) { // 성공하지 않았을 때만 실패로 저장
                    findViewById<TextView>(R.id.timerTextView).text = "0"
                    // saveResult(false) // 실패 결과 저장
                }
                // 타이머가 종료되면 자동으로 실패 처리됨
                //  returnToHomeScreen()
            }
        }

        // 문제보기 클릭 시 다이얼로그 띄우기
        findViewById<TextView>(R.id.problemText).setOnClickListener {
            showProblemDialog()
        }

        timer.start() // 액티비티가 생성되면 타이머 시작
        isTimerRunning = true
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun showProblemDialog() {
        val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(this)

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

            // ///////saveResult(true) // 문제 풀이 성공으로 표시
            // returnToHomeScreen() // 홈 화면으로 이동
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

    private fun showNewMailEditText() {
        // 메일 에딧창, 작은 텍스트 표시
        binding.newMailAddressEdittext.visibility = View.VISIBLE
        binding.smallText.visibility = View.VISIBLE

        // 기존 이메일 사용 텍스트와 다음 버튼을 메일 에딧창 아래로 이동
        moveTextAndButton()
    }

    private fun hideNewMailEditText() {
        // 메일 에딧창, 작은 텍스트 숨기기
        binding.newMailAddressEdittext.visibility = View.GONE
        binding.smallText.visibility = View.GONE

        // 기존 위치로 이동
        resetTextAndButton()
    }

    private fun moveTextAndButton() {
        val params = binding.newMailAddressEdittext.layoutParams as ConstraintLayout.LayoutParams
        val existingMailParams = binding.existingMailText.layoutParams as ConstraintLayout.LayoutParams
        val buttonParams = binding.mailNextButton.layoutParams as ConstraintLayout.LayoutParams
        val smallTextParams = binding.smallText.layoutParams as ConstraintLayout.LayoutParams

        // 메일 에딧창 아래로 이동
        params.topToBottom = R.id.mail_radio_group
        binding.newMailAddressEdittext.layoutParams = params

        // 작은 텍스트 아래로 이동
        smallTextParams.topToBottom = R.id.new_mail_address_edittext
        binding.smallText.layoutParams = smallTextParams

        // 기존 이메일 사용 텍스트와 다음 버튼도 메일 에딧창 아래로 이동
        existingMailParams.topToBottom = R.id.small_text
        binding.existingMailText.layoutParams = existingMailParams

        buttonParams.topToBottom = R.id.small_text
        binding.mailNextButton.layoutParams = buttonParams

        // 스몰 텍스트 왼쪽으로 이동
        binding.smallText.translationX = -120f // 이동 거리 조정

        // 스몰 텍스트와 다른 뷰 사이의 간격 조정
        smallTextParams.marginStart = 0 // 간격 조정
        existingMailParams.topMargin = 120 // 간격 조정
        buttonParams.topMargin = 120 // 간격 조정
    }

    private fun resetTextAndButton() {
        val existingMailParams = binding.existingMailText.layoutParams as ConstraintLayout.LayoutParams
        val buttonParams = binding.mailNextButton.layoutParams as ConstraintLayout.LayoutParams

        // 기존 이메일 사용 텍스트와 다음 버튼을 초기 위치로 되돌림
        existingMailParams.topToBottom = R.id.mail_radio_group
        binding.existingMailText.layoutParams = existingMailParams

        buttonParams.topToBottom = R.id.existing_mail_text
        binding.mailNextButton.layoutParams = buttonParams
    }
}
