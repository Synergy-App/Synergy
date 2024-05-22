package com.sungkyul.synergy.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sungkyul.synergy.databinding.ActivityGptTestBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("data")
    fun post(@Body data: Data): Call<String>
}

data class Data(
    var prompt: String
)

object RetrofitClient {
    private const val BASE_URL = "http://localhost:8080/"

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}

// 데이터를 서버에 POST로 보낸다.
fun postData(data: Data) {
    RetrofitClient.instance.post(data).enqueue(object: Callback<String> {
        override fun onResponse(call: Call<String>, response: Response<String>) {
            if(response.isSuccessful) {
                Log.i("Response", response.body()!!)
            } else {
                Log.i("Response", response.errorBody()!!.string())
            }
        }

        override fun onFailure(call: Call<String>, t: Throwable) {
            Log.i("Response", t.message!!)
        }
    })
}

class GPTTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGptTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGptTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postData(Data("Synergy"))
    }
}
