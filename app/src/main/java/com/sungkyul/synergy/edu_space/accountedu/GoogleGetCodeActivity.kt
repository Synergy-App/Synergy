package com.sungkyul.synergy.edu_space.accountedu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityGoogleGetCodeBinding

class GoogleGetCodeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGoogleGetCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoogleGetCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pwnextButton: Button = findViewById(R.id.pw_next_button)
        pwnextButton.setOnClickListener {
            val nextIntent = Intent(this, GooglePutCodeActivity::class.java)

            // 값을 전달한다.
            nextIntent.putExtras(intent)
            nextIntent.putExtra("phone_number", binding.phoneEdittext.text.toString())

            startActivity(nextIntent)
        }
    }
}

