package com.sungkyul.synergy.edu_space.screen_layout

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.sungkyul.synergy.R

class ScreenHomeActivity : AppCompatActivity() {

    private var startY = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_home)

        // 하단바 숨기기 설정
        hideSystemUI()

        // 전체 레이아웃 터치 이벤트 처리
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
