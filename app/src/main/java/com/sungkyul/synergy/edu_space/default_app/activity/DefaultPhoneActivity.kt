package com.sungkyul.synergy.edu_space.default_app.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sungkyul.synergy.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityDefaultPhoneBinding
import com.sungkyul.synergy.edu_space.default_app.TOUCH_DOWN_ALPHA
import com.sungkyul.synergy.edu_space.default_app.TOUCH_DURATION_ALPHA
import com.sungkyul.synergy.edu_space.default_app.TOUCH_UP_ALPHA
import com.sungkyul.synergy.edu_space.default_app.fragment.DefaultPhoneContactFragment
import com.sungkyul.synergy.edu_space.default_app.fragment.DefaultPhoneKeypadFragment
import com.sungkyul.synergy.edu_space.default_app.fragment.DefaultPhoneRecentHistoryFragment
import com.sungkyul.synergy.utils.AnimUtils
import com.sungkyul.synergy.utils.DynamicButton
import com.sungkyul.synergy.utils.EduCourses

class DefaultPhoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultPhoneBinding
    private lateinit var keypadFragment: Fragment
    private lateinit var recentHistoryFragment: Fragment
    private lateinit var contactFragment: Fragment

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            if(intent.getStringExtra("from") == null) {
                // 교육 코스 defaultPhoneCourse1을 지정한다.
                binding.eduScreen.course = EduCourses.defaultPhoneCourse1(
                    binding.eduScreen.context,
                    binding.eduScreen.width.toFloat(),
                    binding.eduScreen.height.toFloat()
                )
            }
            if(intent.getStringExtra("from") == "call") {
                // 교육 코스 defaultPhoneCourse2를 지정한다.
                binding.eduScreen.course = EduCourses.defaultPhoneCourse2(
                    binding.eduScreen.context,
                    binding.eduScreen.width.toFloat(),
                    binding.eduScreen.height.toFloat()
                )
            }
            binding.eduScreen.setOnFinishedCourseListener {
                // 교육 코스가 끝났을 때 어떻게 할지 처리하는 곳이다.

                // DefaultAppActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, DefaultAppActivity::class.java)
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
                val intent = Intent(binding.root.context, DefaultAppActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        })

        // Fragments
        keypadFragment = DefaultPhoneKeypadFragment(binding.eduScreen)
        recentHistoryFragment = DefaultPhoneRecentHistoryFragment()
        contactFragment = DefaultPhoneContactFragment()

        // 돋보기, 더보기 버튼의 배경 알파 값 초기화
        binding.magnifyingGlassButton.background.alpha = TOUCH_UP_ALPHA
        binding.moreButton.background.alpha = TOUCH_UP_ALPHA

        binding.magnifyingGlassButton.setOnTouchListener(onTouchMagnifyingGlassListener)
        binding.moreButton.setOnTouchListener(onTouchMoreListener)

        // 초기 메인 레이아웃 배경 설정
        updateMainBgColor(R.color.phoneBgColor)

        binding.keypadButton.post {
            binding.keypadButton.clipToRoundRect(20.0f)
        }
        binding.recentHistoryButton.post {
            binding.recentHistoryButton.clipToRoundRect(20.0f)
        }
        binding.contactButton.post {
            binding.contactButton.clipToRoundRect(20.0f)
        }

        // 하단 내비게이션 뷰에서 메뉴 아이템을 선택하면, 메인 레이아웃 배경을 변경하고 해당하는 프래그먼트로 교체한다.
        binding.keypadButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as DynamicButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }
                MotionEvent.ACTION_UP -> {
                    (view as DynamicButton).startTouchUpAnimation()

                    replaceFragment(keypadFragment)
                }
            }
            true
        }
        binding.recentHistoryButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as DynamicButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }
                MotionEvent.ACTION_UP -> {
                    (view as DynamicButton).startTouchUpAnimation()

                    if(binding.eduScreen.onAction("click_recent_history_button")) {
                        replaceFragment(recentHistoryFragment)
                    }
                }
            }
            true
        }
        binding.contactButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as DynamicButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }
                MotionEvent.ACTION_UP -> {
                    (view as DynamicButton).startTouchUpAnimation()

                    replaceFragment(contactFragment)
                }
            }
            true
        }

        replaceFragment(keypadFragment)
    }

    // 돋보기 버튼의 터치 이벤트 리스너
    private val onTouchMagnifyingGlassListener = View.OnTouchListener { view, event ->
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

    private fun updateMainBgColor(color: Int) {
        val drawable = ContextCompat.getDrawable(applicationContext, color)
        binding.phoneBottomNav.background = drawable
        binding.mainLayout.background = drawable
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.phoneFragmentContainer.id, fragment)
            .commit()
    }
}
