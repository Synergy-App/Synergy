package com.sungkyul.synergy.training_space.google

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeGoogle6Binding
import com.sungkyul.synergy.training_space.call.problem.ExamCallProblem2Activity
import com.sungkyul.synergy.training_space.setting.result.ExamSettingResultActivity

class PracticeGoogle6Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeGoogle6Binding
    private lateinit var timer: CountDownTimer
    private var remainingTimeInMillis: Long = 300000 // 초기 남은 시간 (5분)
    private var isTimerRunning = false
    private var success: Boolean = false // 성공 여부

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeGoogle6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 남은 시간 초기화
        remainingTimeInMillis = intent.getLongExtra("remainingTimeInMillis", 300000)

        startTimer()

        // 버튼 클릭 시 다음 화면으로 이동
        binding.pwNextButton.setOnClickListener {
            val nextIntent = Intent(this, PracticeGoogle7Activity::class.java).apply {
                putExtras(intent)
                putExtra("phone_number", binding.phoneEdittext.text.toString())
                putExtra("remainingTimeInMillis", remainingTimeInMillis)
            }
            startActivity(nextIntent)
        }

        // 전화번호 입력 시 텍스트 변경 감지
        binding.phoneEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("test", "onTextChanged")
                if (s.toString().isNotEmpty()) {
                    binding.eduScreen.onAction("phone_input")
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // 문제보기 클릭 시 다이얼로그 띄우기
        binding.problemText.setOnClickListener { showProblemDialog() }
    }

    override fun onPause() {
        super.onPause()
        if (isTimerRunning) {
            timer.cancel() // 타이머 멈춤
            isTimerRunning = false
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isTimerRunning) {
            startTimer() // 타이머 재시작
        }
    }

    private fun startTimer() {
        timer = object : CountDownTimer(remainingTimeInMillis, 1000) {
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

    @SuppressLint("ClickableViewAccessibility")
    private fun showProblemDialog() {
        val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialoglayout, null)

        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()

        // 다이얼로그 메시지 설정
        dialogView.findViewById<TextView>(R.id.dialogNumber).text = "문제 1."
        dialogView.findViewById<TextView>(R.id.dialogMessage).apply {
            text = "구글 계정 생성을 완료하시오."
            textSize = 20f
        }

        // 확인 버튼 클릭 시 다이얼로그 닫기
        dialogView.findViewById<Button>(R.id.confirmButton).setOnClickListener {
            alertDialog.dismiss()
            startTimer() // 다이얼로그가 닫힌 후 타이머 재시작
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
