package com.sungkyul.synergy.learning_space.screen

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.utils.GALAXY_NOTE9
import com.sungkyul.synergy.learning_space.activity.ExampleProblem5Activity

class PracticeAppMove2Activity : AppCompatActivity() {
    private lateinit var rootLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_app_move2)
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
                    true

                }

                else -> false
            }

        }
        Handler().postDelayed({
            val intent = Intent(this@PracticeAppMove2Activity, ExampleProblem5Activity::class.java)
            startActivity(intent)
        }, 3000)

        if (Build.MODEL == GALAXY_NOTE9) {
            findViewById<TextView>(R.id.timerTextView).textSize = 18.0f
            findViewById<TextView>(R.id.problemText).textSize = 18.0f
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