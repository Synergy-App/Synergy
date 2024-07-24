package com.sungkyul.synergy.home.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.backend.ApiResponse
import com.sungkyul.synergy.backend.auth.AuthAPI
import com.sungkyul.synergy.backend.auth.FindIdBody
import com.sungkyul.synergy.backend.auth.FindIdResult
import com.sungkyul.synergy.backend.auth.VerifyUserBody
import com.sungkyul.synergy.backend.auth.VerifyUserResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


inline fun <reified T : Parcelable> Intent.putParcelableExtra(key: String, value: T?) {
    putExtra(key, value)
}
class FindIdPasswordActivity : AppCompatActivity() {

    private lateinit var editTextPhoneForId: EditText
    private lateinit var btnFindId: Button
    private lateinit var editTextPhoneForPassword: EditText
    private lateinit var editTextIdForPassword: EditText
    private lateinit var btnVerifyUser: Button

    private val authApi: AuthAPI

    init {
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

        this.authApi = retrofit.create(AuthAPI::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_id_password)

        editTextPhoneForId = findViewById(R.id.editTextPhoneForId)
        btnFindId = findViewById(R.id.btnFindId)
        editTextPhoneForPassword = findViewById(R.id.editTextPhoneForPassword)
        editTextIdForPassword = findViewById(R.id.editTextIdForPassword)
        btnVerifyUser = findViewById(R.id.btnVerifyUser)

        val textViewFindId: TextView = findViewById(R.id.textViewFindId)
        val textViewChangePassword: TextView = findViewById(R.id.textViewChangePassword)
        updateTextColor(textViewFindId, "아이디 찾기", "아이디", "#CE3232")
        updateTextColor(textViewChangePassword, "비밀번호 변경", "비밀번호", "#CE3232")

        btnFindId.setOnClickListener {
            val phone = editTextPhoneForId.text.toString().trim()

            if (phone.isEmpty()) {
                Toast.makeText(this@FindIdPasswordActivity, "전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    val body = FindIdBody(phone)
                    val res = callFindIdAPI(body)
                    Log.d("FindIdPasswordActivity", "FindId Response: $res")

                    if (res?.success == true) {
                        val userId = res.data?.authId
                        Toast.makeText(
                            this@FindIdPasswordActivity,
                            "아이디는 $userId 입니다.",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Log.e("FindIdPasswordActivity", "FindId Error: ${res?.err?.msg}")
                        Toast.makeText(
                            this@FindIdPasswordActivity,
                            res?.err?.msg ?: "아이디 찾기에 실패하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        btnVerifyUser.setOnClickListener {
            val phone = editTextPhoneForPassword.text.toString().trim()
            val userId = editTextIdForPassword.text.toString().trim()

            if (phone.isEmpty() || userId.isEmpty()) {
                Toast.makeText(
                    this@FindIdPasswordActivity,
                    "전화번호와 아이디를 모두 입력해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    val verifyBody = VerifyUserBody(userId, phone)
                    val verifyRes = callVerifyUserAPI(verifyBody)
                    Log.d("FindIdPasswordActivity", "VerifyUser Response: $verifyRes")

                    if (verifyRes?.success == true) {
                        val resetKey = verifyRes.data?.resetKey
                        if (!resetKey.isNullOrEmpty()) {
                            // resetKey만 Intent에 추가하여 ChangePasswordActivity로 전달
                            val intent = Intent(this@FindIdPasswordActivity, ChangePasswordActivity::class.java)
                            intent.putExtra("resetKey", resetKey) // resetKey를 String으로 추가
                            Log.d("FindIdPasswordActivity", "ResetKey: $resetKey")
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this@FindIdPasswordActivity,
                                "비밀번호 재설정 키가 누락되었습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Log.e("FindIdPasswordActivity", "VerifyUser Error: ${verifyRes?.err?.msg}")
                        Toast.makeText(
                            this@FindIdPasswordActivity,
                            verifyRes?.err?.msg ?: "사용자 인증에 실패했습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }



    }

    // POST /user/find-id api를 실제로 호출하는 곳
    private suspend fun callFindIdAPI(request: FindIdBody): ApiResponse<FindIdResult>? {
        return withContext(Dispatchers.IO) {
            try {
                val call = authApi.findIdByPhone(request)
                val response = call.execute()
                if (response.isSuccessful) {
                    Log.d("FindIdPasswordActivity", "callFindIdAPI Success: ${response.body()}")
                } else {
                    Log.e(
                        "FindIdPasswordActivity",
                        "callFindIdAPI Failed: ${response.errorBody()?.string()}"
                    )
                }
                response.body()
            } catch (e: Exception) {
                Log.e("FindIdPasswordActivity", "callFindIdAPI Exception: ${e.message}")
                null
            }
        }
    }

    // POST /user/verify-user api를 실제로 호출하는 곳
    private suspend fun callVerifyUserAPI(request: VerifyUserBody): ApiResponse<VerifyUserResult>? {
        return withContext(Dispatchers.IO) {
            try {
                val call = authApi.verifyUser(request)
                val response = call.execute()
                if (response.isSuccessful) {
                    Log.d("FindIdPasswordActivity", "callVerifyUserAPI Success: ${response.body()}")
                } else {
                    Log.e(
                        "FindIdPasswordActivity",
                        "callVerifyUserAPI Failed: ${response.errorBody()?.string()}"
                    )
                }
                response.body()
            } catch (e: Exception) {
                Log.e("FindIdPasswordActivity", "callVerifyUserAPI Exception: ${e.message}")
                null
            }
        }
    }

    // 텍스트 색상 변경 함수
    private fun updateTextColor(textView: TextView, fullText: String, targetText: String, color: String) {
        val spannable = SpannableString(fullText)
        val start = fullText.indexOf(targetText)
        val end = start + targetText.length
        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor(color)),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView.text = spannable
    }
}