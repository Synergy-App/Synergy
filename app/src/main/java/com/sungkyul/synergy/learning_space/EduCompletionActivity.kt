package com.sungkyul.synergy.learning_space

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityEduCompletionBinding
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.learning_space.default_app.message.activity.DefaultMessageFirstActivity
import com.sungkyul.synergy.learning_space.screen_layout.ScreenFirstActivity

class EduCompletionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEduCompletionBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEduCompletionBinding.inflate(layoutInflater)
        setContentView(binding.root)

         val homeIntent = Intent(this, MainActivity::class.java)
         var replayIntent = Intent(this, MainActivity::class.java)

        replayIntent = when(intent.getStringExtra("course")) {
            "screen_layout" -> Intent(this, ScreenFirstActivity::class.java)
            "message" -> Intent(this, DefaultMessageFirstActivity::class.java)
            else -> Intent(this, MainActivity::class.java)
        }

        binding.goHomeButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    //(view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }
                MotionEvent.ACTION_UP -> {
                    //(view as GalaxyButton).startTouchUpAnimation()

                    startActivity(homeIntent)
                }
            }
            true
        }
        binding.replayButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    //(view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }
                MotionEvent.ACTION_UP -> {
                    //(view as GalaxyButton).startTouchUpAnimation()

                    startActivity(replayIntent)
                }
            }
            true
        }
    }
}