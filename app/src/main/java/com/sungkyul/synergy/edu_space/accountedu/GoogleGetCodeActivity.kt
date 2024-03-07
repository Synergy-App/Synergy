package com.sungkyul.synergy.edu_space.accountedu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R

class GoogleGetCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_get_code)

        val pwnextButton: Button = findViewById(R.id.pw_next_button)
        pwnextButton.setOnClickListener {
            val intent = Intent(this, GooglePutCodeActivity::class.java)
            startActivity(intent)
        }
    }
}

