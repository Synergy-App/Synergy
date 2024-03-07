package com.sungkyul.synergy.edu_space.accountedu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityGoogleMakeBinding
import com.sungkyul.synergy.edu_space.accountedu.GoogleDefaultInfoActivity

class GoogleMakeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGoogleMakeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoogleMakeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val makeNextButton: Button = findViewById(R.id.make_next_button)
        makeNextButton.setOnClickListener {
            // 다음 화면으로 이동하는 명시적 인텐트 설정
            val intent = Intent(this, GoogleDefaultInfoActivity::class.java)

            // 값을 전달한다.
            intent.putExtra("last_name", binding.firstnameEdittext.text.toString())
            intent.putExtra("first_name", binding.nameEdittext.text.toString())

            startActivity(intent) // 다음 화면으로 이동
        }
    }
}
