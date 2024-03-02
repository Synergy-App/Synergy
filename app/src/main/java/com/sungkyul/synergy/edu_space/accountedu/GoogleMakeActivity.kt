package com.sungkyul.synergy.edu_space.accountedu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.accountedu.GoogleDefaultInfoActivity

class GoogleMakeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_make)

        val makeNextButton: Button = findViewById(R.id.make_next_button)
        makeNextButton.setOnClickListener {
            // 다음 화면으로 이동하는 명시적 인텐트 설정
            val intent = Intent(this@GoogleMakeActivity, GoogleDefaultInfoActivity::class.java)
            startActivity(intent) // 다음 화면으로 이동
        }
    }
}
