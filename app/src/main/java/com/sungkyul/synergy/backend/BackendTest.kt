package com.sungkyul.synergy.backend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sungkyul.synergy.databinding.ActivityBackendTestBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class BackendTest : AppCompatActivity() {
    private lateinit var binding: ActivityBackendTestBinding
    private var currentQuestionIndex: Int = 0
    private lateinit var questions: List<QuizQuestion>
    private lateinit var quizApi: questionAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBackendTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 레트로핏 인스턴스 생성하기
        val retrofit = Retrofit.Builder()
            .baseUrl("http://172.16.111.213/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        quizApi = retrofit.create(questionAPI::class.java)

        // 질문 가져오기
        loadQuestions()

        // 다음 버튼 클릭 시
        binding.nextBtn.setOnClickListener {
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                displayQuestion(currentQuestionIndex)
            } else {
                Toast.makeText(this, "더 이상 질문이 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadQuestions() {
        quizApi.getQuestion().enqueue(object : Callback<List<QuizQuestion>> {
            override fun onResponse(call: Call<List<QuizQuestion>>, response: Response<List<QuizQuestion>>) {
                if (response.isSuccessful && response.body() != null) {
                    questions = response.body()!!
                    if (questions.isNotEmpty()) {
                        displayQuestion(currentQuestionIndex)
                    } else {
                        Toast.makeText(this@BackendTest, "질문이 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<List<QuizQuestion>>, t: Throwable) {
                // 오류 처리
            }
        })
    }

    private fun displayQuestion(index: Int) {
        val question = questions[index]

        with(binding) {
            QustionTextView.text = question.question
            optionTextView1.text = question.option1
            optionTextView2.text = question.option2
            optionTextView3.text = question.option3
            optionTextView4.text = question.option4
        }
    }
}
