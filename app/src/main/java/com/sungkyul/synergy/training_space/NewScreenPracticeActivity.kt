package com.sungkyul.synergy.training_space

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.sungkyul.synergy.training_space.activity.ExamResultTestActivity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.google.gson.Gson
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
import java.text.SimpleDateFormat
import java.util.*

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
    private var receivedId: Int = -1

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

        // 이전 액티비티에서 receivedId를 받아옵니다.
        receivedId = sharedPreferences.getInt("receivedId", -1)

        Log.d("NewScreenPracticeActivity", "Received receivedId: $receivedId")

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
            .baseUrl("https://sng.hyeonwoo.com/")
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
                Toast.makeText(this, "정답을 선택해주세요.", Toast.LENGTH_SHORT).show()
                Log.d("NewScreenPracticeActivity", "No answer selected")
            }
        }

        optionCardViews = cardViewIds.map { findViewById<CardView>(it) }.toList()

        for (cardView in optionCardViews) {
            cardView.setOnClickListener { selectOption(cardView) }
        }

        // 백 버튼
        binding.backBtn.setOnClickListener {
            if (currentExamIndex > 0) {
                currentExamIndex--
            } else if (currentExamIndex == 0) {
                Toast.makeText(this, "첫 번째 퀴즈입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            showExam(currentExamIndex)
        }
    }

    private fun fetchExamList() {
        authAPI.getExamList().enqueue(object : Callback<ApiResponse<ExamListResponse>> {
            override fun onResponse(
                call: Call<ApiResponse<ExamListResponse>>,
                response: Response<ApiResponse<ExamListResponse>>
            ) {
                if (response.isSuccessful) {
                    val allExams = response.body()?.data?.exams ?: emptyList()

                    // receivedId와 일치하는 educationId를 가진 문제들만 필터링
                    examList = allExams.filter { it.educationId == receivedId }

                    if (examList.isNotEmpty()) {
                        currentExamIndex = 0
                        showExam(currentExamIndex)
                    } else {
                        Toast.makeText(this@NewScreenPracticeActivity, "해당 교육 ID에 대한 문제가 없습니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    }
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

    private fun updateDigitalAge() {
        val currentDigitalAgeGrade = sharedPreferences.getString("DigitalAgeGrade", "old")
        val newDigitalAgeGrade = when (currentDigitalAgeGrade) {
            "old" -> "parent"
            "parent" -> "adult"
            "adult" -> "student"
            "student" -> "baby"
            else -> "old"
        }
        sharedPreferences.edit().putString("DigitalAgeGrade", newDigitalAgeGrade).apply()
        Log.d("NewScreenPracticeActivity", "Digital age updated from $currentDigitalAgeGrade to $newDigitalAgeGrade")

        // 저장된 값 확인 로그 추가
        val updatedDigitalAgeGrade = sharedPreferences.getString("DigitalAgeGrade", null)
        Log.d("NewScreenPracticeActivity", "Stored DigitalAgeGrade: $updatedDigitalAgeGrade")
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
                        Log.d("NewScreenPracticeActivity", "Correct answer: ${result.correctAnswer}")
                        Log.d("NewScreenPracticeActivity", "Explanation: ${result.explanation}")


                        if (result.correct) {
                            correctAnswers++ // 정답일 경우 카운트를 증가시킴
                        }

                        // 교육 ID를 포함하여 결과 리스트에 추가
                        val resultPair = ResultPair(
                            currentExamIndex + 1,
                            result.correct,
                            answer,
                            result.answerOnSelect,
                            receivedId // educationId 추가
                        )

                        Log.d("NewScreenPracticeActivity", "Adding ResultPair: $resultPair")

                        resultList.add(resultPair)
                        saveResultListToSharedPreferences(resultList) // 결과 리스트를 SharedPreferences에 저장
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

    private fun saveResultListToSharedPreferences(resultList: ArrayList<ResultPair>) {
        val gson = Gson()
        val jsonResultList = gson.toJson(resultList)
        val editor = sharedPreferences.edit()
        // 교육 ID를 사용하여 저장
        editor.putString("resultList_$receivedId", jsonResultList)
        editor.apply()

        Log.d("NewScreenPracticeActivity", "Saved resultList to SharedPreferences: $jsonResultList")
    }

    private fun saveDateToSharedPreferences() {
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        sharedPreferences.edit().putString("lastQuizDate_$receivedId", currentDate).apply()
        Log.d("NewScreenPracticeActivity", "Date saved: $currentDate for educationId: $receivedId")
    }

    private fun showNextExam() {
        Log.d("NewScreenPracticeActivity", "showNextExam called")
        if (currentExamIndex < examList.size - 1) {
            currentExamIndex++
            showExam(currentExamIndex)
        } else {
            updateDigitalAge()
            saveDateToSharedPreferences() // 문제를 모두 푼 후 날짜 저장
            // 문제가 더 이상 없으면 ExamResultTestActivity로 이동하여 맞춘 문제 수를 보여줌
            Log.d("NewScreenPracticeActivity", "Received receivedId: $receivedId")
            val intent = Intent(this, ExamResultTestActivity::class.java).apply {
                putParcelableArrayListExtra("resultList", ArrayList(resultList))
                putExtra("totalQuestions", examList.size)
                putExtra("educationId", receivedId) // 교육 ID 추가
                putExtra("receivedId", receivedId) // 교육 ID 추가
            }
            Log.d("NewScreenPracticeActivity", "done Received receivedId: $receivedId")

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

        // 선택지의 수에 따라 가시성 설정
        binding.chooseOption1Btn.visibility = if (exam.select1.isNullOrEmpty() && exam.select1ImgUrl.isNullOrEmpty()) CardView.GONE else CardView.VISIBLE
        binding.chooseOption2Btn.visibility = if (exam.select2.isNullOrEmpty() && exam.select2ImgUrl.isNullOrEmpty()) CardView.GONE else CardView.VISIBLE
        binding.chooseOption3Btn.visibility = if (exam.select3.isNullOrEmpty() && exam.select3ImgUrl.isNullOrEmpty()) CardView.GONE else CardView.VISIBLE
        binding.chooseOption4Btn.visibility = if (exam.select4.isNullOrEmpty() && exam.select4ImgUrl.isNullOrEmpty()) CardView.GONE else CardView.VISIBLE

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
            view.setCardBackgroundColor(if (view == cardView)  Color.parseColor("#FFD231") else Color.WHITE)
        }
    }
}
