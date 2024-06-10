package com.sungkyul.synergy.edu_space.screen_layout

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.sungkyul.synergy.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.edu_courses.screen_layout.ScreenHomeCourse
import com.sungkyul.synergy.databinding.ActivityScreenHomeBinding
import com.sungkyul.synergy.utils.edu.EduScreen

class ScreenHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScreenHomeBinding
    private var startY = 0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            // 교육 코스를 지정한다.
            binding.eduScreen.course = ScreenHomeCourse(binding.eduScreen)

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

        // 전체 레이아웃 터치 이벤트 처리
        binding.constraintLayout.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startY = event.y
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    if (startY - event.y > 100) { // 위로 드래그 거리 설정 (100px 이상 드래그 시)
                        if(binding.eduScreen.onAction("show_menu_screen")) {
                            showMenuScreen()
                        }
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
                        if(binding.eduScreen.onAction("show_topbar_screen")) {
                            showTopbarScreen()
                        }
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
                    if(binding.eduScreen.onAction("show_recently_screen")) {
                        showRecentlyScreen()
                    }
                    true
                }
                else -> false
            }
        }

        findViewById<View>(R.id.transparent_view_2).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> true
                MotionEvent.ACTION_UP -> {
                    if(binding.eduScreen.onAction("return_to_home_screen")) {
                        returnToHomeScreen()
                    }
                    true
                }
                else -> false
            }
        }

        findViewById<View>(R.id.transparent_view_3).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> true
                MotionEvent.ACTION_UP -> {
                    if(binding.eduScreen.onAction("on_back_pressed")) {
                        onBackPressed()
                    }
                    true
                }
                else -> false
            }
        }
    }

    private fun showMenuScreen() {
        val intent = Intent(this, ScreenMenuActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_up, R.anim.stay)
    }

    private fun showTopbarScreen() {
        val intent = Intent(this, ScreenTopBarActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_down, R.anim.stay)
    }

    private fun showRecentlyScreen() {
        val intent = Intent(this, ScreenRecentlyActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay)
    }

    private fun returnToHomeScreen() {
        val intent = Intent(this, ScreenHomeActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.stay, R.anim.stay)
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
