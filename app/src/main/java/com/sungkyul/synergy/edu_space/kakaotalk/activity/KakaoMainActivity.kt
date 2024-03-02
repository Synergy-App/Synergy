package com.sungkyul.synergy.edu_space.kakaotalk.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityKakaoMainBinding
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.edu_space.kakaotalk.fragment.ChatFragment
import com.sungkyul.synergy.edu_space.kakaotalk.fragment.FriendsFragment
import com.sungkyul.synergy.utils.EduCourses

/** 카카오톡 메인화면 하단 네비게이션바 + fragment */

private lateinit var binding: ActivityKakaoMainBinding
private const val TAG_FRIENDS = "friends_fragment"
private const val TAG_CHAT = "chat_fragment"
//private const val TAG_OPENCHAT = "openChat_fragment"
//private const val TAG_SHOPPING = "shopping_fragment"
private const val TAG_MORE = "more_fragment"

class KakaoMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakaoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment(TAG_FRIENDS, FriendsFragment())

        binding.kakaoNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.friendsFragment -> setFragment(TAG_FRIENDS, FriendsFragment())
                R.id.chatFragment -> setFragment(TAG_CHAT, ChatFragment())
                //  R.id.openChatFragment -> setFragment(TAG_OPENCHAT, OpenchatFragment())
            }
            true
        }

        // 교육 추가
        binding.eduScreen.post {
            binding.eduScreen.course = EduCourses.kakaoCourse(
                binding.eduScreen.context,
                binding.eduScreen.width.toFloat(),
                binding.eduScreen.height.toFloat()
            )
            binding.eduScreen.setOnFinishedCourseListener {
                finish()
            }
            binding.eduScreen.start(this)
        }
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