// ChangePasswordActivity.kt
package com.sungkyul.synergy.home.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.backend.ApiResponse
import com.sungkyul.synergy.backend.auth.AuthAPI
import com.sungkyul.synergy.backend.auth.ChangePasswordBody
import com.sungkyul.synergy.backend.auth.ChangePasswordResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var editTextNewPassword: EditText
    private lateinit var btnChangePassword: Button
    private lateinit var authApi: AuthAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        editTextNewPassword = findViewById(R.id.editTextNewPassword)
        btnChangePassword = findViewById(R.id.btnChangePassword)

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

        btnChangePassword.setOnClickListener {
            val newPassword = editTextNewPassword.text.toString().trim()
            val userId = intent.getStringExtra("authId") ?: return@setOnClickListener

            if (newPassword.isEmpty()) {
                Toast.makeText(this, "새 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                changePassword(userId, newPassword)
            }
        }
    }

    private fun changePassword(authId: String, newPassword: String) {
        val request = ChangePasswordBody(authId, newPassword)
        val call = authApi.changePassword(request)

        call.enqueue(object : Callback<ApiResponse<ChangePasswordResult>> {
            override fun onResponse(
                call: Call<ApiResponse<ChangePasswordResult>>,
                response: Response<ApiResponse<ChangePasswordResult>>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()
                    if (result?.success == true) {
                        Toast.makeText(this@ChangePasswordActivity, "비밀번호가 성공적으로 변경되었습니다.", Toast.LENGTH_LONG).show()
                        finish()
                    } else {
                        Log.e("ChangePasswordActivity", "ChangePassword Error: ${result?.err?.msg}")
                        Toast.makeText(this@ChangePasswordActivity, result?.err?.msg ?: "비밀번호 변경에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("ChangePasswordActivity", "ChangePassword API Error: ${response.errorBody()?.string()}")
                    Toast.makeText(this@ChangePasswordActivity, "비밀번호 변경에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse<ChangePasswordResult>>, t: Throwable) {
                Log.e("ChangePasswordActivity", "ChangePassword API Failure: ${t.message}")
                Toast.makeText(this@ChangePasswordActivity, "비밀번호 변경 중 오류가 발생하였습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
