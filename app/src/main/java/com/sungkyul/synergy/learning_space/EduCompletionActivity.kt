package com.sungkyul.synergy.learning_space

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityEduCompletionBinding
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.utils.GalaxyButton

class EduCompletionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEduCompletionBinding
    private val homeIntent = Intent(this, MainActivity::class.java)
    private var replayIntent = Intent(this, MainActivity::class.java)

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEduCompletionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when(intent.getStringExtra("from")) {
            else -> replayIntent = Intent(this, MainActivity::class.java)
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