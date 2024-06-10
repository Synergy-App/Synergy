package com.sungkyul.synergy.learning_space.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.learning_space.ResultPair
import com.sungkyul.synergy.utils.GalaxyButton

class ExamResultTestActivity : AppCompatActivity() {
    private lateinit var resultList: ArrayList<ResultPair>
    private var totalQuestions: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam_result_test)

        resultList = intent.getParcelableArrayListExtra("resultList") ?: arrayListOf()
        totalQuestions = intent.getIntExtra("totalQuestions", 0)
        val correctAnswers = resultList.count { it.isCorrect }

        val resultTextView: TextView = findViewById(R.id.resultTextView)
        resultTextView.text = "맞춘 문제 수: $correctAnswers/$totalQuestions"

        val viewAllButton: GalaxyButton = findViewById(R.id.viewAllButton)
        viewAllButton.setOnClickListener {
            val intent = Intent(this, ExamResultListActivity::class.java).apply {
                putParcelableArrayListExtra("resultList", resultList)
            }
            startActivity(intent)
            finish()
        }
    }
}
