package com.sungkyul.synergy.learning_space.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.learning_space.ResultPair
import com.sungkyul.synergy.databinding.ActivityExamResultListBinding
import com.sungkyul.synergy.learning_space.adapter.ExamResultListAdapter
import com.sungkyul.synergy.learning_space.adapter.ExamResultListData
import com.sungkyul.synergy.learning_space.intent.LearningScreenFragment
import com.sungkyul.synergy.MainActivity

class ExamResultListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExamResultListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamResultListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultList = intent.getParcelableArrayListExtra<ResultPair>("resultList")
        val educationId = intent.getIntExtra("educationId", -1)
        val totalQuestions = intent.getIntExtra("totalQuestions", -1)

        // Log the received data
        Log.d("ExamResultListActivity", "Received educationId: $educationId")
        Log.d("ExamResultListActivity", "Received totalQuestions: $totalQuestions")
        Log.d("ExamResultListActivity", "Received resultList: $resultList")

        val dataSet = ArrayList<ExamResultListData>()
        if (resultList != null) {
            for (result in resultList) {
                val (questionNumber, isCorrect) = result
                val resultText = if (isCorrect) "맞았습니다" else "틀렸습니다"
                val resultImage = if (isCorrect) R.drawable.correct else R.drawable.wrong
                dataSet.add(ExamResultListData(resultImage, "$questionNumber. 문제 - $resultText"))
            }
        }

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ExamResultListAdapter(dataSet)

        // "돌아가기" 버튼 이벤트 처리
        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        // "다시 풀기" 버튼 이벤트 처리
        binding.viewAllButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("target_fragment", MainActivity.Tag_learning)
            intent.putExtra("target_navigation_item", R.id.solvingFragment)
            startActivity(intent)
        }
    }
}
