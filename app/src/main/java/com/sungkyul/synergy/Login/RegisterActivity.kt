package com.sungkyul.synergy.com.sungkyul.synergy.Login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.R

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

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
