package com.sungkyul.synergy.home.activity

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.backend.auth.AuthAPI

class APItestActivity : AppCompatActivity() {

    private lateinit var authApi: AuthAPI
    private lateinit var textViewResult: TextView

/*    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apitest)

        textViewResult = findViewById(R.id.textViewResult)

        // Retrofit 초기화
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://sng.hyeonwoo.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        authApi = retrofit.create(AuthAPI::class.java)

        // API 호출
        fetchUserInfo()
    }

    private fun fetchUserInfo() {
        CoroutineScope(Dispatchers.Main).launch {
            val token = "your_api_token_here" // 여기에 실제 API 토큰을 넣어주세요.
            val response = withContext(Dispatchers.IO) {
                try {
                    authApi.getUserInfo("Bearer $token").execute()
                } catch (e: Exception) {
                    Log.e("APItestActivity", "API 호출 오류: ${e.message}")
                    null
                }
            }

            response?.let {
                if (it.isSuccessful && it.body()?.success == true) {
                    val userInfo = it.body()?.data
                    textViewResult.text = "User Info: ${userInfo?.name}"
                } else {
                    textViewResult.text = "API 호출 실패: ${it.message()}"
                }
            } ?: run {
                textViewResult.text = "API 호출에 실패했습니다."
            }
        }
    }*/
}