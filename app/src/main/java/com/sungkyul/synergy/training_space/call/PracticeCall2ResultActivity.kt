package com.sungkyul.synergy.training_space.call

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeCall2ResultBinding
import com.sungkyul.synergy.learning_space.default_app.TOUCH_DOWN_ALPHA
import com.sungkyul.synergy.learning_space.default_app.TOUCH_DURATION_ALPHA
import com.sungkyul.synergy.learning_space.default_app.TOUCH_UP_ALPHA
import com.sungkyul.synergy.learning_space.default_app.phone.adapter.ContactData
import com.sungkyul.synergy.learning_space.default_app.phone.fragment.DefaultPhoneContactFragment
import com.sungkyul.synergy.learning_space.default_app.phone.fragment.DefaultPhoneKeypadFragment
import com.sungkyul.synergy.learning_space.default_app.phone.fragment.DefaultPhoneRecentHistoryFragment
import com.sungkyul.synergy.training_space.call.problem.ExamCallProblem3Activity
import com.sungkyul.synergy.utils.AnimUtils
import com.sungkyul.synergy.utils.GalaxyButton

// 전화 끊고 나오는 뷰임
class PracticeCall2ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeCall2ResultBinding
    private lateinit var keypadFragment: Fragment
    private lateinit var recentHistoryFragment: Fragment
    private lateinit var contactFragment: Fragment

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityPracticeCall2ResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fragments 초기화
        keypadFragment = DefaultPhoneKeypadFragment(binding.eduScreen)
        recentHistoryFragment = DefaultPhoneRecentHistoryFragment()
        contactFragment = DefaultPhoneContactFragment(eduListener = binding.eduScreen)

        // 초기 메인 레이아웃 배경 설정
        updateMainBgColor(R.color.phoneBgColor)


        // 3초 후에 화면 전환
        Handler(Looper.getMainLooper()).postDelayed({
            // 예를 들어, DefaultPhoneActivity로 전환하려면 다음 코드를 사용
            // Intent를 사용하여 다른 액티비티로 이동
            val intent = Intent(this, ExamCallProblem3Activity::class.java)
            startActivity(intent)
            // 현재 액티비티를 종료하려면
            finish()
        }, 3000) // 3000 밀리초 = 3초

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
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }

                MotionEvent.ACTION_UP -> {
                    (view as GalaxyButton).startTouchUpAnimation()

                    if (binding.eduScreen.onAction("click_keypad_button")) {
                        replaceFragment(keypadFragment)
                    }
                }
            }
            true
        }
        binding.recentHistoryButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }

                MotionEvent.ACTION_UP -> {
                    (view as GalaxyButton).startTouchUpAnimation()

                    if (binding.eduScreen.onAction("click_recent_history_button")) {
                        replaceFragment(recentHistoryFragment)
                    }
                }
            }
            true
        }
        binding.contactButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }

                MotionEvent.ACTION_UP -> {
                    (view as GalaxyButton).startTouchUpAnimation()

                    if (binding.eduScreen.onAction("click_contact_button")) {
                        replaceFragment(contactFragment)
                    }
                }
            }
            true
        }

        // 기본 화면을 최근 기록으로 설정
        replaceFragment(recentHistoryFragment)

        if (intent.getStringExtra("from") == "save_contact") {
            // 새 연락처를 프래그먼트로 넘긴다.
            contactFragment = DefaultPhoneContactFragment(
                ContactData(
                    R.drawable.ic_person_black_24dp,
                    intent.getStringExtra("name")!!,
                    intent.getStringExtra("num")!!
                ),
                binding.eduScreen
            )

            replaceFragment(recentHistoryFragment)
        }
    }


    // 더보기 버튼의 터치 이벤트 리스너
    private val onTouchMoreListener = View.OnTouchListener { view, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                AnimUtils.startAlphaAnimation(
                    view.background,
                    TOUCH_DURATION_ALPHA,
                    TOUCH_UP_ALPHA,
                    TOUCH_DOWN_ALPHA
                )
            }

            MotionEvent.ACTION_UP -> {
                AnimUtils.startAlphaAnimation(
                    view.background,
                    TOUCH_DURATION_ALPHA,
                    TOUCH_DOWN_ALPHA,
                    TOUCH_UP_ALPHA
                )
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
