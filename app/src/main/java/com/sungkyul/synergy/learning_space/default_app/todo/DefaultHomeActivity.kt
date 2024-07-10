package com.sungkyul.synergy.learning_space.default_app.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.sungkyul.synergy.R
import android.view.MotionEvent


class DefaultHomeActivity : AppCompatActivity() {

    private var initialY = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default_menu)

        val rootLayout = findViewById<ConstraintLayout>(R.id.rootLayout)

        rootLayout.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    initialY = event.y
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    val deltaY = event.y - initialY
                    if (deltaY < -20) { // 임의의 값으로 조절하여 사용자의 슬라이드 거리를 설정
                        // 사용자가 화면을 위로 슬라이드한 경우 DefaultMenuActivity로 이동
                        val intent = Intent(this@DefaultHomeActivity, DefaultMenuActivity::class.java)
                        startActivity(intent)
                    }
                    true
                }
                else -> false
            }
        }
    }
}
