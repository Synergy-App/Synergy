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
import com.sungkyul.synergy.R
import com.sungkyul.synergy.learning_space.activity.ExamProblem3Activity

/**기본화면에서 하단바 클릭해서 최근 앱 보는 퀴즈화면
 * 이거 기본화면 */
class PracticeRecentlyDefaultActivity : AppCompatActivity() {
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 30000
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가


    @SuppressLint("MissingInflatedId", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_recently_default)

        // 투명한 뷰 터치 이벤트 처리
        findViewById<View>(R.id.transparent_view_1).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> true
                MotionEvent.ACTION_UP -> {
                    success = true // 성공 여부를 true로 설정
                    showRecentlyScreen()
                    true
                }
                else -> false
            }
        }

        // 타이머 시작
        startTimer(remainingTimeInMillis)
    }

    override fun onPause() {
        super.onPause()
        pausedTimeInMillis = remainingTimeInMillis
        timer.cancel()
        isTimerRunning = false
    }

    override fun onResume() {
        super.onResume()
        if (!isTimerRunning) {
            startTimer(pausedTimeInMillis)
        }
    }
    private fun showRecentlyScreen() {
        timer.cancel() // 타이머를 취소
        saveResult(success) // 현재의 성공 여부를 저장
        val intent = Intent(this, PracticeRecentlyActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun startTimer(startTimeInMillis: Long = remainingTimeInMillis) {
        timer = object : CountDownTimer(startTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                findViewById<TextView>(R.id.timerTextView).text = secondsLeft.toString()
            }

            override fun onFinish() {
                if (!success) { // 성공하지 않았을 때만 실패로 저장
                    findViewById<TextView>(R.id.timerTextView).text = "0"
                    saveResult(false) // 실패 결과 저장
                }
                // 타이머가 종료되면 자동으로 실패 처리됨
                showRecentlyScreen()
            }
        }

        // 문제보기 클릭 시 다이얼로그 띄우기
        findViewById<TextView>(R.id.problemText).setOnClickListener {
            showProblemDialog()
        }

        timer.start() // 액티비티가 생성되면 타이머 시작
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
        numberTextView.text = "문제 5."

        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        messageTextView.text = "최근 실행한 앱을 확인해보세요."
        messageTextView.textSize = 20f // 글씨 크기 설정

        // 확인 버튼 설정
        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            alertDialog.dismiss() // 다이얼로그 닫기
            saveResult(true) // 성공 결과 저장
            startTimer(remainingTimeInMillis) // 타이머 다시 시작
        }

        alertDialog.show()

        // 다이얼로그가 나타나면 타이머 멈춤
        timer.cancel()
        isTimerRunning = false
    }

    private fun saveResult(isSuccess: Boolean) {
        val sharedPreferences = getSharedPreferences("PracticeRecentlyDefaultPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("quiz_result", isSuccess)
        editor.apply()
    }
}
