package com.sungkyul.synergy.learning_space.default_app.todo

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R

class DefaultLockedScreenActivity : AppCompatActivity() {

    private lateinit var imageView46: ImageView
    private var initialY = 0f
    private var previousY = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default_locked_screenctivity)

        imageView46 = findViewById(R.id.imageView46)

        imageView46.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    initialY = event.y
                    previousY = imageView46.y
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    val deltaY = event.y - initialY
                    imageView46.y = previousY + deltaY
                    true
                }
                MotionEvent.ACTION_UP -> {
                    val deltaY = event.y - initialY
                    if (deltaY < -50) { // 임의의 값으로 조절하여 사용자의 슬라이드 거리를 설정
                        // 사용자가 이미지뷰를 위로 슬라이드한 경우 DefaultMenuActivity로 이동
                        val intent = Intent(this@DefaultLockedScreenActivity, DefaultHomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        // 이미지뷰를 초기 위치로 되돌림
                        imageView46.animate().translationY(0f).setDuration(200).start()
                    }
                    true
                }
                else -> false
            }
        }
    }
}
