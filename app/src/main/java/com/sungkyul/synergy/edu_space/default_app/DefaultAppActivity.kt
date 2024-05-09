package com.sungkyul.synergy.edu_space.default_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityDefaultAppBinding
import com.sungkyul.synergy.edu_space.default_app.camera.activity.DefaultCameraActivity
import com.sungkyul.synergy.edu_space.default_app.message.activity.DefaultMessageChattingActivity
import com.sungkyul.synergy.edu_space.default_app.phone.activity.DefaultPhoneActivity
import com.sungkyul.synergy.utils.GalaxyButton

class DefaultAppActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultAppBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.callEduBox.post { binding.callEduBox.clipToRoundRect(27.0f) }
        binding.cameraEduBox.post { binding.cameraEduBox.clipToRoundRect(27.0f) }
        binding.messageEduBox.post { binding.messageEduBox.clipToRoundRect(27.0f) }

        binding.callEduBox.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }
                MotionEvent.ACTION_UP -> {
                    (view as GalaxyButton).startTouchUpAnimation()

                    // 전화 앱으로 이동한다.
                    val intent = Intent(applicationContext, DefaultPhoneActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
        binding.cameraEduBox.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }
                MotionEvent.ACTION_UP -> {
                    (view as GalaxyButton).startTouchUpAnimation()

                    // 카메라 앱으로 이동한다.
                    val intent = Intent(applicationContext, DefaultCameraActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
        binding.messageEduBox.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }
                MotionEvent.ACTION_UP -> {
                    (view as GalaxyButton).startTouchUpAnimation()

                    // 메시지 앱으로 이동한다.
                    val intent = Intent(applicationContext, DefaultMessageChattingActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }
}
