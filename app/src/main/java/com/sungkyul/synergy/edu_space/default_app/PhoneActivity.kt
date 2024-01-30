package com.sungkyul.synergy.edu_space.default_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.databinding.ActivityPhoneBinding
import com.sungkyul.synergy.util.Animation
import com.sungkyul.synergy.R

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

        // 돋보기 버튼에 터치 애니메이션을 추가한다.
        Animation.setViewBgAlphaAnimationOnTouch(
            binding.magnifyingGlassButton,
            0,
            0,
            36,
            250
        )
        // 더보기 버튼에 터치 애니메이션을 추가한다.
        Animation.setViewBgAlphaAnimationOnTouch(
            binding.moreButton,
            0,
            0,
            36,
            250
        )

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

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.phoneFragmentContainer.id, fragment)
            .commit()
    }
}
