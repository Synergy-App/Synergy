package com.sungkyul.synergy.edu_space.default_app.phone.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityDefaultPhoneBinding
import com.sungkyul.synergy.edu_courses.default_app.phone.DefaultPhoneCourse1
import com.sungkyul.synergy.edu_courses.default_app.phone.DefaultPhoneCourse2
import com.sungkyul.synergy.edu_courses.default_app.phone.DefaultPhoneCourse3
import com.sungkyul.synergy.edu_space.default_app.TOUCH_DOWN_ALPHA
import com.sungkyul.synergy.edu_space.default_app.TOUCH_DURATION_ALPHA
import com.sungkyul.synergy.edu_space.default_app.TOUCH_UP_ALPHA
import com.sungkyul.synergy.edu_space.default_app.DefaultAppActivity
import com.sungkyul.synergy.edu_space.default_app.phone.adapter.ContactData
import com.sungkyul.synergy.edu_space.default_app.phone.fragment.DefaultPhoneContactFragment
import com.sungkyul.synergy.edu_space.default_app.phone.fragment.DefaultPhoneKeypadFragment
import com.sungkyul.synergy.edu_space.default_app.phone.fragment.DefaultPhoneRecentHistoryFragment
import com.sungkyul.synergy.utils.AnimUtils
import com.sungkyul.synergy.utils.GalaxyButton
import com.sungkyul.synergy.utils.edu.EduScreen

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
                binding.eduScreen.course = DefaultPhoneCourse1(binding.eduScreen)
            }
            if(intent.getStringExtra("from") == "call") {
                binding.eduScreen.course = DefaultPhoneCourse2(binding.eduScreen)
            }
            if(intent.getStringExtra("from") == "add_contact") {
                binding.eduScreen.course = DefaultPhoneCourse3(binding.eduScreen)
            }

            binding.eduScreen.setOnFinishedCourseListener {
                EduScreen.toTop(this, DefaultAppActivity::class.java)
            }
            binding.eduScreen.start(this)
        }
        EduScreen.navigateBackToTop(this, DefaultAppActivity::class.java)

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
                    (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }
                MotionEvent.ACTION_UP -> {
                    (view as GalaxyButton).startTouchUpAnimation()

                    if(binding.eduScreen.onAction("click_keypad_button")) {
                        replaceFragment(keypadFragment)
                    }
                }
            }
            true
        }
        binding.recentHistoryButton.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }
                MotionEvent.ACTION_UP -> {
                    (view as GalaxyButton).startTouchUpAnimation()

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
                    (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }
                MotionEvent.ACTION_UP -> {
                    (view as GalaxyButton).startTouchUpAnimation()

                    if(binding.eduScreen.onAction("click_contact_button")) {
                        replaceFragment(contactFragment)
                    }
                }
            }
            true
        }

        replaceFragment(keypadFragment)

        if(intent.getStringExtra("from") == "add_contact") {
            // 새 연락처를 프래그먼트로 넘긴다.
            contactFragment = DefaultPhoneContactFragment(
                ContactData(
                    R.drawable.ic_person_black_24dp,
                    intent.getStringExtra("name")!!,
                    intent.getStringExtra("num")!!
                )
            )

            replaceFragment(contactFragment)
        }
    }

    // 돋보기 버튼의 터치 이벤트 리스너
    private val onTouchMagnifyingGlassListener = View.OnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(view.background, TOUCH_DURATION_ALPHA, TOUCH_UP_ALPHA, TOUCH_DOWN_ALPHA)
            }
//            MotionEvent.ACTION_UP -> {
//                val intent = Intent(this@DefaultPhoneActivity, DefaultPhoneSearchActivity::class.java)
//                startActivity(intent)
//            }
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
