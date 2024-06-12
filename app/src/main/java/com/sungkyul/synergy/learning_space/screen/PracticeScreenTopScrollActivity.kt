package com.sungkyul.synergy.learning_space.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.sungkyul.synergy.R

class PracticeScreenTopScrollActivity : AppCompatActivity() {
    private var startY = 0f
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 15000 // 초기 카운트 다운 시간 (15초)
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_screen_lock2)

        findViewById<ConstraintLayout>(R.id.constraint_layout).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startY = event.y
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    if (startY - event.y > 100) { // 위로 드래그 거리 설정 (100px 이상 드래그 시)
                        saveResult(true) // 성공 결과 저장
                        showMenuScreen()
                        true
                    } else {
                        false
                    }
                }
                else -> false
            }
        }

        findViewById<View>(R.id.include_toolbar).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startY = event.y
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    if (event.y - startY > 100) { // 아래로 드래그 거리 설정 (100px 이상 드래그 시)
                        success = true // 성공 여부를 true로 설정
                        showTopbarScreen()
                        true
                    } else {
                        false
                    }
                }
                else -> false
            }
        }

        findViewById<View>(R.id.transparent_view_1).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> true
                MotionEvent.ACTION_UP -> {
                    showRecentlyScreen()
                    true
                }
                else -> false
            }
        }

        findViewById<View>(R.id.transparent_view_2).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> true
                MotionEvent.ACTION_UP -> {
                    returnToHomeScreen()
                    true
                }
                else -> false
            }
        }

        findViewById<View>(R.id.transparent_view_3).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> true
                MotionEvent.ACTION_UP -> {
                    onBackPressed()
                    true
                }
                else -> false
            }
        }

        findViewById<TextView>(R.id.problemText).setOnClickListener {
            showProblemDialog()
        }

        startTimer()
    }

    override fun onResume() {
        super.onResume()
        if (!isTimerRunning) {
            startTimer(pausedTimeInMillis)
        }
    }

    override fun onPause() {
        super.onPause()
        pausedTimeInMillis = remainingTimeInMillis
        timer.cancel()
        isTimerRunning = false
    }

    private fun startTimer(startTimeInMillis: Long = remainingTimeInMillis) {
        timer = object : CountDownTimer(startTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                findViewById<TextView>(R.id.timerTextView).text = secondsLeft.toString()
            }

            override fun onFinish() {
                findViewById<TextView>(R.id.timerTextView).text = "0"
                saveResult(false) // 타이머가 종료되면 성공하지 않았으므로 false 저장
                // 실패 처리 후 다음 동작을 수행할 수 있도록 설정
                // 여기서는 필요에 따라 다른 화면으로 이동하도록 하거나 다른 작업을 수행할 수 있습니다.
                // 여기서는 예시로 showRecentlyScreen()을 호출하였습니다.
                showTopbarScreen()
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

        val numberTextView = dialogView.findViewById<TextView>(R.id.dialogNumber)
        numberTextView.text = "문제 2."

        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        messageTextView.text = "상단바 내리세요."
        messageTextView.textSize = 20f

        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            alertDialog.dismiss()
            startTimer(remainingTimeInMillis)
        }

        alertDialog.show()

        timer.cancel()
        isTimerRunning = false
    }

    private fun saveResult(isSuccess: Boolean) {
        val sharedPreferences = getSharedPreferences("PracticeRecentlyDefaultPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("scroll_success", isSuccess)
        editor.apply()
    }

    private fun showMenuScreen() {
        // val intent = Intent(this, ScreenMenuActivity::class.java)
        // startActivity(intent)
        overridePendingTransition(R.anim.slide_up, R.anim.stay)
    }

    private fun showTopbarScreen() {
        timer.cancel() // 타이머를 취소
        saveResult(success) // 현재의 성공 여부를 저장
        val intent = Intent(this, PracticeScreenTopScroll2Activity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_down, R.anim.stay)
    }

    private fun showRecentlyScreen() {
        // val intent = Intent(this, ScreenRecentlyActivity::class.java)
        // startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay)
    }

    private fun returnToHomeScreen() {
        // val intent = Intent(this, ScreenHomeActivity::class.java)
        // startActivity(intent)
        overridePendingTransition(R.anim.stay, R.anim.stay)
    }
}
