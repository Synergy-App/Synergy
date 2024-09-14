package com.sungkyul.synergy.training_space.naver

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.preference.PreferenceManager
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.courses.naver.NaverCourse
import com.sungkyul.synergy.courses.naver.NaverFromNaverSearchInfoCourse
import com.sungkyul.synergy.courses.screen_layout.NaverFromScreenHomeCourse
import com.sungkyul.synergy.courses.screen_layout.NaverFromScreenHomeCourse2
import com.sungkyul.synergy.databinding.ActivityNaverBinding
import com.sungkyul.synergy.databinding.ActivityPracticeGoogle9Binding
import com.sungkyul.synergy.databinding.ActivityPracticeNaverBinding
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.learning_space.EduCompletionActivity
import com.sungkyul.synergy.learning_space.naver.activity.NaverSearchActivity
import com.sungkyul.synergy.learning_space.naver.activity.NaverSearchInfoActivity
import com.sungkyul.synergy.learning_space.naver.adapter.NaverPostAdapter
import com.sungkyul.synergy.learning_space.naver.adapter.NaverPostData
import com.sungkyul.synergy.learning_space.screen_layout.ScreenHomeActivity
import com.sungkyul.synergy.training_space.call.problem.ExamCallProblem2Activity
import com.sungkyul.synergy.training_space.call.problem.ExamNaverProblemActivity
import com.sungkyul.synergy.training_space.google.PracticeGoogle2Activity
import com.sungkyul.synergy.utils.AnimUtils
import com.sungkyul.synergy.utils.edu.EduScreen

class PracticeNaverActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeNaverBinding
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 70000
    private var pausedTimeInMillis: Long = 0
    private var success: Boolean = false

    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeNaverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        hideSystemUI()
        startTimer()

        binding.problemText.setOnClickListener { showProblemDialog() }

        // 검색 바 클릭 시
        binding.searchBar.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    val intent = Intent(this, PracticeNaver2Activity::class.java)
                    intent.putExtra("remainingTimeInMillis", remainingTimeInMillis)
                    startActivity(intent)
                    view.performClick()
                }
            }
            true
        }

        // 버튼 클릭 시 애니메이션
        setupButtonTouchListeners()

        // 타이머 초기화
        initializeTimer()
    }

    private fun setupButtonTouchListeners() {
        val buttons = listOf(
            binding.shoppingButton,
            binding.homeButton,
            binding.contentsButton,
            binding.clipButton
        )

        for (button in buttons) {
            button.setOnTouchListener { view, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        AnimUtils.startTouchDownSpringButtonAnimation(view)
                    }

                    MotionEvent.ACTION_UP -> {
                        AnimUtils.startTouchUpSpringButtonAnimation(view)
                        view.performClick()
                    }
                }
                true
            }
        }
    }

    private fun initializeTimer() {
        timer = object : CountDownTimer(remainingTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                binding.timerTextView.text = secondsLeft.toString()
            }

            override fun onFinish() {
                binding.timerTextView.text = "0"
                saveResult(false)
            }
        }

        timer.start()
        isTimerRunning = true
    }

    private fun saveResult(isSuccess: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("practice_naver_result", isSuccess)
        editor.apply()
    }

    override fun onPause() {
        super.onPause()
        pausedTimeInMillis = remainingTimeInMillis
        if (isTimerRunning) {
            timer.cancel()
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
                binding.timerTextView.text = secondsLeft.toString()
            }

            override fun onFinish() {
                binding.timerTextView.text = "0"
                if (!success) { // 성공하지 않았을 때만 실패로 저장
                    saveResult(false)
                }
            }
        }

        timer.start()
        isTimerRunning = true
    }

    private fun showProblemDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialoglayout, null)
        dialogBuilder.setView(dialogView)

        val alertDialog = dialogBuilder.create()

        val numberTextView = dialogView.findViewById<TextView>(R.id.dialogNumber)
        numberTextView.text = "문제 1."

        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        messageTextView.text = "된장찌개 만드는 방법을 검색창에 입력하시오."
        messageTextView.textSize = 20f

        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            alertDialog.dismiss() // 다이얼로그 닫기
            success = true // 성공으로 표시
            saveResult(true) // 성공으로 저장
        }

        alertDialog.show()

        // 타이머 멈춤
        timer.cancel()
        isTimerRunning = false
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
    }
}
