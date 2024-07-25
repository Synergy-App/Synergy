package com.sungkyul.synergy.training_space.default_app.camera

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
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeCameraBinding
import com.sungkyul.synergy.learning_space.default_app.TOUCH_DOWN_ALPHA
import com.sungkyul.synergy.learning_space.default_app.TOUCH_DURATION_ALPHA
import com.sungkyul.synergy.learning_space.default_app.TOUCH_UP_ALPHA
import com.sungkyul.synergy.utils.AnimUtils

class PracticeCameraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeCameraBinding
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 30000
    private var pausedTimeInMillis: Long = 0 // 타이머가 일시정지된 시간
    private var success: Boolean = false // 성공 여부를 나타내는 변수 추가



    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityPracticeCameraBinding.inflate(layoutInflater)
            setContentView(binding.root)

            onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val intent = Intent(binding.root.context, PracticeCamera2Activity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    startActivity(intent)
                }
            })

            // 버튼의 배경 알파 값 초기화
            binding.settingsButton.background.alpha = TOUCH_UP_ALPHA
            binding.flashButton.background.alpha = TOUCH_UP_ALPHA
            binding.timerButton.background.alpha = TOUCH_UP_ALPHA
            binding.ratioButton.background.alpha = TOUCH_UP_ALPHA
            binding.motionPhotoButton.background.alpha = TOUCH_UP_ALPHA
            binding.filterButton.background.alpha = TOUCH_UP_ALPHA
            binding.galleryButton.background.alpha = TOUCH_UP_ALPHA
            binding.cameraToggleButton.background.alpha = TOUCH_UP_ALPHA

            // 버튼의 이벤트 리스너 연결
            binding.settingsButton.setOnTouchListener(onTouchSettingsListener)
            binding.flashButton.setOnTouchListener(onTouchFlashListener)
            binding.timerButton.setOnTouchListener(onTouchTimerListener)
            binding.galleryButton.setOnTouchListener(onTouchGalleryListener)
            binding.shootingButton.setOnTouchListener(onTouchShootingListener)
            binding.cameraToggleButton.setOnTouchListener(onTouchCameraToggleListener)
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
        val intent = Intent(this, PracticeCamera2Activity::class.java)
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
        numberTextView.text = "문제 1."

        val messageTextView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        messageTextView.text = "후면카메라로 사진을 찍고 사진을 확인해보세요."
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
        // 일단 임시로 저장하겠습니다잉.!!!! TODO : !!!!!!!!!!!!!
        val sharedPreferences = getSharedPreferences("000", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("camera_quiz_result", isSuccess)
        editor.apply()
    }

// 설정 버튼의 터치 이벤트 리스너
        private val onTouchSettingsListener = View.OnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                }
                MotionEvent.ACTION_UP -> {
                    AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                    view.performClick()
                }
            }
            true
        }

        // 플래시 버튼의 터치 이벤트 리스너
        private val onTouchFlashListener = View.OnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                }
                MotionEvent.ACTION_UP -> {
                    AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                    view.performClick()
                }
            }
            true
        }

     private val onTouchTimerListener = View.OnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                }
                MotionEvent.ACTION_UP -> {
                    AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                    view.performClick()
                }
            }
            true
        }

        // 갤러리 버튼의 터치 이벤트 리스너
        private val onTouchGalleryListener = View.OnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                }
                MotionEvent.ACTION_UP -> {
                    AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)

                    // 갤러리 뷰로 이동한다.
                    val intent = Intent(this, PracticeCamera2Activity::class.java)
                    startActivity(intent)

                    view.performClick()
                }
            }
            true
        }

        // 촬영 버튼의 터치 이벤트 리스너
        private val onTouchShootingListener = View.OnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    view.background = ContextCompat.getDrawable(applicationContext, R.drawable.camera_grey_circle)
                }
                MotionEvent.ACTION_UP -> {
                    view.background = ContextCompat.getDrawable(applicationContext, R.drawable.white_circle)

                    AnimUtils.startShootingAnimation(this, binding.cameraScreen)

                    binding.eduScreen.onAction("click_shooting_button")

                    view.performClick()
                }
            }
            true
        }

        // 카메라 토글 버튼의 터치 이벤트 리스너
        private val onTouchCameraToggleListener = View.OnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
                }
                MotionEvent.ACTION_UP -> {
                    AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                    view.performClick()
                }
            }
            true
        }
    }
