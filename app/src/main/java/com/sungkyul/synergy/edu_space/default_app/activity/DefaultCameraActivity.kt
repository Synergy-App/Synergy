package com.sungkyul.synergy.edu_space.default_app.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityDefaultCameraBinding
import com.sungkyul.synergy.edu_space.default_app.TOUCH_DOWN_ALPHA
import com.sungkyul.synergy.edu_space.default_app.TOUCH_DURATION_ALPHA
import com.sungkyul.synergy.edu_space.default_app.TOUCH_UP_ALPHA
import com.sungkyul.synergy.utils.AnimUtil

class DefaultCameraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultCameraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 버튼의 배경 알파 값 초기화
        binding.settingsButton.background.alpha = TOUCH_UP_ALPHA
        binding.flashButton.background.alpha = TOUCH_UP_ALPHA
        binding.timerButton.background.alpha = TOUCH_UP_ALPHA
        binding.ratioButton.background.alpha = TOUCH_UP_ALPHA
        binding.motionPhotoButton.background.alpha = TOUCH_UP_ALPHA
        binding.filterButton.background.alpha = TOUCH_UP_ALPHA
        binding.galleryButton.background.alpha = TOUCH_UP_ALPHA
        binding.cameraToggleButton.background.alpha = TOUCH_UP_ALPHA

        // 버튼의 이벤트 리스너 연결
        binding.settingsButton.setOnTouchListener(onTouchSettingsListener)
        binding.flashButton.setOnTouchListener(onTouchFlashListener)
        binding.timerButton.setOnTouchListener(onTouchTimerListener)
        binding.ratioButton.setOnTouchListener(onTouchRatioListener)
        binding.motionPhotoButton.setOnTouchListener(onTouchMotionPhotoListener)
        binding.filterButton.setOnTouchListener(onTouchFilterListener)
        binding.galleryButton.setOnTouchListener(onTouchGalleryListener)
        binding.shootingButton.setOnTouchListener(onTouchShootingListener)
        binding.cameraToggleButton.setOnTouchListener(onTouchCameraToggleListener)
    }

    // 설정 버튼의 터치 이벤트 리스너
    private val onTouchSettingsListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 플래시 버튼의 터치 이벤트 리스너
    private val onTouchFlashListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 타이머 버튼의 터치 이벤트 리스너
    private val onTouchTimerListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 비율 버튼의 터치 이벤트 리스너
    private val onTouchRatioListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 모션 포토 버튼의 터치 이벤트 리스너
    private val onTouchMotionPhotoListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 필터 버튼의 터치 이벤트 리스너
    private val onTouchFilterListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 갤러리 버튼의 터치 이벤트 리스너
    private val onTouchGalleryListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)

                // 갤러리 뷰로 이동
                val intent = Intent(this, DefaultCameraGalleryViewActivity::class.java)
                startActivity(intent)

                view.performClick()
            }
        }
        true
    }

    // 촬영 버튼의 터치 이벤트 리스너
    private val onTouchShootingListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                view.background = ContextCompat.getDrawable(applicationContext, R.drawable.camera_grey_circle)
            }
            MotionEvent.ACTION_UP -> {
                view.background = ContextCompat.getDrawable(applicationContext, R.drawable.white_circle)

                // 찰칵하는 애니메이션
                AnimUtil.startAlphaAnimation2(binding.cameraScreen.drawable, 25, 255, 0)

                view.performClick()
            }
        }
        true
    }

    // 카메라 토글 버튼의 터치 이벤트 리스너
    private val onTouchCameraToggleListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }
}
