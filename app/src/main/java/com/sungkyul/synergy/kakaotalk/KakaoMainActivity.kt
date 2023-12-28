package com.sungkyul.synergy.kakaotalk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityKakaoMainBinding
import androidx.fragment.app.Fragment

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
    }

    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null) {
            fragTransaction.add(R.id.friendsFragment, fragment, tag)
        }

        val friends = manager.findFragmentByTag(TAG_FRIENDS)
        val chat = manager.findFragmentByTag(TAG_CHAT)
        //val myPage = manager.findFragmentByTag(TAG_MY_PAGE)

        if (friends != null) {
            fragTransaction.hide(friends)
        }

        if (chat != null) {
            fragTransaction.hide(chat)
        }

        //if (myPage != null) {
        //    fragTransaction.hide(myPage)
        //}

//        if (tag == TAG_CALENDER) {
//            if (calender != null) {
//                fragTransaction.show(calender)
//            }
//        } else if (tag == TAG_HOME) {
//            if (home != null) {
//                fragTransaction.show(home)
//            }
//        } else if (tag == TAG_MY_PAGE) {
//            if (myPage != null) {
//                fragTransaction.show(myPage)
//            }
//        }

        fragTransaction.commitAllowingStateLoss()
    }
}