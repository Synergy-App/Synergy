package com.sungkyul.synergy.edu_space.kakaotaxi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.sungkyul.synergy.R

class payActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)

        val Button: Button = findViewById(R.id.button6)

        Button.setOnClickListener {
            val payMIntent = Intent(this, payMActivity::class.java)
            startActivity(payMIntent)
        }




    }
}

