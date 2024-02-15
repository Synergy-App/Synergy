package com.sungkyul.synergy.main.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import com.sungkyul.synergy.databinding.ActivityRegisterBinding

/** 파이어베이스 익명 로그인 test*/
private lateinit var binding: ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.synergyRegisterBtn.setOnClickListener {
            userIdCheck()
        }
    }

    private fun userIdCheck() {
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user != null) {
            userId = user.uid
            navigateToNextActivity()
        } else {
            auth.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        userId = auth.currentUser!!.uid
                        navigateToNextActivity()
                    } else {
                        print("Firebase 로그인 실패!")
                    }
                }
        }
    }

    private fun navigateToNextActivity() {
        val intent = Intent(this, DigitalStartActivity::class.java)
        startActivity(intent)
        finish()
    }
}