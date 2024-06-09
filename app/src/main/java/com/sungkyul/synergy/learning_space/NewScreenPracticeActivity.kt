package com.sungkyul.synergy.learning_space

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.sungkyul.synergy.learning_space.activity.ExamResultListActivity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.sungkyul.synergy.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.backend.ApiResponse
import com.sungkyul.synergy.backend.auth.AuthAPI
import com.sungkyul.synergy.databinding.ActivityNewScreenPracticeBinding
import com.sungkyul.synergy.com.sungkyul.synergy.learning_space.ResultPair
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
    private var correctAnswers = 0 // 맞춘 문제 수를 저장하는 변수
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

    private val resultList = ArrayList<ResultPair>() // 문제 번호와 결과를 저장하는 리스트

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
                Log.d("NewScreenPracticeActivity", "No answer selected")
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
        Log.d("NewScreenPracticeActivity", "Checking answer: $answer for examId: $examId with token: Bearer $token")

        authAPI.checkExamAnswer("Bearer $token", examId, answerBody).enqueue(object : Callback<ApiResponse<ExamCheckResult>> {
            override fun onResponse(
                call: Call<ApiResponse<ExamCheckResult>>,
                response: Response<ApiResponse<ExamCheckResult>>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()?.data
                    if (result != null) {
                        Log.d("NewScreenPracticeActivity", "Check result: ${result.correct}")
                        if (result.correct) {
                            correctAnswers++ // 정답일 경우 카운트를 증가시킴
                            Toast.makeText(this@NewScreenPracticeActivity, "정답이에요!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@NewScreenPracticeActivity, "틀렸어요!", Toast.LENGTH_SHORT).show()
                        }
                        // 결과 리스트에 추가
                        resultList.add(ResultPair(currentExamIndex + 1, result.correct))
                        showNextExam()
                    } else {
                        Toast.makeText(this@NewScreenPracticeActivity, "정답을 선택하세요!", Toast.LENGTH_SHORT).show()
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
            // 문제가 더 이상 없으면 MainActivity로 이동하고, 결과를 ExamResultFragment에 전달
            val intent = Intent(this, ExamResultListActivity::class.java).apply {
                putParcelableArrayListExtra("resultList", ArrayList(resultList))
            }
            startActivity(intent)
            finish() // 현재 Activity를 종료
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

        // 선택된 옵션 초기화
        selectedAnswer = null
        for (cardView in optionCardViews) {
            cardView.setCardBackgroundColor(Color.WHITE)
        }
        Log.d("NewScreenPracticeActivity", "showExam finished")
    }

    private fun setupOption(optionCard: CardView, optionText: String?, optionImageUrl: String?, textViewId: Int, imageViewId: Int, optionNumber: Int) {
        val optionTextView = optionCard.findViewById<TextView>(textViewId)
        val optionImageView = optionCard.findViewById<ImageView>(imageViewId)

        if (!optionText.isNullOrEmpty()) {
            optionTextView.text = optionText
            optionTextView.visibility = TextView.VISIBLE
            optionImageView.visibility = ImageView.GONE
        } else if (!optionImageUrl.isNullOrEmpty()) {
            Glide.with(this)
                .load(optionImageUrl)
                .into(optionImageView)
            optionTextView.visibility = TextView.GONE
            optionImageView.visibility = ImageView.VISIBLE
        } else {
            optionTextView.visibility = TextView.GONE
            optionImageView.visibility = ImageView.GONE
        }

        optionCard.tag = optionNumber
    }

    private fun selectOption(cardView: CardView) {
        selectedAnswer = cardView.tag as Int
        for (view in optionCardViews) {
            view.setCardBackgroundColor(if (view == cardView) Color.YELLOW else Color.WHITE)
        }
    }
}
