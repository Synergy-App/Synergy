package com.sungkyul.synergy.edu_space.delivery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.delivery.payDActivity

class foodmenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foodmenu)

        val menuButton: Button = findViewById(R.id.button1)

        menuButton.setOnClickListener {
            val mapIntent = Intent(this, payDActivity::class.java)
            startActivity(mapIntent)
        }

    }
}