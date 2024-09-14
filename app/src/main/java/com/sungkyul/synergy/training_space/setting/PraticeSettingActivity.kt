package com.sungkyul.synergy.training_space.setting

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPraticeSettingBinding
import com.sungkyul.synergy.training_space.call.problem.ExamCallProblem2Activity

class PraticeSettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPraticeSettingBinding

    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 30000
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPraticeSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 타이머 시작
        startTimer()

        // setting_display_btn 클릭 이벤트 처리
        binding.settingDisplayBtn.setOnClickListener {
            if (binding.eduScreen.onAction("tap_display_item")) {
                val intent = Intent(this, PracticeSettingDisActivity::class.java)
                intent.putExtra("remainingTimeInMillis", remainingTimeInMillis)
                startActivity(intent)
            }
        }

        // 문제보기 클릭 시 다이얼로그 띄우기
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }
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
                binding.timerTextView.text = secondsLeft.toString() // 초를 텍스트뷰에 표시
            }

            override fun onFinish() {
                if (!success) { // 성공하지 않았을 때만 실패로 저장
                    binding.timerTextView.text = "0" // 타이머 종료 시 "0"으로 표시
                }
            }
        }

        timer.start() // 타이머 시작
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
        numberTextView.text = "문제 1."

        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        messageTextView.text = "글자 크기를 최대로 높여보세요."
        messageTextView.textSize = 20f // 글씨 크기 설정

        // 확인 버튼 설정
        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            alertDialog.dismiss() // 다이얼로그 닫기
            // 다이얼로그 닫을 때 타이머를 다시 시작
            startTimer(remainingTimeInMillis)
        }

        alertDialog.setOnShowListener {
            timer.cancel() // 다이얼로그가 열릴 때 타이머 멈춤
            isTimerRunning = false
        }

        alertDialog.setOnDismissListener {
            // 다이얼로그가 닫힐 때 타이머 다시 시작
            startTimer(remainingTimeInMillis)
        }

        alertDialog.show()
        timer.cancel() // 다이얼로그가 나타나면 타이머 멈춤
        isTimerRunning = false
    }

    private fun returnToHomeScreen() {
        val intent = Intent(this, ExamCallProblem2Activity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.stay, R.anim.stay)
    }
}
