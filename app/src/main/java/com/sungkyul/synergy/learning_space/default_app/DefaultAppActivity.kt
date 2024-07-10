package com.sungkyul.synergy.learning_space.default_app

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityDefaultAppBinding
import com.sungkyul.synergy.learning_space.default_app.camera.activity.DefaultCameraActivity
import com.sungkyul.synergy.learning_space.default_app.message.activity.DefaultMessageChattingActivity
import com.sungkyul.synergy.learning_space.default_app.phone.activity.DefaultPhoneActivity
import com.sungkyul.synergy.learning_space.default_app.gallery.activity.DefaultGalleryActivity
import com.sungkyul.synergy.utils.GalaxyButton

class DefaultAppActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultAppBinding
    private var standardSizeX: Int = 0
    private var standardSizeY: Int = 0
    private var density: Float = 0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 해상도와 density를 기준으로 표준 크기를 계산합니다.
        getStandardSize()

        // 기기별 해상도를 기준으로 글씨 크기를 조절합니다.
        binding.iconeduTool.textSize = (standardSizeX / 12).toFloat()
        binding.iconeduTool2.textSize = (standardSizeX / 20).toFloat()

        binding.callEduBox.post { binding.callEduBox.clipToRoundRect(27.0f) }
        binding.cameraEduBox.post { binding.cameraEduBox.clipToRoundRect(27.0f) }
        binding.messageEduBox.post { binding.messageEduBox.clipToRoundRect(27.0f) }
        binding.galleryEduBox.post { binding.galleryEduBox.clipToRoundRect(27.0f) }

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
        binding.galleryEduBox.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }
                MotionEvent.ACTION_UP -> {
                    (view as GalaxyButton).startTouchUpAnimation()

                    // 앨범 앱으로 이동한다.
                    val intent = Intent(applicationContext, DefaultGalleryActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }

    private fun getScreenSize(): Point {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size
    }

    private fun getStandardSize() {
        val screenSize = getScreenSize()
        density = resources.displayMetrics.density

        standardSizeX = (screenSize.x / density).toInt()
        standardSizeY = (screenSize.y / density).toInt()
    }
}
