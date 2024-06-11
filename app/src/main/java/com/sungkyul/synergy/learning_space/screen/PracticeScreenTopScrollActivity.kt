package com.sungkyul.synergy.learning_space.screen

import android.annotation.SuppressLint
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
import com.sungkyul.synergy.edu_space.screen_layout.ScreenHomeActivity
import com.sungkyul.synergy.edu_space.screen_layout.ScreenMenuActivity
import com.sungkyul.synergy.edu_space.screen_layout.ScreenRecentlyActivity
import com.sungkyul.synergy.edu_space.screen_layout.ScreenTopBarActivity
import com.sungkyul.synergy.learning_space.activity.ExamProblem2Activity

/** 상단바 내리세요 문제 */
class PracticeScreenTopScrollActivity : AppCompatActivity() {
    private var startY = 0f
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 15000 // 초기 카운트 다운 시간 (15초)
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간

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
                        showMenuScreen()
                        true
                    } else {
                        false
                    }
                }
                else -> false
            }
        }

        // 툴바 터치 이벤트 처리
        findViewById<View>(R.id.include_toolbar).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startY = event.y
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    if (event.y - startY > 100) { // 아래로 드래그 거리 설정 (100px 이상 드래그 시)
                        showTopbarScreen()
                        true
                    } else {
                        false
                    }
                }
                else -> false
            }
        }

        // 투명한 뷰 터치 이벤트 처리
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

        // 문제보기 클릭 시 다이얼로그 띄우기
        findViewById<TextView>(R.id.problemText).setOnClickListener {
            showProblemDialog()
        }

        // 타이머 시작
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
                // 타이머 종료 후 수행할 작업 추가
            }
        }

        timer.start()
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
        numberTextView.text = "문제 3."

        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        messageTextView.text = "상단바 내리세요."
        messageTextView.textSize = 20f // 글씨 크기 설정

        // 확인 버튼 설정
        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            alertDialog.dismiss()
            startTimer(remainingTimeInMillis)
        }

        alertDialog.show()

        // 다이얼로그가 나타나면 타이머 멈춤
        timer.cancel()
        isTimerRunning = false
    }

    private fun showMenuScreen() {
//        val intent = Intent(this, ScreenMenuActivity::class.java)
//        startActivity(intent)
        overridePendingTransition(R.anim.slide_up, R.anim.stay)
    }

    private fun showTopbarScreen() {
        val intent = Intent(this, PracticeScreenTopScroll2Activity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_down, R.anim.stay)
    }

    private fun showRecentlyScreen() {
//        val intent = Intent(this, ScreenRecentlyActivity::class.java)
//        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay)
    }

    private fun returnToHomeScreen() {
//        val intent = Intent(this, ScreenHomeActivity::class.java)
//        startActivity(intent)
        overridePendingTransition(R.anim.stay, R.anim.stay)
    }


}
