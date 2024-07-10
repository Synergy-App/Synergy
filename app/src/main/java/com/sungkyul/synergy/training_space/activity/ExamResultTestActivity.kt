package com.sungkyul.synergy.training_space.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.learning_space.ResultPair
import com.sungkyul.synergy.MainActivity

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

        val turtleImageView: ImageView = findViewById(R.id.turtle_image)
        if (correctAnswers >= 5) {
            turtleImageView.setImageResource(R.drawable.sebook_good)
        } else {
            turtleImageView.setImageResource(R.drawable.sebook_sad)
        }

        val viewAllButton: ConstraintLayout = findViewById(R.id.viewAllButton)
        viewAllButton.setOnClickListener {
            val intent = Intent(this, ExamResultListActivity::class.java).apply {
                putParcelableArrayListExtra("resultList", resultList)
            }
            startActivity(intent)
            finish()
        }

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
