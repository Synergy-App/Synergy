package com.sungkyul.synergy.edu_space.accountedu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R


class GoogleMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_main)

        // 계정 생성 또는 로그인이 완료되면, 이 부분이 실행된다.
        if(intent.hasExtra("has_user_info")) {
            Log.i("last_name", intent.getStringExtra("last_name")!!)
            Log.i("first_name", intent.getStringExtra("first_name")!!)
            Log.i("year", intent.getStringExtra("year")!!)
            Log.i("month", intent.getStringExtra("month")!!)
            Log.i("day", intent.getStringExtra("day")!!)
            Log.i("gender", intent.getStringExtra("gender")!!)
            Log.i("email", intent.getStringExtra("email")!!)
            Log.i("password", intent.getStringExtra("password")!!)
            Log.i("phone_number", intent.getStringExtra("phone_number")!!)
            Log.i("recovery_email", intent.getStringExtra("recovery_email")!!)
        }

        val loginButton: Button = findViewById(R.id.login_button)
        loginButton.setOnClickListener {
            val nextIntent = Intent(this, GoogleLoginActivity::class.java)
            startActivity(nextIntent)
        }
    }
}