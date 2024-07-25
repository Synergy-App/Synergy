package com.sungkyul.synergy.learning_space.screen_layout

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.courses.screen_layout.ScreenHomeCourse
import com.sungkyul.synergy.com.sungkyul.synergy.edu_courses.screen_layout.ScreenHomeCourse2
import com.sungkyul.synergy.com.sungkyul.synergy.edu_courses.screen_layout.ScreenHomeCourse3
import com.sungkyul.synergy.com.sungkyul.synergy.edu_courses.screen_layout.ScreenHomeCourse4
import com.sungkyul.synergy.com.sungkyul.synergy.edu_courses.screen_layout.ScreenHomeCourse5
import com.sungkyul.synergy.com.sungkyul.synergy.edu_courses.screen_layout.ScreenHomeCourse6
import com.sungkyul.synergy.databinding.ActivityScreenHomeBinding
import com.sungkyul.synergy.learning_space.EduCompletionActivity
import com.sungkyul.synergy.utils.edu.EduScreen

class ScreenHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScreenHomeBinding
    private var startY = 0f
    private var dragged = false
    private var draggedToolbar = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            Log.i("lock error log", "교육 정의 시작")

            // 교육 코스를 지정한다.
            if(intent.getStringExtra("from") == "ScreenMoveHomeActivity") {
                binding.eduScreen.course = ScreenHomeCourse2(binding.eduScreen)
            } else if(intent.getStringExtra("from") == "ScreenTopBarActivity") {
                binding.eduScreen.course = ScreenHomeCourse3(binding.eduScreen)
            } else if(intent.getStringExtra("from") == "ScreenRecentlyActivity") {
                binding.eduScreen.course = ScreenHomeCourse4(binding.eduScreen)
            }
            else if(intent.getStringExtra("from") == "NaverActivity") {
                binding.eduScreen.course = ScreenHomeCourse5(binding.eduScreen)
            }
            else if(intent.getStringExtra("from") == "NaverActivity2") {
                binding.eduScreen.course = ScreenHomeCourse6(binding.eduScreen)
            }
            else {
                binding.eduScreen.course = ScreenHomeCourse(binding.eduScreen)
            }

            Log.i("lock error log", "코스 지정")

            // 교육 코스가 끝났을 때 발생하는 이벤트 리스너를 설정한다.
            binding.eduScreen.setOnFinishedCourseListener {
                //EduScreen.toTop(this, MainActivity::class.java)

            }

            // 교육을 시작한다.
            binding.eduScreen.start(this)

            Log.i("lock error log", "교육 시작")
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
                    dragged = false
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    if (!dragged && startY - event.y > 100) {
                        if(binding.eduScreen.onAction("show_menu_screen")) {
                            if(intent.getStringExtra("from") == "ScreenRecentlyActivity") {
                                val intent = Intent(this, ScreenMenuActivity::class.java)
                                intent.putExtra("from", "ScreenHomeActivity4")
                                startActivity(intent)
                            }
                            else if(intent.getStringExtra("from") == "NaverActivity") {
                                val intent = Intent(this, ScreenMenuActivity::class.java)
                                Log.i("네이버 첫번째 입성 후 돌아오기", "true")
                                intent.putExtra("from", "ScreenHomeActivity5")
                                startActivity(intent)
                            }
                            else {
                                showMenuScreen()
                            }
                        }
                        dragged = true
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
                    draggedToolbar = false
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    if (!draggedToolbar && event.y - startY > 100) { // 아래로 드래그 거리 설정 (100px 이상 드래그 시)
                        if(binding.eduScreen.onAction("show_topbar_screen")) {
                            Log.i("show_topbar_screen", "true")
                            showTopbarScreen()
                        }
                        draggedToolbar = true
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
        val options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_up, R.anim.stay)
        startActivity(intent, options.toBundle())
    }

    private fun showTopbarScreen() {
        val intent = Intent(this, ScreenTopBarActivity::class.java)
        val options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_down, R.anim.stay)
        startActivity(intent, options.toBundle())
    }

    private fun showRecentlyScreen() {
        val intent = Intent(this, ScreenRecentlyActivity::class.java)
        val options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_in_left, R.anim.stay)
        startActivity(intent, options.toBundle())
        startActivity(intent)
    }

    private fun returnToHomeScreen() {
        val intent = Intent(this, ScreenHomeActivity::class.java)
        val options = ActivityOptions.makeCustomAnimation(this, R.anim.stay, R.anim.stay)
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
