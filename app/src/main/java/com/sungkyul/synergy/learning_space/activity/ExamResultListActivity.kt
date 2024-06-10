package com.sungkyul.synergy.learning_space.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.learning_space.ResultPair
import com.sungkyul.synergy.databinding.ActivityExamResultListBinding
import com.sungkyul.synergy.learning_space.adapter.ExamResultListAdapter
import com.sungkyul.synergy.learning_space.adapter.ExamResultListData

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
    }
}