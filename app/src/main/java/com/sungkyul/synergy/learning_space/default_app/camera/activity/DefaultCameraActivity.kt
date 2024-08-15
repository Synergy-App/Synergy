package com.sungkyul.synergy.learning_space.default_app.camera.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityDefaultCameraBinding
import com.sungkyul.synergy.courses.default_app.camera.DefaultCameraCourse
import com.sungkyul.synergy.courses.default_app.camera.DefaultCameraFromGalleryCourse
import com.sungkyul.synergy.courses.default_app.camera.DefaultCameraGalleryViewCourse
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.learning_space.default_app.TOUCH_DOWN_ALPHA
import com.sungkyul.synergy.learning_space.default_app.TOUCH_DURATION_ALPHA
import com.sungkyul.synergy.learning_space.default_app.TOUCH_UP_ALPHA

import com.sungkyul.synergy.utils.AnimUtils

class DefaultCameraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultCameraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            if(intent.getStringExtra("from")=="DefaultGalleryActivity"){
                binding.eduScreen.course = DefaultCameraFromGalleryCourse(binding.eduScreen)
            }else {
                binding.eduScreen.course = DefaultCameraCourse(binding.eduScreen)
            }

            binding.eduScreen.setOnFinishedCourseListener {
                // 교육 코스가 끝났을 때 어떻게 할지 처리하는 곳이다.

                // MAinActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
            // 교육을 시작한다.
            binding.eduScreen.start(this)
        }

        // 뒤로 가기 키를 눌렀을 때의 이벤트를 처리한다.
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // DefaultAppActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        })

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
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 플래시 버튼의 터치 이벤트 리스너
    private val onTouchFlashListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()


            }
        }
        true
    }

    // 타이머 버튼의 터치 이벤트 리스너
    private val onTouchTimerListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 비율 버튼의 터치 이벤트 리스너
    private val onTouchRatioListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 모션 포토 버튼의 터치 이벤트 리스너
    private val onTouchMotionPhotoListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 필터 버튼의 터치 이벤트 리스너
    private val onTouchFilterListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 갤러리 버튼의 터치 이벤트 리스너
    private val onTouchGalleryListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)

                if(binding.eduScreen.onAction("click_gallery_button")){
                    if(binding.eduScreen.course is DefaultCameraFromGalleryCourse){
                        val intent = Intent(binding.root.context, DefaultCameraGalleryViewActivity::class.java)
                        intent.putExtra("from", "DefaultCameraWithFrontPic")
                        startActivity(intent)
                    } else {
                        // 갤러리 뷰로 이동한다.
                        val intent = Intent(this, DefaultCameraGalleryViewActivity::class.java)
                        startActivity(intent)
                    }
                }


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

                AnimUtils.startShootingAnimation(this, binding.cameraScreen)

                if(binding.eduScreen.onAction("click_shooting_button")){

                }

                view.performClick()
            }
        }
        true
    }

    // 카메라 토글 버튼의 터치 이벤트 리스너
    private val onTouchCameraToggleListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()

                if(binding.eduScreen.onAction("click_camera_toggle_button")){
                    binding.cameraScreen.setImageResource(R.drawable.behind_picture)
                }
            }
        }
        true
    }
}
