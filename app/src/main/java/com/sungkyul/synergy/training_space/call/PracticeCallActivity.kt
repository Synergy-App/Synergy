package com.sungkyul.synergy.training_space.call

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeCallBinding
import com.sungkyul.synergy.learning_space.default_app.phone.adapter.ContactData
import com.sungkyul.synergy.learning_space.default_app.phone.fragment.DefaultPhoneContactFragment
import com.sungkyul.synergy.learning_space.default_app.phone.fragment.DefaultPhoneKeypadFragment
import com.sungkyul.synergy.learning_space.default_app.phone.fragment.DefaultPhoneRecentHistoryFragment
import com.sungkyul.synergy.training_space.call.problem.ExamCallResult1Activity
import com.sungkyul.synergy.training_space.screen.PracticeScreenLock2Activity
import com.sungkyul.synergy.utils.AnimUtils
import com.sungkyul.synergy.utils.GalaxyButton

class PracticeCallActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeCallBinding
    private lateinit var keypadFragment: Fragment
    private lateinit var recentHistoryFragment: Fragment
    private lateinit var contactFragment: Fragment

    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 30000
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeCallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fragments
        keypadFragment = DefaultPhoneKeypadFragment2(binding.eduScreen)
        recentHistoryFragment = DefaultPhoneRecentHistoryFragment()
        contactFragment = DefaultPhoneContactFragment(eduListener = binding.eduScreen)

        // 초기 메인 레이아웃 배경 설정
        updateMainBgColor(R.color.phoneBgColor)

        // 하단 내비게이션 뷰에서 메뉴 아이템을 선택하면, 메인 레이아웃 배경을 변경하고 해당하는 프래그먼트로 교체한다.
        setNavigationButtons()

        // 문제보기 클릭 시 다이얼로그 띄우기
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }

        // 타이머 초기화 및 시작
        startTimer()
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun setNavigationButtons() {
        binding.keypadButton.setOnTouchListener { view, event ->
            handleButtonTouch(view, event) {
                replaceFragment(keypadFragment)
            }
        }

        binding.recentHistoryButton.setOnTouchListener { view, event ->
            handleButtonTouch(view, event) {
                replaceFragment(recentHistoryFragment)
            }
        }

        binding.contactButton.setOnTouchListener { view, event ->
            handleButtonTouch(view, event) {
                replaceFragment(contactFragment)
            }
        }

        replaceFragment(keypadFragment) // 초기 프래그먼트 설정
    }

    private fun handleButtonTouch(view: View, event: MotionEvent, onClick: () -> Unit): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
            }

            MotionEvent.ACTION_UP -> {
                (view as GalaxyButton).startTouchUpAnimation()
                onClick()
            }
        }
        return true
    }

    private fun showHomeScreen() {
        timer.cancel() // 타이머를 취소
        saveResult(success) // 현재의 성공 여부를 저장
        val intent = Intent(this, ExamCallResult1Activity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.scale_up_center, R.anim.fade_out)
    }

    private fun startTimer(startTimeInMillis: Long = remainingTimeInMillis) {
        if (isTimerRunning) return // 타이머가 이미 실행 중이면 새로 시작하지 않음

        timer = object : CountDownTimer(startTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                binding.timerTextView.text = (millisUntilFinished / 1000).toString() // 초를 텍스트뷰에 표시
            }

            override fun onFinish() {
                binding.timerTextView.text = "0" // 타이머 종료 시 "0"으로 표시
                saveResult(false) // 실패 결과 저장
                isTimerRunning = false
                showHomeScreen()

            }
            // 타이머가 종료되면 자동으로 실패 처리됨
        }
        timer.start()
        isTimerRunning = true
    }

    private fun showProblemDialog() {
        stopTimer() // 다이얼로그 열기 전에 타이머 멈춤

        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialoglayout, null)
        dialogBuilder.setView(dialogView)

        val alertDialog = dialogBuilder.create()

        val numberTextView = dialogView.findViewById<TextView>(R.id.dialogNumber)
        numberTextView.text = "문제 1."

        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        messageTextView.text = "010-2345-6789로 전화를 걸어주세요."
        messageTextView.textSize = 20f

        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            alertDialog.dismiss() // 다이얼로그 닫기
            startTimer(remainingTimeInMillis) // 타이머 다시 시작
        }

        alertDialog.show()
    }

    private fun stopTimer() {
        if (isTimerRunning) {
            timer.cancel()
            isTimerRunning = false
            Log.d("PracticeCallActivity", "타이머가 중지되었습니다.")
        }
    }

    private fun returnToHomeScreen() {
        val intent = Intent(this, ExamCallResult1Activity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.stay, R.anim.stay)
    }

    private fun saveResult(isSuccess: Boolean) {
        val sharedPreferences = getSharedPreferences("PracticeCallPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("call_result", isSuccess)
        editor.apply()

        Log.d("PracticeCallActivity", "결과 저장됨: $isSuccess")
    }

    private fun updateMainBgColor(color: Int) {
        val drawable = ContextCompat.getDrawable(applicationContext, color)
        binding.phoneBottomNav.background = drawable
        binding.mainLayout.background = drawable
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.phoneFragmentContainer.id, fragment)
            .commit()
    }

    override fun onPause() {
        super.onPause()
        pausedTimeInMillis = remainingTimeInMillis
        stopTimer() // 타이머를 취소하여 불필요한 시간 감소를 막음
    }

    override fun onResume() {
        super.onResume()
        if (!isTimerRunning) {
            startTimer(pausedTimeInMillis) // 일시 정지된 시간부터 타이머 다시 시작
        }
    }
}
