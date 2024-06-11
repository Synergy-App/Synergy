package com.sungkyul.synergy.learning_space.screen

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.ClipData
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.ViewCompat
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeAppMoveBinding
import com.sungkyul.synergy.databinding.ActivityPracticeScreenLockPracticeBinding
import com.sungkyul.synergy.edu_space.screen_layout.ScreenHomeActivity
import com.sungkyul.synergy.edu_space.screen_layout.ScreenMoveHomeActivity

class PracticeAppMoveActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeAppMoveBinding
    private var startY = 0f
    private lateinit var lockIcon: ImageView
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 60000
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간

    @SuppressLint( "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeAppMoveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideSystemUI()

        // 아이콘 레이아웃에 롱클릭 리스너 추가
        binding.playstoreIcon.setOnLongClickListener { view ->
            val intent = Intent(this, PracticeAppMove2Activity::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(this, binding.playstoreIcon, "icon_transition")

            // 드래그 앤 드롭 관련 데이터 설정
            val clipData = ClipData.newPlainText("icon_tag", view.tag.toString())
            val shadowBuilder = View.DragShadowBuilder(view)
            ViewCompat.startDragAndDrop(view, clipData, shadowBuilder, null, View.DRAG_FLAG_GLOBAL)

            // startActivity를 호출하여 ScreenMoveHomeActivity로 전환
            startActivity(intent, options.toBundle())
            true
        }

        // 투명한 뷰 터치 이벤트 처리
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
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                )

        // 타이머 초기화
        timer = object : CountDownTimer(remainingTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                binding.timerTextView.text = secondsLeft.toString() // 초를 텍스트뷰에 표시
            }

            override fun onFinish() {
                binding.timerTextView.text = "0" // 타이머 종료 시 "0"으로 표시
            }
        }

        // 문제보기 클릭 시 다이얼로그 띄우기
        binding.problemText.setOnClickListener {
            showProblemDialog()
        }

        timer.start() // 액티비티가 생성되면 타이머 시작
        isTimerRunning = true
    }

    override fun onPause() {
        super.onPause()
        pausedTimeInMillis = remainingTimeInMillis
        timer.cancel() // 타이머를 취소하여 불필요한 시간 감소를 막음
        isTimerRunning = false
    }

    override fun onResume() {
        super.onResume()
        if (!isTimerRunning) {
            startTimer(pausedTimeInMillis)
        }
    }

    private fun startTimer(startTimeInMillis: Long) {
        timer = object : CountDownTimer(startTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                binding.timerTextView.text = secondsLeft.toString() // 초를 텍스트뷰에 표시
            }

            override fun onFinish() {
                binding.timerTextView.text = "0" // 타이머 종료 시 "0"으로 표시
            }
        }
        // 문제보기 클릭 시 다이얼로그 띄우기
        binding.problemText.setOnClickListener {
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
        numberTextView.text = "문제 2."

        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        messageTextView.text = "잠금화면을 풀어 홈화면으로 이동하시오."
        messageTextView.textSize = 20f // 글씨 크기 설정

        // 확인 버튼 설정
        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            alertDialog.dismiss() // 다이얼로그 닫기
            saveResult(true) // 문제 풀이 성공으로 표시
            returnToHomeScreen() // 홈 화면으로 이동
        }

        alertDialog.show()

        // 다이얼로그가 나타나면 타이머 멈춤
        timer.cancel()
        isTimerRunning = false
    }

    private fun returnToHomeScreen() {
        val intent = Intent(this, ScreenHomeActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.stay, R.anim.stay)
    }

    private fun saveResult(isSuccess: Boolean) {
        val sharedPreferences = getSharedPreferences("PracticeRecentlyDefaultPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("move_app_success", isSuccess)
        editor.apply()
    }
}
