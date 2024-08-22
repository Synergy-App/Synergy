package com.sungkyul.synergy.training_space.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.learning_space.ResultPair
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.training_space.NewScreenPracticeActivity

class ExamResultTestActivity : AppCompatActivity() {
    private lateinit var resultList: ArrayList<ResultPair>
    private var totalQuestions: Int = 0
    private var receivedId: Int = 0 // 변수명 변경



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam_result_test)
        Log.d("ExamResultTestActivity", "before Received receivedId: $receivedId")

        // 이전 액티비티에서 데이터를 받습니다.
        resultList = intent.getParcelableArrayListExtra("resultList") ?: arrayListOf()
        totalQuestions = intent.getIntExtra("totalQuestions", 0)
        receivedId = intent.getIntExtra("receivedId", -1) // receivedId 받기

        Log.d("ExamResultTestActivity", "Received receivedId: $receivedId")

        // 맞춘 문제 수 계산
        val correctAnswers = resultList.count { it.isCorrect }

        // 맞춘 문제 수를 화면에 표시
        val resultTextView: TextView = findViewById(R.id.resultTextView)
        resultTextView.text = "맞춘 문제 수: $correctAnswers/$totalQuestions"

        // 70% 이상 정답을 맞췄는지 확인
        val requiredCorrectAnswers = (totalQuestions * 0.7).toInt()

        // 거북이 이미지를 변경
        val turtleImageView: ImageView = findViewById(R.id.turtle_image)
        if (correctAnswers >= requiredCorrectAnswers) {
            turtleImageView.setImageResource(R.drawable.sebook_good)
        } else {
            turtleImageView.setImageResource(R.drawable.sebook_sad)
        }


        // 전체 보기 버튼 클릭 시
        val viewAllButton: ConstraintLayout = findViewById(R.id.viewAllButton)
        viewAllButton.setOnClickListener {
            val intent = Intent(this, ExamResultListActivity::class.java).apply {
                putParcelableArrayListExtra("resultList", resultList)
                putExtra("receivedId", receivedId) // receivedId 전달
                putExtra("source", "ExamResultTestActivity")
            }
            startActivity(intent)
            finish()
        }

        // 뒤로 가기 버튼 클릭 시
        val backButton: ConstraintLayout = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("target_fragment", MainActivity.Tag_examSpace)
                putExtra("selected_navigation_item", R.id.solvingFragment)
            }
            startActivity(intent)
            finish()
        }
    }
}
