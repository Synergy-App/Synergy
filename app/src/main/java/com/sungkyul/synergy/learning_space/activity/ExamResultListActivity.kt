package com.sungkyul.synergy.learning_space.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityExamResultListBinding
import com.sungkyul.synergy.com.sungkyul.synergy.learning_space.ResultPair
import com.sungkyul.synergy.learning_space.adapter.ExamResultListAdapter
import com.sungkyul.synergy.learning_space.adapter.ExamResultListData
import com.sungkyul.synergy.utils.GalaxyButton

class ExamResultListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExamResultListBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamResultListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultList = intent.getParcelableArrayListExtra<ResultPair>("resultList")

        val dataSet = ArrayList<ExamResultListData>()
        if (resultList != null) {
            for (result in resultList) {
                val (questionNumber, isCorrect) = result
                val resultText = if (isCorrect) "맞았습니다" else "틀렸습니다"
                val resultImage = if (isCorrect) R.drawable.todo_circle else R.drawable.ic_micoff_white_24
                dataSet.add(ExamResultListData(resultImage, "$questionNumber. 문제 - $resultText"))
            }
        }

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ExamResultListAdapter(dataSet)

        binding.backButton.post { binding.backButton.clipToRoundRect(27.0f) }
        binding.viewAllButton.post { binding.viewAllButton.clipToRoundRect(27.0f) }

        binding.backButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }
                MotionEvent.ACTION_UP -> {
                    (view as GalaxyButton).startTouchUpAnimation()
                    finish()
                }
            }
            true
        }

        binding.viewAllButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }
                MotionEvent.ACTION_UP -> {
                    (view as GalaxyButton).startTouchUpAnimation()
                    // 다시 풀기 기능 추가
                }
            }
            true
        }
    }
}
