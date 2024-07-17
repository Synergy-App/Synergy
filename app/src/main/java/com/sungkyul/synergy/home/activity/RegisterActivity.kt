package com.sungkyul.synergy.home.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.home.fragment.FragmentRegisterId
import com.sungkyul.synergy.com.sungkyul.synergy.home.fragment.FragmentRegisterNickname
import com.sungkyul.synergy.com.sungkyul.synergy.home.fragment.FragmentRegisterPassword
import com.sungkyul.synergy.com.sungkyul.synergy.home.fragment.FragmentRegisterPhone

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        navigateToIdFragment()
    }

    private fun navigateToIdFragment() {
        replaceFragment(FragmentRegisterId())
    }

    fun navigateToPasswordFragment(userId: String) {
        replaceFragment(FragmentRegisterPassword.newInstance(userId))
    }

    fun navigateToNicknameFragment(userId: String, password: String) {
        replaceFragment(FragmentRegisterNickname.newInstance(userId, password))
    }

    fun navigateToPhoneFragment(userId: String, password: String, nickname: String) {
        replaceFragment(FragmentRegisterPhone.newInstance(userId, password, nickname))
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
