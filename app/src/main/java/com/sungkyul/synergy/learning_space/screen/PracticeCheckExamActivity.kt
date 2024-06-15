package com.sungkyul.synergy.learning_space.screen

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.learning_space.ResultPair
import com.sungkyul.synergy.databinding.ActivityPracticeCheckExamBinding
import com.sungkyul.synergy.backend.ApiResponse
import com.sungkyul.synergy.backend.auth.AuthAPI
import com.sungkyul.synergy.types.Exam
import com.sungkyul.synergy.types.ExamListResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PracticeCheckExamActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeCheckExamBinding
    private lateinit var authAPI: AuthAPI
    private lateinit var token: String
    private var examList: List<Exam> = emptyList()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeCheckExamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedExamResult = intent.getParcelableExtra<ResultPair>("selectedExamResult")
        val questionNumber = intent.getIntExtra("questionNumber", -1)

        // Log the received data
        Log.d("PracticeCheckExamActivity", "Received questionNumber: $questionNumber")
        Log.d("PracticeCheckExamActivity", "Received selectedExamResult: $selectedExamResult")

        // Include된 레이아웃에서 버튼 찾기
        val includeLayout = findViewById<View>(R.id.practice_nav_layout)

        val button1 = includeLayout.findViewById<View>(R.id.home_nav)
        val button2 = includeLayout.findViewById<View>(R.id.back_nav)

        button1.setOnClickListener {
            finish()
        }
        // Back 이전 문제 버튼
        button2.setOnClickListener {
            finish()
        }

        // SharedPreferences 초기화
        val sharedPreferences = getSharedPreferences("SynergyPrefs", MODE_PRIVATE)
        token = sharedPreferences.getString("Token", null) ?: run {
            finish()
            return
        }

        // Logging Interceptor 설정
        val logging = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        // OkHttpClient에 logging 인터셉터 추가
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        // Retrofit 설정
        val retrofit = Retrofit.Builder()
            .baseUrl("https://synergy.hyeonwoo.com/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        authAPI = retrofit.create(AuthAPI::class.java)

        if (selectedExamResult != null && questionNumber != -1) {
            fetchExamList(selectedExamResult, questionNumber)
        } else {
            Log.e("PracticeCheckExamActivity", "Invalid selectedExamResult or questionNumber")
        }
    }

    private fun fetchExamList(selectedExamResult: ResultPair, questionNumber: Int) {
        authAPI.getExamList().enqueue(object : Callback<ApiResponse<ExamListResponse>> {
            override fun onResponse(
                call: Call<ApiResponse<ExamListResponse>>,
                response: Response<ApiResponse<ExamListResponse>>
            ) {
                if (response.isSuccessful) {
                    examList = response.body()?.data?.exams ?: emptyList()
                    val exam = examList.firstOrNull { it.id == questionNumber }
                    if (exam != null) {
                        showExam(exam)
                        setInitialOptionStates(selectedExamResult)
                    } else {
                        Log.e("PracticeCheckExamActivity", "Exam not found for questionNumber: $questionNumber")
                    }
                } else {
                    Log.e("PracticeCheckExamActivity", "Failed to fetch data: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse<ExamListResponse>>, t: Throwable) {
                Log.e("PracticeCheckExamActivity", "Error fetching data: ${t.message}")
            }
        })
    }

    private fun showExam(exam: Exam) {
        binding.learningInfoTv.text = exam.question

        if (!exam.descriptionImgUrl.isNullOrEmpty()) {
            binding.descriptionText.visibility = View.GONE
            binding.descriptionImage.visibility = View.VISIBLE
            Glide.with(this)
                .load(exam.descriptionImgUrl)
                .into(binding.descriptionImage)
        } else if (!exam.description.isNullOrEmpty()) {
            binding.descriptionImage.visibility = View.GONE
            binding.descriptionText.visibility = View.VISIBLE
            binding.descriptionText.text = exam.description
        } else {
            binding.descriptionImage.visibility = View.GONE
            binding.descriptionText.visibility = View.GONE
        }

        setupOption(binding.chooseOption1Btn, exam.select1, exam.select1ImgUrl, binding.optionText1.id, binding.optionImage1.id, 1)
        setupOption(binding.chooseOption2Btn, exam.select2, exam.select2ImgUrl, binding.optionText2.id, binding.optionImage2.id, 2)
        setupOption(binding.chooseOption3Btn, exam.select3, exam.select3ImgUrl, binding.optionText3.id, binding.optionImage3.id, 3)
        setupOption(binding.chooseOption4Btn, exam.select4, exam.select4ImgUrl, binding.optionText4.id, binding.optionImage4.id, 4)
    }

    private fun setupOption(optionCard: CardView, optionText: String?, optionImageUrl: String?, textViewId: Int, imageViewId: Int, optionNumber: Int) {
        val optionTextView = optionCard.findViewById<TextView>(textViewId)
        val optionImageView = optionCard.findViewById<ImageView>(imageViewId)

        if (!optionText.isNullOrEmpty()) {
            optionTextView.text = optionText
            optionTextView.visibility = View.VISIBLE
            optionImageView.visibility = View.GONE
        } else if (!optionImageUrl.isNullOrEmpty()) {
            Glide.with(this)
                .load(optionImageUrl)
                .into(optionImageView)
            optionTextView.visibility = View.GONE
            optionImageView.visibility = View.VISIBLE
        } else {
            optionTextView.visibility = View.GONE
            optionImageView.visibility = View.GONE
        }
    }

    private fun setInitialOptionStates(selectedExamResult: ResultPair) {
        val userAnswer = selectedExamResult.userAnswer
        val correctAnswer = selectedExamResult.correctAnswer

        val optionViews = listOf(
            binding.chooseOption1Btn,
            binding.chooseOption2Btn,
            binding.chooseOption3Btn,
            binding.chooseOption4Btn
        )

        optionViews.forEachIndexed { index, cardView ->
            val isSelected = index + 1 == userAnswer
            val isCorrect = index + 1 == correctAnswer

            cardView.setCardBackgroundColor(
                when {
                    isSelected && isCorrect -> Color.GREEN
                    isSelected -> Color.YELLOW
                    isCorrect -> Color.BLUE
                    else -> Color.WHITE
                }
            )
        }
    }
}
