package com.sungkyul.synergy.learning_space.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityExamProblemBinding
import com.sungkyul.synergy.learning_space.screen.PracticeRecentlyDefaultActivity
import com.sungkyul.synergy.learning_space.screen.PracticeTopBarActivity

class ExampleProblem5Activity  : AppCompatActivity() {

    private lateinit var binding: ActivityExamProblemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.problemText.text = "최근 실행했던 앱을 확인해보세요."
        binding.problemStartBtn.setOnClickListener {
            val intent = Intent(this, PracticeRecentlyDefaultActivity::class.java)
            startActivity(intent)
        }
    }
}