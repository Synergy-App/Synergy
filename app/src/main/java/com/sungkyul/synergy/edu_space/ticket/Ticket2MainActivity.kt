package com.sungkyul.synergy.edu_space.ticket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.sungkyul.synergy.R

class Ticket2MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket2_main)


        val resButton1: Button = findViewById(R.id.button16)

        resButton1.setOnClickListener {
            val mapIntent = Intent(this, resActivity::class.java)
            startActivity(mapIntent)
        }

    }



}