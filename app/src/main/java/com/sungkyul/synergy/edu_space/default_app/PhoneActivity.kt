package com.sungkyul.synergy.edu_space.default_app

import android.opengl.Visibility
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityDefaultCallPhoneBinding
import com.sungkyul.synergy.util.AnimationUtil
import com.sungkyul.synergy.util.EduUtil

class PhoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultCallPhoneBinding
    private lateinit var keypadFragment: Fragment
    private lateinit var recentHistoryFragment: Fragment
    private lateinit var contactFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultCallPhoneBinding.inflate(layoutInflater)
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

        // 초기 메인 레이아웃 배경 설정
        updateMainBgColor(R.color.phoneBgColor)

        // 하단 내비게이션 뷰에서 메뉴 아이템을 선택하면, 메인 레이아웃 배경을 변경하고 해당하는 프래그먼트로 교체한다.
        binding.phoneBottomNav.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.phone_keypad_nav -> {
                    updateMainBgColor(R.color.phoneBgColor)
                    replaceFragment(keypadFragment)
                }
                R.id.phone_recent_history_nav -> {
                    updateMainBgColor(R.color.phoneDeepBgColor)
                    replaceFragment(recentHistoryFragment)
                }
                R.id.phone_contact_nav -> {
                    updateMainBgColor(R.color.phoneDeepBgColor)
                    replaceFragment(contactFragment)
                }
            }
            true
        }

        replaceFragment(keypadFragment)

        // TODO: TEST
        /*binding.eduScreen.bringToFront()
        binding.phoneBottomNav.visibility = View.INVISIBLE
        EduUtil.setAllViewsEnabled(binding.mainLayout, false)
        EduUtil.test(supportFragmentManager, binding.eduScreen.id)*/
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
