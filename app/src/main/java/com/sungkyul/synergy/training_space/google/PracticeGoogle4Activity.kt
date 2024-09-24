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
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeGoogle4Binding
import com.sungkyul.synergy.training_space.call.problem.ExamCallProblem2Activity
import com.sungkyul.synergy.training_space.setting.result.ExamSettingResultActivity

class PracticeGoogle4Activity : AppCompatActivity() {

    private lateinit var binding: ActivityPracticeGoogle4Binding
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 300000 // 5분
    private var pausedTimeInMillis: Long = remainingTimeInMillis // 일시정지된 시간
    private var success: Boolean = false // 성공 여부

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeGoogle4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        remainingTimeInMillis = intent.getLongExtra("remainingTimeInMillis", 300000)

        startTimer()

        // 라디오 버튼 클릭 시 메일 에딧텍스트 표시
        binding.radioButtonTemp3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showNewMailEditText()
                binding.eduScreen.onAction("mail_click")
            } else {
                hideNewMailEditText()
            }
        }

        // 다음 버튼 클릭 시 다음 액티비티로 이동
        binding.mailNextButton.setOnClickListener {
            val intent = Intent(this, PracticeGoogle5Activity::class.java)
            intent.putExtra("remainingTimeInMillis", remainingTimeInMillis) // 남은 시간 전달
            startActivity(intent)
        }

        // newMailAddressEditText의 텍스트 변경 감지
        binding.newMailAddressEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("test", "onTextChanged")
                if (s.toString().isNotEmpty()) {
                    binding.eduScreen.onAction("mail_input")
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // 문제보기 클릭 시 다이얼로그 띄우기
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }
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

    private fun startTimer(startTimeInMillis: Long = remainingTimeInMillis) {
        timer = object : CountDownTimer(startTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                binding.timerTextView.text = secondsLeft.toString()
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
        val numberTextView = dialogView.findViewById<TextView>(R.id.dialogNumber)
        numberTextView.text = "문제 1."

        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        messageTextView.text = "구글 계정 생성을 완료하시오."
        messageTextView.textSize = 20f

        // 확인 버튼 클릭 시 다이얼로그 닫고 타이머 재시작
        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            alertDialog.dismiss() // 다이얼로그 닫기
            // 타이머 재시작
            startTimer(remainingTimeInMillis) // 남아 있는 시간을 사용하여 타이머를 재시작
        }

        alertDialog.show()

        // 다이얼로그가 나타나면 타이머 멈춤
        if (isTimerRunning) {
            timer.cancel() // 타이머를 멈춤
            isTimerRunning = false
        }
    }

    private fun returnToHomeScreen() {
        val intent = Intent(this, ExamCallProblem2Activity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.stay, R.anim.stay)
    }

    private fun showNewMailEditText() {
        binding.newMailAddressEdittext.visibility = View.VISIBLE
        binding.smallText.visibility = View.VISIBLE
        moveTextAndButton()
    }

    private fun hideNewMailEditText() {
        binding.newMailAddressEdittext.visibility = View.GONE
        binding.smallText.visibility = View.GONE
        resetTextAndButton()
    }

    private fun moveTextAndButton() {
        val params = binding.newMailAddressEdittext.layoutParams as ConstraintLayout.LayoutParams
        val existingMailParams =
            binding.existingMailText.layoutParams as ConstraintLayout.LayoutParams
        val buttonParams = binding.mailNextButton.layoutParams as ConstraintLayout.LayoutParams
        val smallTextParams = binding.smallText.layoutParams as ConstraintLayout.LayoutParams

        params.topToBottom = R.id.mail_radio_group
        binding.newMailAddressEdittext.layoutParams = params

        smallTextParams.topToBottom = R.id.new_mail_address_edittext
        binding.smallText.layoutParams = smallTextParams

        existingMailParams.topToBottom = R.id.small_text
        binding.existingMailText.layoutParams = existingMailParams

        buttonParams.topToBottom = R.id.small_text
        binding.mailNextButton.layoutParams = buttonParams

        binding.smallText.translationX = -120f // 이동 거리 조정
        smallTextParams.marginStart = 0 // 간격 조정
        existingMailParams.topMargin = 120 // 간격 조정
        buttonParams.topMargin = 120 // 간격 조정
    }

    private fun resetTextAndButton() {
        val existingMailParams =
            binding.existingMailText.layoutParams as ConstraintLayout.LayoutParams
        val buttonParams = binding.mailNextButton.layoutParams as ConstraintLayout.LayoutParams

        existingMailParams.topToBottom = R.id.mail_radio_group
        binding.existingMailText.layoutParams = existingMailParams

        buttonParams.topToBottom = R.id.existing_mail_text
        binding.mailNextButton.layoutParams = buttonParams
    }
}
