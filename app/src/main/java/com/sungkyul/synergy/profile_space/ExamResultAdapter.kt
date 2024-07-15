package com.sungkyul.synergy.profile_space

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.training_space.activity.ExamResultListActivity
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

class ExamResultAdapter(
    private val examResults: List<ExamResult>,
    private val educationId: Int,
    private val totalQuestions: Int
) : RecyclerView.Adapter<ExamResultAdapter.ExamResultViewHolder>() {

    class ExamResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewExamTitle: TextView = itemView.findViewById(R.id.textViewExamTitle)
        val textViewExamTitle2: TextView = itemView.findViewById(R.id.textViewExamTitle2)
        val textViewExamResult: TextView = itemView.findViewById(R.id.textViewExamResult)
        val imageViewExamIcon: ImageView = itemView.findViewById(R.id.imageViewExamIcon)
    }

    private lateinit var authAPI: AuthAPI
    private var examList: List<Exam> = emptyList()

    init {
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
        fetchExamList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamResultViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_result_exam, parent, false)
        return ExamResultViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExamResultViewHolder, position: Int) {
        val currentItem = examResults[position]
        holder.textViewExamTitle.text = currentItem.title
        holder.textViewExamTitle2.text = currentItem.title2
        holder.textViewExamResult.text = currentItem.result
        holder.imageViewExamIcon.setImageResource(currentItem.imageResId)

        holder.imageViewExamIcon.setOnClickListener {
            Log.d("ExamResultAdapter", "ImageView clicked for position: $position")
            val context = holder.itemView.context
            val intent = Intent(context, ExamResultListActivity::class.java).apply {
                putParcelableArrayListExtra("resultList", ArrayList(currentItem.resultList))
                putExtra("educationId", educationId)
                putExtra("totalQuestions", totalQuestions)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = examResults.size

    private fun fetchExamList() {
        authAPI.getExamList().enqueue(object : Callback<ApiResponse<ExamListResponse>> {
            override fun onResponse(
                call: Call<ApiResponse<ExamListResponse>>,
                response: Response<ApiResponse<ExamListResponse>>
            ) {
                if (response.isSuccessful) {
                    examList = response.body()?.data?.exams ?: emptyList()
                    notifyDataSetChanged()
                } else {
                    Log.e("API_ERROR", "Failed to fetch data: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse<ExamListResponse>>, t: Throwable) {
                Log.e("API_ERROR", "Error fetching data: ${t.message}")
            }
        })
    }
}
