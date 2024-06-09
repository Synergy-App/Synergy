package com.sungkyul.synergy.learning_space

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.sungkyul.synergy.R
import com.sungkyul.synergy.backend.ApiResponse
import com.sungkyul.synergy.backend.auth.AuthAPI
import com.sungkyul.synergy.databinding.ActivityNewScreenPracticeBinding
import com.sungkyul.synergy.types.Exam
import com.sungkyul.synergy.types.ExamAnswerBody
import com.sungkyul.synergy.types.ExamCheckResult
import com.sungkyul.synergy.types.ExamListResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewScreenPracticeActivity : AppCompatActivity() {

    private lateinit var authAPI: AuthAPI
    private var examList: List<Exam> = emptyList()
    private var currentExamIndex = 0
    private var selectedAnswer: Int? = null
    private lateinit var optionCardViews: List<CardView>
    private lateinit var binding: ActivityNewScreenPracticeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var token: String

    private val cardViewIds = arrayOf(
        R.id.choose_option1_btn,
        R.id.choose_option2_btn,
        R.id.choose_option3_btn,
        R.id.choose_option4_btn
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewScreenPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // SharedPreferences 초기화
        sharedPreferences = getSharedPreferences("SynergyPrefs", Context.MODE_PRIVATE)
        token = sharedPreferences.getString("Token", null) ?: run {
            Toast.makeText(this, "토큰이 없습니다. 다시 로그인 해주세요.", Toast.LENGTH_SHORT).show()
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

        // Exam 데이터를 가져오는 함수 호출
        fetchExamList()

        // Next Question 버튼 클릭 리스너 설정
        binding.nextBtn.setOnClickListener {
            selectedAnswer?.let {
                Log.d("NewScreenPracticeActivity", "Selected answer: $it")
                checkAnswer(it)
            } ?: run {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
            }
        }

        optionCardViews = cardViewIds.map { findViewById<CardView>(it) }.toList()

        for (cardView in optionCardViews) {
            cardView.setOnClickListener { selectOption(cardView) }
        }

        // 백 버튼
        binding.backBtn.setOnClickListener {
            // Handle back button action
        }

        // 다음 버튼
        binding.nextBtn.setOnClickListener {
            selectedAnswer?.let {
                checkAnswer(it)
            } ?: run {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchExamList() {
        authAPI.getExamList().enqueue(object : Callback<ApiResponse<ExamListResponse>> {
            override fun onResponse(
                call: Call<ApiResponse<ExamListResponse>>,
                response: Response<ApiResponse<ExamListResponse>>
            ) {
                if (response.isSuccessful) {
                    examList = response.body()?.data?.exams ?: emptyList()
                    currentExamIndex = 0
                    showExam(currentExamIndex)
                } else {
                    Toast.makeText(this@NewScreenPracticeActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                    Log.e("API_ERROR", "Response code: ${response.code()}, message: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse<ExamListResponse>>, t: Throwable) {
                Toast.makeText(this@NewScreenPracticeActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("API_ERROR", "Failure: ${t.message}", t)
            }
        })
    }

    private fun checkAnswer(answer: Int) {
        val examId = examList[currentExamIndex].id
        val answerBody = ExamAnswerBody(answerOnSelect = answer, answerOnInput = "", answerOnActivity = "")

        authAPI.checkExamAnswer(token, examId, answerBody).enqueue(object : Callback<ApiResponse<ExamCheckResult>> {
            override fun onResponse(
                call: Call<ApiResponse<ExamCheckResult>>,
                response: Response<ApiResponse<ExamCheckResult>>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()?.data
                    if (result != null) {
                        if (result.correct) {
                            Toast.makeText(this@NewScreenPracticeActivity, "Correct answer!", Toast.LENGTH_SHORT).show()
                            showNextExam()
                        } else {
                            Toast.makeText(this@NewScreenPracticeActivity, "Wrong answer!", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@NewScreenPracticeActivity, "Failed to check answer", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val errorMsg = response.errorBody()?.string()
                    Toast.makeText(this@NewScreenPracticeActivity, "Failed to check answer: $errorMsg", Toast.LENGTH_SHORT).show()
                    Log.e("API_ERROR", "Response code: ${response.code()}, message: ${response.message()}, error: $errorMsg")
                }
            }

            override fun onFailure(call: Call<ApiResponse<ExamCheckResult>>, t: Throwable) {
                Toast.makeText(this@NewScreenPracticeActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("API_ERROR", "Failure: ${t.message}", t)
            }
        })
    }

    private fun showNextExam() {
        Log.d("NewScreenPracticeActivity", "showNextExam called")
        if (currentExamIndex < examList.size - 1) {
            currentExamIndex++
            showExam(currentExamIndex)
        } else {
            Toast.makeText(this, "No more questions available", Toast.LENGTH_SHORT).show()
        }
        Log.d("NewScreenPracticeActivity", "showNextExam finished")
    }

    private fun showExam(index: Int) {
        Log.d("NewScreenPracticeActivity", "showExam called with index: $index")
        val exam = examList[index]
        binding.learningInfoTv.text = exam.question

        // descriptionImgUrl 또는 description을 설정
        if (!exam.descriptionImgUrl.isNullOrEmpty()) {
            binding.descriptionText.visibility = TextView.GONE
            binding.descriptionImage.visibility = ImageView.VISIBLE
            Glide.with(this)
                .load(exam.descriptionImgUrl)
                .into(binding.descriptionImage)
        } else if (!exam.description.isNullOrEmpty()) {
            binding.descriptionImage.visibility = ImageView.GONE
            binding.descriptionText.visibility = TextView.VISIBLE
            binding.descriptionText.text = exam.description
        } else {
            binding.descriptionImage.visibility = ImageView.GONE
            binding.descriptionText.visibility = TextView.GONE
        }

        // 선택지 텍스트 또는 이미지를 각 Button 및 ImageButton에 설정
        setupOption(binding.chooseOption1Btn, exam.select1, exam.select1ImgUrl, binding.optionText1.id, binding.optionImage1.id, 1)
        setupOption(binding.chooseOption2Btn, exam.select2, exam.select2ImgUrl, binding.optionText2.id, binding.optionImage2.id, 2)
        setupOption(binding.chooseOption3Btn, exam.select3, exam.select3ImgUrl, binding.optionText3.id, binding.optionImage3.id, 3)
        setupOption(binding.chooseOption4Btn, exam.select4, exam.select4ImgUrl, binding.optionText4.id, binding.optionImage4.id, 4)

        // Reset selection
        selectedAnswer = null
        resetOptionColors()
        Log.d("NewScreenPracticeActivity", "showExam finished")
    }

    private fun setupOption(cardView: CardView, text: String?, imageUrl: String?, textViewId: Int, imageViewId: Int, optionNumber: Int) {
        val textView = cardView.findViewById<TextView>(textViewId)
        val imageView = cardView.findViewById<ImageView>(imageViewId)

        val selectOption: () -> Unit = {
            selectedAnswer = optionNumber
            Log.d("NewScreenPracticeActivity", "Option selected: $optionNumber")
            resetOptionColors()
            cardView.setCardBackgroundColor(Color.parseColor("#FFD231"))  // Selected option color
        }

        textView.setOnClickListener { selectOption() }
        imageView.setOnClickListener { selectOption() }

        if (!text.isNullOrEmpty()) {
            textView.visibility = TextView.VISIBLE
            imageView.visibility = ImageView.GONE
            textView.text = text
        } else if (!imageUrl.isNullOrEmpty()) {
            textView.visibility = TextView.VISIBLE
            imageView.visibility = ImageView.VISIBLE
            textView.text = "$optionNumber."
            Glide.with(this)
                .load(imageUrl)
                .into(imageView)
        } else {
            textView.visibility = TextView.GONE
            imageView.visibility = ImageView.GONE
        }

        // Set default border for CardView
        cardView.setCardBackgroundColor(Color.WHITE)  // Default option color
    }

    private fun resetOptionColors() {
        optionCardViews.forEach {
            it.setCardBackgroundColor(Color.WHITE)  // Default option color
        }
    }

    private fun selectOption(cardView: CardView) {
        optionCardViews.forEach {
            it.setCardBackgroundColor(Color.WHITE)
        }
        cardView.setCardBackgroundColor(Color.parseColor("#FFD231"))
    }
}
