package com.sungkyul.synergy.backend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungkyul.synergy.databinding.ActivityBackendTestBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BackendTest : AppCompatActivity() {
    private lateinit var binding: ActivityBackendTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBackendTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 레트로핏 인스턴스 생성하기
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.35.175/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val quizApi = retrofit.create(questionAPI::class.java)

        // 요청 실패
        quizApi.getQuestion().enqueue(object : Callback<List<QuizQuestion>> {
            override fun onResponse(call: Call<List<QuizQuestion>>, response: Response<List<QuizQuestion>>) {
                if (response.isSuccessful && response.body() != null) {
                    val questions = response.body()!!
                    if (questions.isNotEmpty()) {
                        val firstQuestion = questions[0]

                        with(binding) {
                            QustionTextView.text = firstQuestion.question
                            optionTextView1.text = firstQuestion.option1
                            optionTextView2.text = firstQuestion.option2
                            optionTextView3.text = firstQuestion.option3
                            optionTextView4.text = firstQuestion.option4
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<QuizQuestion>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}
