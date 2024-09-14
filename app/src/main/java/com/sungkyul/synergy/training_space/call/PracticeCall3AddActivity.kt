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
import com.sungkyul.synergy.training_space.call.problem.ExamCallProblem2Activity
import com.sungkyul.synergy.training_space.call.problem.ExamCallProblem4Activity
import com.sungkyul.synergy.training_space.call.problem.ExamCallResult3Activity
import com.sungkyul.synergy.utils.AnimUtils

class PracticeCall3AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeCall3AddBinding
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 30000

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeCall3AddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startTimer(remainingTimeInMillis)

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
                    val intent = Intent(this, ExamCallResult3Activity::class.java)
                    startActivity(intent)
                }
            }
            true
        }

        // 문제보기 클릭 시 다이얼로그 띄우기
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }
    }

    override fun onPause() {
        super.onPause()
        if (isTimerRunning) {
            timer.cancel() // 타이머를 취소
            isTimerRunning = false
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isTimerRunning) {
            startTimer(remainingTimeInMillis) // 남은 시간으로 타이머 시작
        }
    }

    private fun startTimer(startTimeInMillis: Long) {
        timer = object : CountDownTimer(startTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                binding.timerTextView.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                binding.timerTextView.text = "0"
                // 추가적인 종료 시 처리 로직
                isTimerRunning = false // 타이머가 종료되었음을 표시
            }
        }
        timer.start()
        isTimerRunning = true
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun showProblemDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialoglayout, null)
        dialogBuilder.setView(dialogView)

        val alertDialog = dialogBuilder.create()

        // 다이얼로그 메시지 텍스트뷰 설정
        val numberTextView = dialogView.findViewById<TextView>(R.id.dialogNumber)
        numberTextView.text = "문제 3."

        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        messageTextView.text = "연락처에 다음과 같이 저장하시오.\n이름: 시너지, \n전화번호: 010-1111-1111."
        messageTextView.textSize = 20f

        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            alertDialog.dismiss()
            // 문제 풀이 성공으로 표시
            // success = true // 필요한 경우 성공 처리
        }

        alertDialog.setOnShowListener {
            if (isTimerRunning) {
                timer.cancel() // 다이얼로그가 보일 때 타이머 취소
                isTimerRunning = false
            }
        }

        alertDialog.setOnDismissListener {
            if (!isTimerRunning) {
                startTimer(remainingTimeInMillis) // 다이얼로그가 닫힌 후 타이머 재시작
            }
        }

        alertDialog.show()
    }
}
