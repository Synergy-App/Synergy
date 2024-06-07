package com.sungkyul.synergy.my_profile

import ExamResultAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R

class MyExamResultActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ExamResultAdapter
    private val examResults = listOf(
        ExamResult( R.drawable.screen_app1,"4차", "앱 설치 시험결과","8월 3일"),
        ExamResult( R.drawable.ic_edubutton_kakaotalk,"3차", "카카오톡 시험결과","3월 31일"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_exam_result)

        // RecyclerView 설정
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ExamResultAdapter(examResults)
        recyclerView.adapter = adapter
    }
}
