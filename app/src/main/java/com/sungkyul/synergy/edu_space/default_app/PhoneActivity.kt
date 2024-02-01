package com.sungkyul.synergy.edu_space.default_app

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPhoneBinding
import com.sungkyul.synergy.util.AnimationUtil

class PhoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhoneBinding
    private lateinit var keypadFragment: Fragment
    private lateinit var recentHistoryFragment: Fragment
    private lateinit var contactFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fragments
        keypadFragment = KeypadFragment()
        recentHistoryFragment = RecentHistoryFragment()
        contactFragment = ContactFragment()

        // 돋보기, 더보기 버튼의 배경 알파 값 초기화
        binding.magnifyingGlassButton.background.alpha = TOUCH_UP_ALPHA
        binding.moreButton.background.alpha = TOUCH_UP_ALPHA

        binding.magnifyingGlassButton.setOnTouchListener(onTouchMagnifyingGlassListener)
        binding.moreButton.setOnTouchListener(onTouchMoreListener)

        // 하단 내비게이션 뷰에서 메뉴 아이템을 선택하면, 해당하는 프래그먼트로 교체한다.
        binding.phoneBottomNav.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.phone_keypad_nav -> replaceFragment(keypadFragment)
                R.id.phone_recent_history_nav -> replaceFragment(recentHistoryFragment)
                R.id.phone_contact_nav -> replaceFragment(contactFragment)
            }
            true
        }

        replaceFragment(keypadFragment)
    }

    // 돋보기 버튼의 터치 이벤트 리스너
    private val onTouchMagnifyingGlassListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    // 더보기 버튼의 터치 이벤트 리스너
    private val onTouchMoreListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
            MotionEvent.ACTION_UP -> {
                AnimationUtil.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_DOWN_ALPHA, TOUCH_UP_ALPHA)
                view.performClick()
            }
        }
        true
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.phoneFragmentContainer.id, fragment)
            .commit()
    }
}
