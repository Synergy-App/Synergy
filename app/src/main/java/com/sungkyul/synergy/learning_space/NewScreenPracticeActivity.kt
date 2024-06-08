package com.sungkyul.synergy.learning_space

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.sungkyul.synergy.R
import com.sungkyul.synergy.backend.ApiResponse
import com.sungkyul.synergy.backend.auth.AuthAPI
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
    private lateinit var token: String
    private lateinit var optionCardViews: List<CardView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_screen_practice)

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

        // SharedPreferences에서 토큰 불러오기
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        token = sharedPreferences.getString("token", null) ?: run {
            Toast.makeText(this, "토큰이 없습니다. 다시 로그인 해주세요.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Exam 데이터를 가져오는 함수 호출
        fetchExamList()

        // Next Question 버튼 클릭 리스너 설정
        val nextButton = findViewById<Button>(R.id.button44)
        nextButton.setOnClickListener {
            selectedAnswer?.let {
                checkAnswer(it)
            } ?: run {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
            }
        }

        optionCardViews = listOf(
            findViewById(R.id.choose_option1_btn),
            findViewById(R.id.choose_option2_btn),
            findViewById(R.id.choose_option3_btn),
            findViewById(R.id.choose_option4_btn)
        )
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
        if (currentExamIndex < examList.size - 1) {
            currentExamIndex++
            showExam(currentExamIndex)
        } else {
            Toast.makeText(this, "No more questions available", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showExam(index: Int) {
        val exam = examList[index]
        val questionTextView = findViewById<TextView>(R.id.learning_info_tv)
        questionTextView.text = exam.question

        // descriptionImgUrl 또는 description을 설정
        val descriptionTextView = findViewById<TextView>(R.id.description_text)
        val imageView = findViewById<ImageView>(R.id.icon_image)

        if (!exam.descriptionImgUrl.isNullOrEmpty()) {
            descriptionTextView.visibility = TextView.GONE
            imageView.visibility = ImageView.VISIBLE
            Glide.with(this)
                .load(exam.descriptionImgUrl)
                .into(imageView)
        } else if (!exam.description.isNullOrEmpty()) {
            imageView.visibility = ImageView.GONE
            descriptionTextView.visibility = TextView.VISIBLE
            descriptionTextView.text = exam.description
        } else {
            imageView.visibility = ImageView.GONE
            descriptionTextView.visibility = TextView.GONE
        }

        // 선택지 텍스트 또는 이미지를 각 Button 및 ImageButton에 설정
        setupOption(findViewById(R.id.choose_option1_btn), exam.select1, exam.select1ImgUrl, R.id.option_text1, R.id.option_image1, 1)
        setupOption(findViewById(R.id.choose_option2_btn), exam.select2, exam.select2ImgUrl, R.id.option_text2, R.id.option_image2, 2)
        setupOption(findViewById(R.id.choose_option3_btn), exam.select3, exam.select3ImgUrl, R.id.option_text3, R.id.option_image3, 3)
        setupOption(findViewById(R.id.choose_option4_btn), exam.select4, exam.select4ImgUrl, R.id.option_text4, R.id.option_image4, 4)

        // Reset selection
        selectedAnswer = null
        resetOptionColors()
    }

    private fun setupOption(cardView: CardView, text: String?, imageUrl: String?, textViewId: Int, imageViewId: Int, optionNumber: Int) {
        val textView = cardView.findViewById<Button>(textViewId)
        val imageView = cardView.findViewById<ImageButton>(imageViewId)

        val selectOption: () -> Unit = {
            selectedAnswer = optionNumber
            resetOptionColors()
            cardView.setCardBackgroundColor(0xFFDDDDDD.toInt())  // Light grey color for selected option
            cardView.cardElevation = 8f  // Increase elevation for selected option
        }

        textView.setOnClickListener { selectOption() }
        imageView.setOnClickListener { selectOption() }

        if (!text.isNullOrEmpty()) {
            textView.visibility = Button.VISIBLE
            imageView.visibility = ImageButton.GONE
            textView.text = text
        } else if (!imageUrl.isNullOrEmpty()) {
            textView.visibility = Button.VISIBLE
            imageView.visibility = ImageButton.VISIBLE
            textView.text = "$optionNumber."
            Glide.with(this)
                .load(imageUrl)
                .into(imageView)
        } else {
            textView.visibility = Button.GONE
            imageView.visibility = ImageButton.GONE
        }

        // Set default border for CardView
        cardView.setCardBackgroundColor(0xFFFFFFFF.toInt())  // White color for default option
        cardView.cardElevation = 2f  // Default elevation
    }

    private fun resetOptionColors() {
        optionCardViews.forEach {
            it.setCardBackgroundColor(0x2193F3.toInt())  // White color for default option
            it.cardElevation = 2f  // Default elevation

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityExamResultListBinding
import com.sungkyul.synergy.databinding.ActivityNewScreenPracticeBinding

class NewScreenPracticeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewScreenPracticeBinding


    private lateinit var optionButtons: Array<CardView>
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

        optionButtons = cardViewIds.map { findViewById<CardView>(it) }.toTypedArray()
        for (cardView in optionButtons) {
            cardView.setOnClickListener { selectOption(cardView) }
        }

        // 백 버튼
        binding.backBtn.setOnClickListener {
//            val intent = Intent(this, NewScreenPracticeActivity::class.java)
//            startActivity(intent)
        }
        // 다음 버튼
        binding.backBtn.setOnClickListener {
//            val intent = Intent(this, NewScreenPracticeActivity::class.java)
//            startActivity(intent)
        }
    }
    private fun selectOption(selectedCardView: CardView) {
        for (cardView in optionButtons) {
            if (cardView == selectedCardView) {
                cardView.setCardBackgroundColor(Color.parseColor("#FFD231"))
            } else {
                cardView.setCardBackgroundColor(Color.WHITE)
            }

    }
}
