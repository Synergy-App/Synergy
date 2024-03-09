package com.sungkyul.synergy.edu_space.accountedu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityGoogleMailAddBinding
import com.sungkyul.synergy.databinding.ActivityGoogleMailBinding

class GoogleMailAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGoogleMailAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoogleMailAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mailAddSkipButton.setOnClickListener {
            val nextIntent = Intent(this, GoogleMainActivity::class.java)

            // 값을 전달한다.
            nextIntent.putExtras(intent)
            nextIntent.putExtra("recovery_email", binding.mailAddEdittext.text.toString())
            nextIntent.putExtra("has_user_info", true)

            startActivity(nextIntent)
        }
    }
}
