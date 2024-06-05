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

        findViewById<ConstraintLayout>(R.id.constraint_layout).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startY = event.y
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    if (event.y - startY > 100) { // 드래그 거리 설정 (100px 이상 드래그 시)
                        showTopbarScreen()
                        true
                    } else {
                        false
                    }
                }
                else -> false
            }
        }
    }

    private fun showTopbarScreen() {
        val intent = Intent(this, ScreenTopBarActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_down, R.anim.stay)
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
