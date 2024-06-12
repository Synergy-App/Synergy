package com.sungkyul.synergy.my_profile

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Point
import android.os.Bundle
import android.util.DisplayMetrics
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

    private var standardSize_X: Int = 0
    private var standardSize_Y: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyExamResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 화면의 기준 사이즈를 계산
        getStandardSize()

        // 텍스트 크기를 기준 사이즈를 이용해 설정
        binding.headerTitle.textSize = (standardSize_X / 12).toFloat()
        binding.headerSubtitle.textSize = (standardSize_X / 16).toFloat()

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

    // 디스플레이 사이즈를 반환하는 메서드
    private fun getScreenSize(): Point {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size
    }

    // 기기의 해상도와 밀도를 기준으로 기준 사이즈를 계산하는 메서드
    private fun getStandardSize() {
        val screenSize = getScreenSize()
        val displayMetrics: DisplayMetrics = resources.displayMetrics
        val density = displayMetrics.density

        standardSize_X = (screenSize.x / density).toInt()
        standardSize_Y = (screenSize.y / density).toInt()
    }
}
