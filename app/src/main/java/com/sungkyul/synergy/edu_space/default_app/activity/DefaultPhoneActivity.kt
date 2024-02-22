package com.sungkyul.synergy.edu_space.default_app.activity

import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityDefaultPhoneBinding
import com.sungkyul.synergy.edu_space.default_app.TOUCH_DOWN_ALPHA
import com.sungkyul.synergy.edu_space.default_app.TOUCH_DURATION_ALPHA
import com.sungkyul.synergy.edu_space.default_app.TOUCH_UP_ALPHA
import com.sungkyul.synergy.edu_space.default_app.fragment.DefaultPhoneContactFragment
import com.sungkyul.synergy.edu_space.default_app.fragment.DefaultPhoneKeypadFragment
import com.sungkyul.synergy.edu_space.default_app.fragment.DefaultPhoneRecentHistoryFragment
import com.sungkyul.synergy.utils.AnimUtils
import com.sungkyul.synergy.utils.Edu
import com.sungkyul.synergy.utils.EduData
import com.sungkyul.synergy.utils.EduDescription
import com.sungkyul.synergy.utils.EduPractice

class DefaultPhoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefaultPhoneBinding
    private lateinit var keypadFragment: Fragment
    private lateinit var recentHistoryFragment: Fragment
    private lateinit var contactFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 교육 코스 추가
        val course = ArrayList<EduData>()

        course.add(EduDescription(
            title = "",
            titleGravity = Gravity.START,
            content = "안녕하세요!",
            contentGravity = Gravity.CENTER,
            dialogDuration = 0,
            dialogTop = 300,
            dialogBottom = 300,
            dialogStart = 50,
            dialogEnd = 50,
            coverDuration = 0,
            coverTop = 900,
            coverBottom = 0,
            coverStart = 0,
            coverEnd = 0
        ))
        course.add(EduDescription(
            content = "기본 앱의 교육 과정을\n클래스 형태로 구현해봤어요."
        ))
        course.add(EduDescription(
            title = "전화 앱",
            content = "다이얼로그에 제목을\n추가할 수 있어요.",
            contentGravity = Gravity.START,
            dialogDuration = 500,
            dialogTop = 280,
            dialogBottom = 280,
            dialogStart = 40,
            dialogEnd = 40
        ))
        course.add(EduDescription(
            content = "제목 또는 내용을\n왼쪽/가운데/오른쪽\n정렬할 수 있어요.",
            titleGravity = Gravity.END,
            contentGravity = Gravity.END,
            dialogBottom = 260
        ))
        course.add(EduDescription(
            content = "다이얼로그를 크기 조절하거나\n이동할 수 있어요.",
            titleGravity = Gravity.START,
            contentGravity = Gravity.START,
            dialogTop = 60,
            dialogBottom = 500,
            dialogStart = 40,
            dialogEnd = 40
        ))
        course.add(EduDescription(
            content = "특정 부분을 강조할 수 있어요.",
            coverTop = 280,
            coverBottom = 160
        ))
        course.add(EduDescription(
            content = "키패드에서 7을 눌러보세요.",
            coverDuration = 500,
            coverTop = 440,
            coverBottom = 240
        ))
        course.add(EduPractice(
            actionId = "click_key_button",
            actionMsg = "7"
        ))
        course.add(EduDescription(
            title = "",
            titleGravity = Gravity.START,
            content = "잘했어요!",
            contentGravity = Gravity.CENTER,
            dialogDuration = 0,
            dialogTop = 300,
            dialogBottom = 260,
            dialogStart = 50,
            dialogEnd = 50,
            coverDuration = 0,
            coverTop = 900,
            coverBottom = 0,
            coverStart = 0,
            coverEnd = 0
        ))
        course.add(EduDescription(
            title = "전화 앱",
            content = "이번엔 여기를 135790로\n맞춰 보세요.",
            contentGravity = Gravity.START,
            dialogDuration = 500,
            dialogTop = 350,
            dialogBottom = 220,
            dialogStart = 40,
            dialogEnd = 40,
            coverTop = 200,
            coverBottom = 500
        ))
        course.add(EduPractice(
            actionId = "phone_num_text_changed",
            actionMsg = "135790"
        ))
        course.add(EduDescription(
            title = "",
            titleGravity = Gravity.START,
            content = "굿잡이에요!",
            contentGravity = Gravity.CENTER,
            dialogDuration = 0,
            dialogTop = 300,
            dialogBottom = 300,
            dialogStart = 50,
            dialogEnd = 50,
            coverDuration = 0,
            coverTop = 900,
            coverBottom = 0,
            coverStart = 0,
            coverEnd = 0
        ))

        val edu = Edu(this, course)

        binding.phoneBottomNav.visibility = BottomNavigationView.INVISIBLE


        // Fragments
        keypadFragment = DefaultPhoneKeypadFragment(edu)
        recentHistoryFragment = DefaultPhoneRecentHistoryFragment()
        contactFragment = DefaultPhoneContactFragment()

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
