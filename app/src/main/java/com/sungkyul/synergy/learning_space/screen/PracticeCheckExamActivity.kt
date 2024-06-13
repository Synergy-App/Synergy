package com.sungkyul.synergy.learning_space.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.sungkyul.synergy.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPracticeCheckExamBinding

class PracticeCheckExamActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeCheckExamBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeCheckExamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Include된 레이아웃에서 버튼 찾기
        val includeLayout = findViewById<RelativeLayout>(R.id.practice_nav_layout)

        val button1 = includeLayout.findViewById<ImageView>(R.id.home_nav)
        val button2 = includeLayout.findViewById<ImageView>(R.id.back_nav)
        val button3 = includeLayout.findViewById<ImageView>(R.id.next_nav)

        button1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        // Back 이전 문제 버튼
        button2.setOnClickListener {
            // Handle back navigation
        }
        // Next 다음 문제 버튼
        button3.setOnClickListener {
            // Handle next navigation
        }

        setInitialOptionStates()
    }



    ///TODO : db연동 전 ; 1 -> 틀림..  2 -> 맞음
    private fun setInitialOptionStates() {
        val option1Layout = binding.chooseOption1Btn
        val option2Layout = binding.chooseOption2Btn

        val option1Image = binding.optionImage1
        val option2Image = binding.optionImage2

        option1Layout.setCardBackgroundColor(Color.parseColor("#FFD231"))
        option1Image.setImageResource(R.drawable.wrong)
        option1Image.visibility = View.VISIBLE

        option2Layout.setCardBackgroundColor(Color.parseColor("#52A669"))
        option2Image.setImageResource(R.drawable.correct)
        option2Image.visibility = View.VISIBLE
    }
}
