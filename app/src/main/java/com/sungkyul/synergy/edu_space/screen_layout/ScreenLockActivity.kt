package com.sungkyul.synergy.edu_space.screen_layout

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.edu_courses.screen_layout.ScreenLockCourse
import com.sungkyul.synergy.databinding.ActivityScreenLockBinding
import com.sungkyul.synergy.utils.edu.EduScreen

class ScreenLockActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScreenLockBinding
    private var startY = 0f
    private lateinit var lockIcon: ImageView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenLockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            // 교육 코스를 지정한다.
            binding.eduScreen.course = ScreenLockCourse(binding.eduScreen)

            // 교육 코스가 끝났을 때 발생하는 이벤트 리스너를 설정한다.
            binding.eduScreen.setOnFinishedCourseListener {
                EduScreen.toTop(this, MainActivity::class.java)
            }

            // 교육을 시작한다.
            binding.eduScreen.start(this)
        }

        // 스마트폰의 이전 버튼을 누르면, 지정된 액티비티로 되돌아간다.
        EduScreen.navigateBackToTop(this, MainActivity::class.java)

        // 하단바 숨기기 설정
        hideSystemUI()

        lockIcon = findViewById(R.id.lock_icon)

        lockIcon.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startY = event.rawY
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    val endY = event.rawY
                    if (Math.abs(endY - startY) > lockIcon.height / 2) { // 드래그가 아이콘 크기의 절반 이상일 때
                        showHomeScreen()
                        true
                    } else {
                        false
                    }
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    // 터치 이벤트가 끝날 때
                    startY = 0f
                    false
                }
                else -> false
            }
        }
    }

    private fun showHomeScreen() {
        val intent = Intent(this, ScreenHomeActivity::class.java)
        val options = ActivityOptions.makeCustomAnimation(this, R.anim.scale_up_center, R.anim.fade_out)
        startActivity(intent, options.toBundle())
    }

    private fun hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.let { controller ->
                controller.hide(WindowInsets.Type.systemBars())
                controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
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
}
