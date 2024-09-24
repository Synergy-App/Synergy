package com.sungkyul.synergy.training_space.google

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.sungkyul.synergy.databinding.ActivityPracticeGoogle9Binding
import com.sungkyul.synergy.training_space.google.result.ExamGoogleResultActivity

class PracticeGoogle9Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeGoogle9Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeGoogle9Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //삼초 뒤 성공 메세지 뜨게함
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, ExamGoogleResultActivity::class.java)
            startActivity(intent)
        }, 3000)
    }
}