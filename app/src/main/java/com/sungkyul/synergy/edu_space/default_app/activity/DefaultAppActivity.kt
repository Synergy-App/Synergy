package com.sungkyul.synergy.edu_space.default_app.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityDefaultAppBinding

class DefaultAppActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultAppBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 각 버튼의 터치 리스너를 설정한다.
        binding.callEduBox.setOnTouchListener(onTouchCallEduButtonListener)
        binding.cameraEduBox.setOnTouchListener(onTouchCameraEduButtonListener)
        binding.messageEduBox.setOnTouchListener(onTouchMessageEduButtonListener)
    }

    private val onTouchCallEduButtonListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
            }
            MotionEvent.ACTION_UP -> {
                // 전화 앱으로 이동한다.
                val intent = Intent(applicationContext, DefaultPhoneActivity::class.java)
                startActivity(intent)

                view.performClick()
            }
        }
        true
    }

    private val onTouchCameraEduButtonListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
            }
            MotionEvent.ACTION_UP -> {
                // 카메라 앱으로 이동한다.
                val intent = Intent(applicationContext, DefaultCameraActivity::class.java)
                startActivity(intent)

                view.performClick()
            }
        }
        true
    }

    private val onTouchMessageEduButtonListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
            }
            MotionEvent.ACTION_UP -> {
                // 메시지 앱으로 이동한다.
                val intent = Intent(applicationContext, DefaultMessageActivity::class.java)
                startActivity(intent)

                view.performClick()
            }
        }
        true
    }
}
