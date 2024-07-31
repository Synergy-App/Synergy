package com.sungkyul.synergy.learning_space.kakaotalk.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityKakaoMainBinding
import com.sungkyul.synergy.courses.kakotalk.KakaoCourse
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.learning_space.kakaotalk.fragment.ChatFragment
import com.sungkyul.synergy.learning_space.kakaotalk.fragment.FriendsFragment

/** 카카오톡 메인화면 하단 네비게이션바 + fragment */

private lateinit var binding: ActivityKakaoMainBinding
private const val TAG_FRIENDS = "friends_fragment"
private const val TAG_CHAT = "chat_fragment"
private const val TAG_OPENCHAT = "openChat_fragment"
private const val TAG_SHOPPING = "shopping_fragment"
private const val TAG_MORE = "more_fragment"

class KakaoMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakaoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment(TAG_FRIENDS, FriendsFragment())

        binding.friendButton.setOnClickListener {
            setFragment(TAG_FRIENDS, FriendsFragment())
        }
        binding.chattingButton.setOnClickListener {
            setFragment(TAG_CHAT, ChatFragment())
        }
        binding.openchattingButton.setOnClickListener {
            //setFragment(TAG_OPENCHAT, ())
        }
        binding.shoppingButton.setOnClickListener {
            //setFragment(TAG_SHOPPING, ())
        }
        binding.moreButton.setOnClickListener {
            //setFragment(TAG_MORE, ())
        }

        // 교육 추가
        binding.eduScreen.post {
            binding.eduScreen.course = KakaoCourse(binding.eduScreen)

            binding.eduScreen.setOnFinishedCourseListener {
                finish()
            }

            binding.eduScreen.start(this)
        }
        // 뒤로 가기 키를 눌렀을 때의 이벤트를 처리한다.
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // MainActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        })
    }

    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null) {
            fragTransaction.add(R.id.kakaoMainFrameLayout, fragment, tag)
        }

        val friends = manager.findFragmentByTag(TAG_FRIENDS)
        val chat = manager.findFragmentByTag(TAG_CHAT)

        if (friends != null) {
            if (tag == TAG_FRIENDS) {
                fragTransaction.show(friends)
            } else {
                fragTransaction.hide(friends)
            }
        }

        if (chat != null) {
            if (tag == TAG_CHAT) {
                fragTransaction.show(chat)
            } else {
                fragTransaction.hide(chat)
            }
        }

        fragTransaction.commitAllowingStateLoss()
    }
}