package com.sungkyul.synergy.learning_space.default_app.camera.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.sungkyul.synergy.R
import com.sungkyul.synergy.courses.default_app.camera.DefaultCameraGalleryViewCourse
import com.sungkyul.synergy.courses.default_app.camera.DefaultCameraGalleryViewWithBehindPicCourse
import com.sungkyul.synergy.databinding.ActivityDefaultCameraGalleryViewBinding
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.learning_space.EduCompletionActivity
import com.sungkyul.synergy.learning_space.default_app.TOUCH_DOWN_ALPHA
import com.sungkyul.synergy.learning_space.default_app.TOUCH_DURATION_ALPHA
import com.sungkyul.synergy.learning_space.default_app.TOUCH_UP_ALPHA
import com.sungkyul.synergy.learning_space.default_app.gallery.activity.DefaultGalleryActivity
import com.sungkyul.synergy.utils.AnimUtils

class DefaultCameraGalleryViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultCameraGalleryViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultCameraGalleryViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            if(intent.getStringExtra("from")=="DefaultCameraWithFrontPic"){
                binding.eduScreen.course = DefaultCameraGalleryViewWithBehindPicCourse(binding.eduScreen)
                binding.galleryScreen.setImageResource(R.drawable.behind_picture)
            } else{
                binding.eduScreen.course = DefaultCameraGalleryViewCourse(binding.eduScreen)
            }


            binding.eduScreen.setOnFinishedCourseListener {
                // 교육 코스가 끝났을 때 어떻게 할지 처리하는 곳이다.

                if(binding.eduScreen.course is DefaultCameraGalleryViewWithBehindPicCourse){
                    val intent = Intent(binding.root.context, EduCompletionActivity::class.java)
                    intent.putExtra("course", "camera")
                    startActivity(intent)
                }
                else{
                    // MAinActivity로 되돌아 간다.
                    val intent = Intent(binding.root.context, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    startActivity(intent)
                }
            }
            // 교육을 시작한다.
            binding.eduScreen.start(this)
        }

        // 버튼의 배경 알파 값 초기화
        binding.backButton.background.alpha = TOUCH_UP_ALPHA
        binding.bixbyVisionButton.background.alpha = TOUCH_UP_ALPHA
        binding.moreButton.background.alpha = TOUCH_UP_ALPHA
        binding.favoriteButton.background.alpha = TOUCH_UP_ALPHA
        binding.editButton.background.alpha = TOUCH_UP_ALPHA
        binding.shareButton.background.alpha = TOUCH_UP_ALPHA
        binding.deleteButton.background.alpha = TOUCH_UP_ALPHA

        // 버튼의 이벤트 리스너 연결
        binding.backButton.setOnTouchListener(onTouchBackListener)
        binding.bixbyVisionButton.setOnTouchListener(onTouchBixbyVisionListener)
        binding.moreButton.setOnTouchListener(onTouchMoreListener)
        binding.favoriteButton.setOnTouchListener(onTouchFavoriteListener)
        binding.editButton.setOnTouchListener(onTouchEditListener)
        binding.shareButton.setOnTouchListener(onTouchShareListener)
        binding.deleteButton.setOnTouchListener(onTouchDeleteListener)
    }

    // 이전 버튼의 터치 이벤트 리스너
    private val onTouchBackListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)

                if(binding.eduScreen.onAction("click_back_button")){
                    val intent = Intent(binding.root.context, DefaultGalleryActivity::class.java)
                    startActivity(intent)
                }

                view.performClick()
            }
        }
        true
    }

    // 빅스비 비전 버튼의 터치 이벤트 리스너
    private val onTouchBixbyVisionListener = View.OnTouchListener { view, event ->
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

    // 더보기 버튼의 터치 이벤트 리스너
    private val onTouchMoreListener = View.OnTouchListener { view, event ->
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

    // 즐겨찾기 버튼의 터치 이벤트 리스너
    private val onTouchFavoriteListener = View.OnTouchListener { view, event ->
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

    // 편집 버튼의 터치 이벤트 리스너
    private val onTouchEditListener = View.OnTouchListener { view, event ->
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

    // 공유 버튼의 터치 이벤트 리스너
    private val onTouchShareListener = View.OnTouchListener { view, event ->
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

    // 삭제 버튼의 터치 이벤트 리스너
    private val onTouchDeleteListener = View.OnTouchListener { view, event ->
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
}
