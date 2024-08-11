package com.sungkyul.synergy.training_space.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AlertDialog
import com.sungkyul.synergy.utils.GALAXY_NOTE9
import com.sungkyul.synergy.databinding.ActivityExamStartBinding

class ExamStartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExamStartBinding
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 10000 // 초기 카운트 다운 시간 (10초)
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**타이머 멈추고 실행*/
        // 타이머 초기화
        timer = object : CountDownTimer(remainingTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                binding.timerTextView.text = secondsLeft.toString() // 초를 텍스트뷰에 표시
            }

            override fun onFinish() {
                binding.timerTextView.text = "0" // 타이머 종료 시 "0"으로 표시
                // 타이머 종료 후 수행할 작업 추가

            }
        }

        // 문제보기 클릭 시 다이얼로그 띄우기
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }

        timer.start() // 액티비티가 생성되면 타이머 시작
        isTimerRunning = true

        if (Build.MODEL == GALAXY_NOTE9) {
            binding.timerTextView.textSize = 18.0f
            binding.problemText.textSize = 18.0f
        }
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

    private fun startTimer(startTimeInMillis: Long) {
        timer = object : CountDownTimer(startTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                binding.timerTextView.text = secondsLeft.toString() // 초를 텍스트뷰에 표시
            }

            override fun onFinish() {
                binding.timerTextView.text = "0" // 타이머 종료 시 "0"으로 표시
                // 타이머 종료 후 수행할 작업 추가
            }
        }
        // 문제보기 클릭 시 다이얼로그 띄우기
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }

        timer.start() // 액티비티가 생성되면 타이머 시작
        isTimerRunning = true
    }

    private fun showProblemDialog() {
        // 다이얼로그 생성
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("")
            .setMessage("주어진 시간내에 어플을 홈화면에 배치하시오.")
            .setPositiveButton("확인") { dialog, _ ->
                dialog.dismiss() // 다이얼로그 닫기
                // 다이얼로그가 닫힐 때 타이머 다시 시작
                startTimer(remainingTimeInMillis)
            }
        val dialog = dialogBuilder.create()
        dialog.show() // 다이얼로그 보이기

        // 다이얼로그가 나타나면 타이머 멈춤
        timer.cancel()
        isTimerRunning = false
    }
}