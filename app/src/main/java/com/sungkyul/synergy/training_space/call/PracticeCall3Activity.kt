package com.sungkyul.synergy.training_space.call

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeCall3Binding
import com.sungkyul.synergy.learning_space.default_app.phone.fragment.DefaultPhoneRecentHistoryFragment
import com.sungkyul.synergy.training_space.call.problem.ExamCallProblem2Activity
import com.sungkyul.synergy.utils.GalaxyButton

class PracticeCall3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeCall3Binding
    private lateinit var keypadFragment: Fragment
    private lateinit var recentHistoryFragment: Fragment
    private lateinit var contactFragment: Fragment

    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 30000
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeCall3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        startTimer()

        // Fragments
        keypadFragment = DefaultPhoneKeypadFragment2(binding.eduScreen)
        recentHistoryFragment = DefaultPhoneRecentHistoryFragment()
        contactFragment = DefaultPhoneContact2Fragment(eduListener = binding.eduScreen)

        setupButtons()

        replaceFragment(contactFragment)

        // 문제보기 클릭 시 다이얼로그 띄우기
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }
    }

    private fun setupButtons() {
        binding.keypadButton.post {
            binding.keypadButton.clipToRoundRect(20.0f)
        }
        binding.recentHistoryButton.post {
            binding.recentHistoryButton.clipToRoundRect(20.0f)
        }
        binding.contactButton.post {
            binding.contactButton.clipToRoundRect(20.0f)
        }
        binding.contactButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }
                MotionEvent.ACTION_UP -> {
                    (view as GalaxyButton).startTouchUpAnimation()
                    if (binding.eduScreen.onAction("click_contact_button")) {
                        replaceFragment(contactFragment)
                    }
                }
            }
            true
        }
    }
    private fun saveResult(isSuccess: Boolean) {
        val sharedPreferences = getSharedPreferences("PracticeCallPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("call3_result", isSuccess)
        editor.apply()
    }

    private fun showHomeScreen() {
        timer.cancel() // 타이머를 취소
        saveResult(success) // 현재의 성공 여부를 저장
        val intent = Intent(this, PracticeCall2ResultActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.scale_up_center, R.anim.fade_out)
    }


    private fun startTimer(startTimeInMillis: Long = remainingTimeInMillis) {
        timer = object : CountDownTimer(startTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                binding.timerTextView.text = secondsLeft.toString()
            }

            override fun onFinish() {
                binding.timerTextView.text = "0"
                saveResult(false) // 실패 결과 저장
                isTimerRunning = false
                showHomeScreen()
            }
        }
        timer.start()
        isTimerRunning = true // 타이머가 실행 중임을 표시
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
            success = true
            // 타이머를 다시 시작
            startTimer(remainingTimeInMillis)
        }

        alertDialog.setOnShowListener {
            if (isTimerRunning) {
                timer.cancel() // 다이얼로그가 보일 때 타이머 취소
                isTimerRunning = false // 타이머 상태 업데이트
            }
        }

        alertDialog.show()
    }

    override fun onPause() {
        super.onPause()
        if (isTimerRunning) {
            timer.cancel() // 액티비티가 일시정지될 때 타이머 취소
            isTimerRunning = false // 타이머 상태 업데이트
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isTimerRunning) {
            startTimer(remainingTimeInMillis) // 남은 시간으로 타이머 시작
        }
    }

    private fun returnToHomeScreen() {
        val intent = Intent(this, ExamCallProblem2Activity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.stay, R.anim.stay)
    }


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.phoneFragmentContainer.id, fragment)
            .commit()
    }
}
