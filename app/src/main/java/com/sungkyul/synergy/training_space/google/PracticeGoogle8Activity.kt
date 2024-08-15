package com.sungkyul.synergy.training_space.google

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.sungkyul.synergy.R
import com.sungkyul.synergy.courses.accountedu.GoogleMailAddCourse
import com.sungkyul.synergy.databinding.ActivityGoogleMailAddBinding
import com.sungkyul.synergy.databinding.ActivityPracticeGoogle8Binding
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.learning_space.accountedu.GoogleMainActivity

class PracticeGoogle8Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeGoogle8Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeGoogle8Binding.inflate(layoutInflater)
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
