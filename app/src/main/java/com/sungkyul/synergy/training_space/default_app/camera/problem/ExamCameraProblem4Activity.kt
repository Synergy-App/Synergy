package com.sungkyul.synergy.training_space.default_app.camera.problem

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityExamCameraProblem4Binding

class ExamCameraProblem4Activity : AppCompatActivity() {
    private lateinit var binding: ActivityExamCameraProblem4Binding
    private lateinit var optionButtons: List<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamCameraProblem4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 객관식 버튼들을 리스트에 담기
        optionButtons = listOf(
            binding.chooseOption1Btn,
            binding.chooseOption2Btn,
            binding.chooseOption3Btn,
            binding.chooseOption4Btn

        )

        // Next Question 버튼 클릭 리스너 설정
        binding.nextBtn.setOnClickListener {
//            val intent = Intent(this, ExamCameraProblem3Activity::class.java)
//            startActivity(intent)
        }

        // 백 버튼
        binding.backBtn.setOnClickListener {
        }

        // 버튼 클릭 리스너 설정
        optionButtons.forEach { button ->
            button.setOnClickListener { view ->
                handleOptionClick(view)
            }
        }
    }

    private fun handleOptionClick(clickedView: View) {
        // 모든 버튼의 배경색을 원래 색상으로 복구
        optionButtons.forEach { button ->
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        }

        // 클릭된 버튼만 노란색으로 변경
        clickedView.setBackgroundColor(Color.parseColor("#FFD231"))
    }

}