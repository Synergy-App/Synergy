package com.sungkyul.synergy.training_space.default_app.camera.problem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungkyul.synergy.databinding.ActivityExamProblemBinding
import com.sungkyul.synergy.training_space.default_app.camera.PracticeCamera3Activity

//사과사진 삭제하는거
class ExamCameraProblem3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityExamProblemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.problemText.text = "다음 갤러리에서 사과 사진을 삭제하시오."
        binding.problemStartBtn.setOnClickListener {
            val intent = Intent(this, PracticeCamera3Activity::class.java)
            startActivity(intent)
        }
    }
}