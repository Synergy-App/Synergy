package com.sungkyul.synergy.training_space.call

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import com.sungkyul.synergy.R
import com.sungkyul.synergy.courses.default_app.phone.DefaultPhoneCourse3
import com.sungkyul.synergy.databinding.ActivityDefaultPhoneAddBinding
import com.sungkyul.synergy.databinding.ActivityPracticeCall3AddBinding
import com.sungkyul.synergy.databinding.ActivityPracticeCall3Binding
import com.sungkyul.synergy.learning_space.default_app.DefaultAppActivity
import com.sungkyul.synergy.learning_space.default_app.phone.activity.DefaultPhoneActivity
import com.sungkyul.synergy.training_space.call.problem.ExamCallProblem2Activity
import com.sungkyul.synergy.training_space.call.problem.ExamCallProblem4Activity
import com.sungkyul.synergy.utils.AnimUtils

class PracticeCall3AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeCall3AddBinding
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 30000
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeCall3AddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startTimer()


        // 각 버튼의 터치 애니메이션을 초기화한다.
        AnimUtils.initTouchButtonAnimation(binding.cancelButton)
        AnimUtils.initTouchButtonAnimation(binding.saveButton)


        // 이벤트 리스너들을 추가한다.
        binding.cancelButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownButtonAnimation(this, view)
                }

                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpButtonAnimation(this, view)

                    //finish()
                }
            }
            true
        }
        binding.saveButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownButtonAnimation(this, view)
                }

                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpButtonAnimation(this, view)

                    val intent = Intent(this, ExamCallProblem4Activity::class.java)
//                    intent.putExtra(
//                        "from",
//                        "save_contact"
//                    ) // id 값 바꾸기 - 연락처 저장 눌렀을 때 변화하는 화면을 알아야 하기 때문
//                    intent.putExtra("name", binding.phoneNameEditText.text.toString())
//                    intent.putExtra("num", binding.phoneNumEditText.text.toString())
//                    intent.putExtra("email", binding.phoneEmailEditText.text.toString())
//                    intent.putExtra("group", binding.phoneGroupEditText.text.toString())
                    startActivity(intent)
                }
            }
            true
        }
        binding.phoneNameEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.eduScreen.onAction("phone_name_edit_text")
            }

        }
        //이름 적는 칸
        binding.phoneNameEditText.setOnClickListener {
            if (binding.eduScreen.onAction("phone_name_edit_text")) {
                // id가 서로 일치하면 이 부분이 실행된다.
            }
        }
        //전화번호 적는 칸
        binding.phoneNumEditText.setOnClickListener {

            val inputText = binding.phoneNameEditText.text.toString()
            if (inputText == "시너지") {
                binding.eduScreen.onAction("phone_num_edit_text")
            }
        }
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
        val dialogBuilder = AlertDialog.Builder(this)

        // 커스텀 레이아웃을 설정하기 위한 레이아웃 인플레이터
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialoglayout, null)

        dialogBuilder.setView(dialogView)

        val alertDialog = dialogBuilder.create()

        // 다이얼로그 메시지 텍스트뷰 설정
        val numberTextView = dialogView.findViewById<TextView>(R.id.dialogNumber)
        numberTextView.text = "문제 3."

        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        messageTextView.text = "연락처에 다음과 같이 저장하시오.\n이름: 시너지, \n전화번호: 010-1111-1111."
        messageTextView.textSize = 20f // 글씨 크기 설정

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
}
