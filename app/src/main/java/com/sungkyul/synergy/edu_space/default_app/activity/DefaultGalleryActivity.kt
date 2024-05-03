package com.sungkyul.synergy.edu_space.default_app.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.databinding.ActivityDefaultGalleryBinding
import com.sungkyul.synergy.edu_space.default_app.fragment.DefaultGalleryAlbumFragment
import com.sungkyul.synergy.edu_space.default_app.fragment.DefaultGalleryPictureFragment
import com.sungkyul.synergy.edu_space.default_app.fragment.DefaultGalleryShareFragment
import com.sungkyul.synergy.edu_space.default_app.fragment.DefaultGalleryStoryFragment
import com.sungkyul.synergy.utils.AnimUtils
import com.sungkyul.synergy.utils.DynamicButton

class DefaultGalleryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultGalleryBinding
    private lateinit var pictureFragment: Fragment
    private lateinit var albumFragment: Fragment
    private lateinit var storyFragment: Fragment
    private lateinit var shareFragment: Fragment

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 프래그먼트들을 초기화한다.
        pictureFragment = DefaultGalleryPictureFragment(binding.eduScreen)
        albumFragment = DefaultGalleryAlbumFragment(binding.eduScreen)
        storyFragment = DefaultGalleryStoryFragment(binding.eduScreen)
        shareFragment = DefaultGalleryShareFragment(binding.eduScreen)

        // 각 버튼의 인터랙션을 초기화한다.
        AnimUtils.initTouchButtonAnimation(binding.groupingSimilarImagesButton)
        AnimUtils.initTouchButtonAnimation(binding.makingMovieButton)
        AnimUtils.initTouchButtonAnimation(binding.searchButton)
        AnimUtils.initTouchButtonAnimation(binding.moreButton)
        AnimUtils.initTouchButtonAnimation(binding.pictureButton)
        AnimUtils.initTouchButtonAnimation(binding.albumButton)
        AnimUtils.initTouchButtonAnimation(binding.storyButton)
        AnimUtils.initTouchButtonAnimation(binding.shareButton)

        // 이벤트 리스너들을 추가한다.
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
        binding.pictureButton.setOnTouchListener { view, event ->
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
        binding.albumButton.setOnTouchListener { view, event ->
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
        binding.storyButton.setOnTouchListener { view, event ->
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
        binding.shareButton.setOnTouchListener { view, event ->
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

        replaceFragment(pictureFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, fragment)
            .commit()
    }
}
