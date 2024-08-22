package com.sungkyul.synergy.training_space.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.learning_space.ResultPair
import com.sungkyul.synergy.databinding.ActivityExamResultListBinding
import com.sungkyul.synergy.training_space.adapter.ExamResultListAdapter
import com.sungkyul.synergy.training_space.adapter.ExamResultListData
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.backend.ApiResponse
import com.sungkyul.synergy.backend.auth.AuthAPI
import com.sungkyul.synergy.training_space.screen.PracticeCheckExamActivity
import com.sungkyul.synergy.types.ExamListResponse
import com.sungkyul.synergy.types.Exam
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ExamResultListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExamResultListBinding
    private lateinit var authAPI: AuthAPI
    private lateinit var token: String
    private var examList: List<Exam> = emptyList()
    private var receivedId: Int = -1
    private var source: String? = null  // Source 변수 추가

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamResultListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Intent에서 source를 받아옵니다.
        source = intent.getStringExtra("source")

        val resultList = intent.getParcelableArrayListExtra<ResultPair>("resultList")
        val totalQuestions = intent.getIntExtra("totalQuestions", -1)

        Log.d("ExamResultListActivity", "Received totalQuestions: $totalQuestions")
        Log.d("ExamResultListActivity", "Received resultList: $resultList")

        val sharedPreferences = getSharedPreferences("SynergyPrefs", MODE_PRIVATE)
        token = sharedPreferences.getString("Token", null) ?: run {
            finish()
            return
        }

        receivedId = intent.getIntExtra("receivedId", -1)
        Log.d("ExamResultListActivity", "Received receivedId: $receivedId")

        val logging = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://sng.hyeonwoo.com/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        authAPI = retrofit.create(AuthAPI::class.java)
        fetchExamList(resultList)
    }

    private fun fetchExamList(resultList: ArrayList<ResultPair>?) {
        authAPI.getExamList().enqueue(object : Callback<ApiResponse<ExamListResponse>> {
            override fun onResponse(
                call: Call<ApiResponse<ExamListResponse>>,
                response: Response<ApiResponse<ExamListResponse>>
            ) {
                if (response.isSuccessful) {
                    examList = response.body()?.data?.exams ?: emptyList()
                    setupRecyclerView(resultList)
                } else {
                    Log.e("API_ERROR", "Failed to fetch data: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse<ExamListResponse>>, t: Throwable) {
                Log.e("API_ERROR", "Error fetching data: ${t.message}")
            }
        })
    }

    private fun setupRecyclerView(resultList: ArrayList<ResultPair>?) {
        val dataSet = ArrayList<ExamResultListData>()
        if (resultList != null) {
            for (result in resultList) {
                val (questionNumber, isCorrect) = result
                val exam = examList.firstOrNull { it.id == questionNumber }
                val questionText = exam?.question ?: "Unknown question"
                val resultText = if (isCorrect) "맞았습니다" else "틀렸습니다"
                val resultImage = if (isCorrect) R.drawable.correct else R.drawable.wrong
                dataSet.add(ExamResultListData(resultImage, "$questionText - $resultText"))
            }
        }

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ExamResultListAdapter(dataSet) { position ->
            Log.d("ExamResultListActivity", "Item clicked at position: $position")

            val selectedExamResult = resultList?.get(position)
            val intent = Intent(this, PracticeCheckExamActivity::class.java).apply {
                putParcelableArrayListExtra("resultList", resultList)
                putExtra("selectedExamResult", selectedExamResult)
                putExtra("questionNumber", selectedExamResult?.questionNumber)
                putExtra("position", position)
                putExtra("receivedId", receivedId)
            }
            startActivity(intent)
        }

        // "돌아가기" 버튼 이벤트 처리
        binding.backButton.setOnClickListener {
            when (source) {
                "MyExamResultFragment" -> {
                    // MyExamResultFragment에서 왔을 경우
                    finish()  // 그냥 종료
                }
                "ExamResultTestActivity" -> {
                    // ExamResultTestActivity에서 왔을 경우
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("fragment", "SolvingFragment")
                    startActivity(intent)
                }
                else -> {
                    finish()  // 기본 동작으로 종료
                }
            }
        }

        // "다시 풀기" 버튼 이벤트 처리
        binding.viewAllButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("fragment", "SolvingFragment")
            startActivity(intent)
        }
    }
}
