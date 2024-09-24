package com.sungkyul.synergy.training_space.default_app.camera

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.databinding.ActivityPracticeCamera3Binding
import com.sungkyul.synergy.learning_space.default_app.gallery.activity.BottomNavItem
import com.sungkyul.synergy.learning_space.default_app.gallery.fragment.DefaultGalleryAlbumFragment
import com.sungkyul.synergy.utils.AnimUtils

class PracticeCamera3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeCamera3Binding

    private lateinit var pictureFragment: Fragment
    private lateinit var albumFragment: Fragment
    private lateinit var bottomNavItems: List<BottomNavItem>

    private val enabledColor = Color.parseColor("#252525")
    private val disabledColor = Color.parseColor("#858585")

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeCamera3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 프래그먼트들을 초기화한다.
        pictureFragment = DefaultGalleryPictureExamFragment()
        albumFragment = DefaultGalleryAlbumFragment(binding.eduScreen)

        // 하단 바의 버튼들을 리스트로 묶는다.
        bottomNavItems = listOf(
            BottomNavItem(pictureFragment, binding.pictureButtonText, binding.pictureButtonLine),
            BottomNavItem(albumFragment, binding.albumButtonText, binding.albumButtonLine),
        )

        // 각 버튼의 인터랙션을 초기화한다.
        AnimUtils.initTouchButtonAnimation(binding.groupingSimilarImagesButton)
        AnimUtils.initTouchButtonAnimation(binding.makingMovieButton)
        AnimUtils.initTouchButtonAnimation(binding.searchButton)
        AnimUtils.initTouchButtonAnimation(binding.moreButton)
        AnimUtils.initTouchButtonAnimation(binding.pictureButton)
        AnimUtils.initTouchButtonAnimation(binding.albumButton)
        AnimUtils.initTouchButtonAnimation(binding.storyButton)
        AnimUtils.initTouchButtonAnimation(binding.shareButton)

        // '비슷한 이미지 묶기' 버튼의 터치 리스너를 설정한다.
        binding.groupingSimilarImagesButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownButtonAnimation(this, view)
                }
                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpButtonAnimation(this, view)
                }
            }
            true
        }

        // '영화 만들기' 버튼의 터치 리스너를 설정한다.
        binding.makingMovieButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownButtonAnimation(this, view)
                }
                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpButtonAnimation(this, view)
                }
            }
            true
        }

        // '검색' 버튼의 터치 리스너를 설정한다.
        binding.searchButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownButtonAnimation(this, view)
                }
                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpButtonAnimation(this, view)
                }
            }
            true
        }

        // '더보기' 버튼의 터치 리스너를 설정한다.
        binding.moreButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownButtonAnimation(this, view)
                }
                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpButtonAnimation(this, view)
                }
            }
            true
        }

        // '사진' 버튼의 터치 리스너를 설정한다.
        binding.pictureButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownButtonAnimation(this, view)

                    selectBottomNavItem(pictureFragment)
                }
                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpButtonAnimation(this, view)

                    replaceFragment(pictureFragment)
                    setButtonsVisibility(pictureFragment)
                }
            }
            true
        }

        // '앨범' 버튼의 터치 리스너를 설정한다.
        binding.albumButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownButtonAnimation(this, view)

                    selectBottomNavItem(albumFragment)
                }
                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpButtonAnimation(this, view)

                    replaceFragment(albumFragment)
                    setButtonsVisibility(albumFragment)
                }
            }
            true
        }
//        // '스토리' 버튼의 터치 리스너를 설정한다.
//        binding.storyButton.setOnTouchListener { view, event ->
//            when(event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    AnimUtils.startTouchDownButtonAnimation(this, view)
//
//                    selectBottomNavItem(storyFragment)
//                }
//                MotionEvent.ACTION_UP -> {
//                    AnimUtils.startTouchUpButtonAnimation(this, view)
//
//                    replaceFragment(storyFragment)
//                    setButtonsVisibility(storyFragment)
//                }
//            }
//            true
//        }
//
//        // '공유' 버튼의 터치 리스너를 설정한다.
//        binding.shareButton.setOnTouchListener { view, event ->
//            when(event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    AnimUtils.startTouchDownButtonAnimation(this, view)
//
//                    selectBottomNavItem(shareFragment)
//                }
//                MotionEvent.ACTION_UP -> {
//                    AnimUtils.startTouchUpButtonAnimation(this, view)
//
//                    replaceFragment(shareFragment)
//                    setButtonsVisibility(shareFragment)
//                }
//            }
//            true
//        }

        selectBottomNavItem(pictureFragment)
        replaceFragment(pictureFragment)
    }

    // 프래그먼트를 교체한다.
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, fragment)
            .commit()
    }

    // 각 버튼의 보여짐 여부를 fragment에 따라 설정한다.
    private fun setButtonsVisibility(fragment: Fragment) {
        // 갤러리/사진 화면에 있을 때만 '비슷한 이미지 묶기', '영화 만들기' 버튼을 보여준다.
        if(fragment == pictureFragment) {
            binding.groupingSimilarImagesButton.visibility = ImageButton.VISIBLE
            binding.makingMovieButton.visibility = ImageButton.VISIBLE
        } else {
            binding.groupingSimilarImagesButton.visibility = ImageButton.INVISIBLE
            binding.makingMovieButton.visibility = ImageButton.INVISIBLE
        }
    }

    private fun selectBottomNavItem(fragment: Fragment) {
        // 각 아이템의 텍스트 색상과 밑줄 보여짐 여부를 변경한다.
        for(i in bottomNavItems) {
            if(i.fragment == fragment) {
                i.text.setTextColor(enabledColor)
                i.line.visibility = ImageView.VISIBLE
            } else {
                i.text.setTextColor(disabledColor)
                i.line.visibility = ImageView.INVISIBLE
            }
        }
    }
    private fun saveResult(isSuccess: Boolean) {
        val sharedPreferences = getSharedPreferences("PracticeCameraPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("camera_apple_result", isSuccess)
        editor.apply()
    }
}