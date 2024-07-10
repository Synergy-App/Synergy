package com.sungkyul.synergy.learning_space.accountedu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityGoogleMainBinding
import com.sungkyul.synergy.courses.accountedu.GoogleMainCourse

class
GoogleMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGoogleMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoogleMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            binding.eduScreen.course = GoogleMainCourse(binding.eduScreen)

            binding.eduScreen.setOnFinishedCourseListener {
                // 교육 코스가 끝났을 때 어떻게 할지 처리하는 곳이다.

                // MainActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
            // 교육을 시작한다.
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