package com.sungkyul.synergy.training_space.google

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeGoogle8Binding
import com.sungkyul.synergy.training_space.call.problem.ExamCallProblem2Activity
import com.sungkyul.synergy.training_space.setting.result.ExamSettingResultActivity

class PracticeGoogle8Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeGoogle8Binding
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 300000 // 초기 남은 시간 (5분)
    private var pausedTimeInMillis: Long = remainingTimeInMillis // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeGoogle8Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 남은 시간 초기화
        remainingTimeInMillis = intent.getLongExtra("remainingTimeInMillis", 300000)

        binding.mailAddSkipButton.setOnClickListener {
            val nextIntent = Intent(this, PracticeGoogle9Activity::class.java)
            nextIntent.putExtra("remainingTimeInMillis", remainingTimeInMillis) // 남은 시간 전달
            startActivity(nextIntent)
        }

        // 타이머 초기화
        startTimer()

        // 문제보기 클릭 시 다이얼로그 띄우기
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }
    }

    private fun startTimer(startTimeInMillis: Long = remainingTimeInMillis) {
        timer = object : CountDownTimer(startTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                binding.timerTextView.text = secondsLeft.toString() // 초를 텍스트뷰에 표시
            }
            override fun onFinish() {
                binding.timerTextView.text = "0" // 타이머 종료 시 "0"으로 표시
                saveResult(false) // 실패 결과 저장
                isTimerRunning = false
                showHomeScreen()
            }
        }.start()
        isTimerRunning = true
    }
    private fun saveResult(isSuccess: Boolean) {
        val sharedPreferences = getSharedPreferences("PracticeGooglePrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("google_result", isSuccess)
        editor.apply()
    }

    private fun showHomeScreen() {
        timer.cancel() // 타이머를 취소
        saveResult(success) // 현재의 성공 여부를 저장
        val intent = Intent(this, ExamSettingResultActivity::class.java)
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        pausedTimeInMillis = remainingTimeInMillis
        if (isTimerRunning) {
            timer.cancel() // 타이머를 취소하여 불필요한 시간 감소를 막음
            isTimerRunning = false
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isTimerRunning) {
            startTimer(pausedTimeInMillis) // 일시 정지된 시간으로 타이머 재시작
        }
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

            // 타이머 재시작
            if (!isTimerRunning) {
                startTimer(remainingTimeInMillis) // 남아 있는 시간을 사용하여 타이머 시작
            }

            // 문제 풀이 성공으로 표시
            // saveResult(true)
            // returnToHomeScreen() // 홈 화면으로 이동
        }

        alertDialog.show()

        // 다이얼로그가 나타나면 타이머 멈춤
        if (isTimerRunning) {
            timer.cancel()
            isTimerRunning = false
        }
    }

    private fun returnToHomeScreen() {
        val intent = Intent(this, ExamCallProblem2Activity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.stay, R.anim.stay)
    }
}
