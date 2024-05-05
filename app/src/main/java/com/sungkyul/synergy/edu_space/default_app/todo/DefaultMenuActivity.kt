package com.sungkyul.synergy.edu_space.default_app.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.sungkyul.synergy.R
import android.view.View


class DefaultMenuActivity : AppCompatActivity() {

    private var initialX = 0f
    private var initialY = 0f
    private var offsetX = 0f
    private var offsetY = 0f
    private lateinit var linearLayout1: LinearLayout // LinearLayout 변수 추가
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default_menu2)

        val rootLayout = findViewById<ConstraintLayout>(R.id.rootLayout)
        val imageView50 = findViewById<ImageView>(R.id.imageView50)
        val imageView51 = findViewById<ImageView>(R.id.imageView51)
        linearLayout1 = findViewById(R.id.linearLayout1) // 변수 초기화



        imageView51.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // 뷰를 터치한 초기 위치 기록
                    initialX = event.rawX
                    initialY = event.rawY
                    // 터치 지점에서 이미지의 왼쪽 상단 모서리까지의 거리 계산
                    offsetX = event.rawX - view.x
                    offsetY = event.rawY - view.y

                }
                MotionEvent.ACTION_MOVE -> {
                    // 이동한 위치 계산
                    val newX = event.rawX - offsetX
                    val newY = event.rawY - offsetY
                    // 뷰를 새 위치로 이동
                    view.animate()
                        .x(newX)
                        .y(newY)
                        .setDuration(0)
                        .start()
                    rootLayout.removeView(imageView50)
                    linearLayout1.visibility = View.VISIBLE

                }
                MotionEvent.ACTION_UP -> {
                    // 터치가 끝나면 아무것도 하지 않음
                }
            }
            true
        }
    }
}
