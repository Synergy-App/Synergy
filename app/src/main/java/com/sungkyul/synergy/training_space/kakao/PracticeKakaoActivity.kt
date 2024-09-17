package com.sungkyul.synergy.training_space.kakao

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityKakaoMainBinding
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.training_space.kakao.fragment.Friends2Fragment

// Fragment 태그 상수 정의
private const val TAG_FRIENDS = "friends_fragment"
private const val TAG_CHAT = "chat_fragment"
private const val TAG_OPENCHAT = "openChat_fragment"
private const val TAG_SHOPPING = "shopping_fragment"
private const val TAG_MORE = "more_fragment"

class PracticeKakaoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKakaoMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakaoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 초기 Fragment 설정
        setFragment(TAG_FRIENDS, Friends2Fragment())

        // 버튼 클릭 리스너 설정
        binding.friendButton.setOnClickListener {
            setFragment(TAG_FRIENDS, Friends2Fragment())
        }

        binding.chattingButton.setOnClickListener {
            if (binding.eduScreen.onAction("click_chatting_button")) {
                //  setFragment(TAG_CHAT, ChatFragment())
            }
        }

        binding.openchattingButton.setOnClickListener {
            // setFragment(TAG_OPENCHAT, OpenChatFragment())
        }

        binding.shoppingButton.setOnClickListener {
            // setFragment(TAG_SHOPPING, ShoppingFragment())
        }

        binding.moreButton.setOnClickListener {
            // setFragment(TAG_MORE, MoreFragment())
        }

        // 뒤로 가기 키 이벤트 처리
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent(binding.root.context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        })
    }

    // Fragment 설정 함수
    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        // 해당 태그의 Fragment가 없는 경우 추가
        if (manager.findFragmentByTag(tag) == null) {
            fragTransaction.add(R.id.kakaoMainFrameLayout, fragment, tag)
        }

        // 이미 추가된 Fragment의 상태에 따라 보여주기/숨기기
        val friends = manager.findFragmentByTag(TAG_FRIENDS)
        val chat = manager.findFragmentByTag(TAG_CHAT)

        friends?.let {
            if (tag == TAG_FRIENDS) {
                fragTransaction.show(it)
            } else {
                fragTransaction.hide(it)
            }
        }

        chat?.let {
            if (tag == TAG_CHAT) {
                fragTransaction.show(it)
            } else {
                fragTransaction.hide(it)
            }
        }

        fragTransaction.commitAllowingStateLoss()
    }
}
