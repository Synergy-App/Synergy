package com.sungkyul.synergy.edu_space.ticket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.sungkyul.synergy.R

class resActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res)


        val resButton2: Button = findViewById(R.id.button29)

        resButton2.setOnClickListener {
            val mapIntent = Intent(this, TpayActivity::class.java)
            startActivity(mapIntent)
        }
    }
}