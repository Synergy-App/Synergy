package com.sungkyul.synergy.training_space.install

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeInstall2Binding
import com.sungkyul.synergy.databinding.ActivityPracticeInstallBinding
import com.sungkyul.synergy.learning_space.appinstall.AppInstallLoadingActivity
import com.sungkyul.synergy.learning_space.appinstall.AppInstallSearchActivity
import com.sungkyul.synergy.training_space.install.result.ExamInstallResultActivity
import com.sungkyul.synergy.training_space.message.result.ExamMessageResultActivity

class PracticeInstall2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityPracticeInstall2Binding
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 30000
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeInstall2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        startTimer(remainingTimeInMillis)
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }

        timer.start() // 액티비티가 생성되면 타이머 시작
        isTimerRunning = true
        val installButton = findViewById<Button>(R.id.kakao_install_btn)
        installButton.setOnClickListener {
            if (binding.eduScreen.onAction("click_install_button")) {
                // 다음 액티비티로 이동하는 Intent 생성
                val intent = Intent(this, PracticeInstall3Activity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION) // 애니메이션 없이 액티비티 시작
                startActivity(intent) // 다음 액티비티로 이동
                overridePendingTransition(0, 0) // 애니메이션 없이 액티비티 전환
            }
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
                binding.timerTextView.text = "0" // 타이머 종료 시 "0"으로 표시
                saveResult(false) // 실패 결과 저장
                isTimerRunning = false
                showHomeScreen()
            }
        }.start()
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
        messageTextView.text = "'카카오톡'을 검색하고 '카카오톡 어플을 설치하시오."
        messageTextView.textSize = 20f // 글씨 크기 설정

        // 확인 버튼 설정
        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            alertDialog.dismiss() // 다이얼로그 닫기
            // 타이머를 다시 시작
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

        // 타이머 멈춤
        timer.cancel()
        isTimerRunning = false
    }

    private fun saveResult(isSuccess: Boolean) {
        val sharedPreferences = getSharedPreferences("PracticeInstallPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("install_result", isSuccess)
        editor.apply()
    }

    private fun showHomeScreen() {
        timer.cancel() // 타이머를 취소
        saveResult(success) // 현재의 성공 여부를 저장
        val intent = Intent(this, ExamInstallResultActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.scale_up_center, R.anim.fade_out)

    }
}