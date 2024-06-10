package com.sungkyul.synergy.edu_space.screen_layout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.sungkyul.synergy.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.edu_courses.screen_layout.ScreenMoveHomeCourse
import com.sungkyul.synergy.databinding.ActivityScreenLayoutBinding
import com.sungkyul.synergy.databinding.ActivityScreenMoveHomeBinding
import com.sungkyul.synergy.utils.edu.EduScreen

class ScreenMoveHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScreenMoveHomeBinding
    private lateinit var rootLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenMoveHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            // 교육 코스를 지정한다.
            binding.eduScreen.course = ScreenMoveHomeCourse(binding.eduScreen)

            // 교육 코스가 끝났을 때 발생하는 이벤트 리스너를 설정한다.
            binding.eduScreen.setOnFinishedCourseListener {
                val intent = Intent(this, ScreenHomeActivity::class.java)
                intent.putExtra("from", "ScreenMoveHomeActivity")
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }

            // 교육을 시작한다.
            binding.eduScreen.start(this)
        }

        // 스마트폰의 이전 버튼을 누르면, 지정된 액티비티로 되돌아간다.
        EduScreen.navigateBackToTop(this, MainActivity::class.java)

        // 하단바 숨기기 설정
        hideSystemUI()

        rootLayout = findViewById(R.id.rootLayout)

        // onDragListener 추가
        rootLayout.setOnDragListener { view, event ->
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    Log.d("ScreenMoveHomeActivity", "Drag started")
                    true
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    Log.d("ScreenMoveHomeActivity", "Drag entered")
                    true
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    Log.d("ScreenMoveHomeActivity", "Drag exited")
                    true
                }
                DragEvent.ACTION_DROP -> {
                    Log.d("ScreenMoveHomeActivity", "Drop event")
                    val x = event.x
                    val y = event.y

                    // 드래그한 레이아웃 복사
                    val layout = LinearLayout(this).apply {
                        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                        orientation = LinearLayout.VERTICAL
                        gravity = android.view.Gravity.CENTER
                        tag = "Play 스토어"
                    }

                    val icon = ImageView(this).apply {
                        setImageResource(R.drawable.screen_app2)
                        layoutParams = ViewGroup.LayoutParams(60.dpToPx(), 60.dpToPx())
                    }

                    val textView = TextView(this).apply {
                        text = "Play 스토어"
                        setTextColor(resources.getColor(R.color.white))
                        textSize = 15f
                        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    }

                    layout.addView(icon)
                    layout.addView(textView)

                    layout.x = x - layout.width / 2
                    layout.y = y - layout.height / 2

                    (view as ViewGroup).addView(layout)
                    true
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    Log.d("ScreenMoveHomeActivity", "Drag ended")
                    binding.eduScreen.onAction("release_icon")
                    true
                }
                else -> false
            }
        }
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

    private fun Int.dpToPx(): Int {
        return (this * resources.displayMetrics.density).toInt()
    }
}
