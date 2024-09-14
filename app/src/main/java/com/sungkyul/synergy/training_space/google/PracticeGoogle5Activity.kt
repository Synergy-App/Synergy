package com.sungkyul.synergy.training_space.google

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeGoogle5Binding
import com.sungkyul.synergy.training_space.call.problem.ExamCallProblem2Activity

class PracticeGoogle5Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeGoogle5Binding
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 300000
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeGoogle5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        remainingTimeInMillis = intent.getLongExtra("remainingTimeInMillis", 300000)
        startTimer()

        // 초기화 시 비밀번호 필드를 가림
        togglePasswordVisibility(false)

        // 뷰 초기화
        binding.showPasswordCheckbox.setOnCheckedChangeListener { _, isChecked ->
            togglePasswordVisibility(isChecked)
        }

        binding.googlePwNextButton.setOnClickListener {
            val password = binding.googlePwEdittext.text.toString()
            val confirmPassword = binding.googlePwCheckEdittext.text.toString()

            if (password == confirmPassword) {
                // 비밀번호 일치 시 다음 화면으로 이동
                val nextIntent = Intent(this, PracticeGoogle6Activity::class.java)
                nextIntent.putExtra("remainingTimeInMillis", remainingTimeInMillis) // 남은 시간 전달
                nextIntent.putExtra("password", password) // 비밀번호 전달
                startActivity(nextIntent)
            } else {
                // 비밀번호 불일치
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        // 텍스트 변경 감지
        setupTextWatchers()

        // 문제보기 클릭 시 다이얼로그 띄우기
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }
    }

    private fun setupTextWatchers() {
        binding.googlePwEdittext.addTextChangedListener(createTextWatcher("pw_input"))
        binding.googlePwCheckEdittext.addTextChangedListener(createTextWatcher("pw_check_input"))
    }

    private fun createTextWatcher(action: String): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty()) {
                    binding.eduScreen.onAction(action)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        }
    }

    private fun startTimer() {
        timer = object : CountDownTimer(remainingTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                updateTimerText()
            }

            override fun onFinish() {
                binding.timerTextView.text = "0"
                returnToHomeScreen()
            }
        }.start()
        isTimerRunning = true
    }

    private fun updateTimerText() {
        val secondsLeft = remainingTimeInMillis / 1000
        binding.timerTextView.text = secondsLeft.toString()
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
        if (!isTimerRunning && pausedTimeInMillis > 0) {
            remainingTimeInMillis = pausedTimeInMillis // 남은 시간을 가져옴
            startTimer() // 이전 타이머를 새로 시작
        }
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

        // 확인 버튼 설정
        dialogView.findViewById<Button>(R.id.confirmButton).setOnClickListener {
            alertDialog.dismiss() // 다이얼로그 닫기
            // 다이얼로그가 닫힌 후 타이머 재시작
            if (!isTimerRunning) {
                startTimer() // 남은 시간으로 타이머를 시작
            }
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

    private fun togglePasswordVisibility(visible: Boolean) {
        binding.googlePwEdittext.transformationMethod = if (visible) {
            HideReturnsTransformationMethod.getInstance()
        } else {
            PasswordTransformationMethod.getInstance()
        }
        binding.googlePwCheckEdittext.transformationMethod =
            binding.googlePwEdittext.transformationMethod
    }
}
