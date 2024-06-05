package com.sungkyul.synergy.learning_space.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityEduSpaceBinding
import com.sungkyul.synergy.databinding.ActivityExamProblemBinding
import com.sungkyul.synergy.edu_space.icon_edu.activity.IconEduActivity

class ExamProblemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExamProblemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.problemText.text = "주어진 시간 내에 설정 어플을 홈 화면에 배치하시오."
        binding.problemStartBtn.setOnClickListener {
            val intent = Intent(this, ExamStartActivity::class.java)
            startActivity(intent)
        }



    }
}