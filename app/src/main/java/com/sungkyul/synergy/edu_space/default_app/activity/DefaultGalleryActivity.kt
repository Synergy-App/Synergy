package com.sungkyul.synergy.edu_space.default_app.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.databinding.ActivityDefaultGalleryBinding
import com.sungkyul.synergy.edu_space.default_app.fragment.DefaultGalleryAlbumFragment
import com.sungkyul.synergy.edu_space.default_app.fragment.DefaultGalleryPictureFragment
import com.sungkyul.synergy.edu_space.default_app.fragment.DefaultGalleryShareFragment
import com.sungkyul.synergy.edu_space.default_app.fragment.DefaultGalleryStoryFragment
import com.sungkyul.synergy.utils.AnimUtils

data class BottomMenuItem(
    val fragment: Fragment,
    val text: TextView,
    val line: ImageView
)

class DefaultGalleryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultGalleryBinding
    private lateinit var pictureFragment: Fragment
    private lateinit var albumFragment: Fragment
    private lateinit var storyFragment: Fragment
    private lateinit var shareFragment: Fragment
    private lateinit var bottomMenuItems: List<BottomMenuItem>

    private val enabledColor = Color.parseColor("#252525")
    private val disabledColor = Color.parseColor("#858585")

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

        bottomMenuItems = listOf(
            BottomMenuItem(pictureFragment, binding.pictureButtonText, binding.pictureButtonLine),
            BottomMenuItem(albumFragment, binding.albumButtonText, binding.albumButtonLine),
            BottomMenuItem(storyFragment, binding.storyButtonText, binding.storyButtonLine),
            BottomMenuItem(shareFragment, binding.shareButtonText, binding.shareButtonLine),
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

                    selectBottomMenuItem(pictureFragment)
                }
                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpButtonAnimation(this, view)

                    replaceFragment(pictureFragment)
                }
            }
            true
        }
        binding.albumButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownButtonAnimation(this, view)

                    selectBottomMenuItem(albumFragment)
                }
                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpButtonAnimation(this, view)

                    replaceFragment(albumFragment)

                }
            }
            true
        }
        binding.storyButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownButtonAnimation(this, view)

                    selectBottomMenuItem(storyFragment)
                }
                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpButtonAnimation(this, view)

                    replaceFragment(storyFragment)
                }
            }
            true
        }
        binding.shareButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    AnimUtils.startTouchDownButtonAnimation(this, view)

                    selectBottomMenuItem(shareFragment)
                }
                MotionEvent.ACTION_UP -> {
                    AnimUtils.startTouchUpButtonAnimation(this, view)

                    replaceFragment(shareFragment)
                }
            }
            true
        }

        selectBottomMenuItem(pictureFragment)
        replaceFragment(pictureFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, fragment)
            .commit()
    }

    private fun selectBottomMenuItem(fragment: Fragment) {
        // 각 아이템의 텍스트 색상과 밑줄 보여짐 여부를 변경한다.
        for(i in bottomMenuItems) {
            if(i.fragment == fragment) {
                i.text.setTextColor(enabledColor)
                i.line.visibility = ImageView.VISIBLE
            } else {
                i.text.setTextColor(disabledColor)
                i.line.visibility = ImageView.INVISIBLE
            }
        }
    }
}
