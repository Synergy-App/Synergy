package com.sungkyul.synergy.my_profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.databinding.ActivityMyExamResultBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.learning_space.ResultPair

class MyExamResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyExamResultBinding
    private lateinit var adapter: ExamResultAdapter
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyExamResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // SharedPreferences 초기화
        sharedPreferences = getSharedPreferences("SynergyPrefs", Context.MODE_PRIVATE)

        // 예제 데이터
        val educationId = 1
        val examResults = listOf(
            ExamResult("00차 화면구성", "시험결과", "0월 0일", R.drawable.group_3969, loadResultListFromSharedPreferences(educationId)),
        )

        // totalQuestions를 가져옵니다.
        val totalQuestions = 10

        // RecyclerView 초기화
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ExamResultAdapter(examResults, educationId, totalQuestions)
        binding.recyclerView.adapter = adapter
    }

    private fun loadResultListFromSharedPreferences(educationId: Int): ArrayList<ResultPair> {
        val gson = Gson()
        val jsonResultList = sharedPreferences.getString("resultList", null)
        return if (jsonResultList != null) {
            val type = object : TypeToken<ArrayList<ResultPair>>() {}.type
            gson.fromJson(jsonResultList, type)
        } else {
            ArrayList()
        }
    }
}