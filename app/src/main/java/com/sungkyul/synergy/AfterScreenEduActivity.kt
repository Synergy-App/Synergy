package com.sungkyul.synergy

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.backend.ApiResponse
import com.sungkyul.synergy.backend.auth.AuthAPI
import com.sungkyul.synergy.backend.auth.SignInBody
import com.sungkyul.synergy.backend.auth.SignInResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    lateinit var btnLogin: Button
    lateinit var editTextId: EditText
    lateinit var editTextPassword: EditText
    lateinit var btnRegister: Button
    lateinit var btnFind: Button
    lateinit var checkBoxAutoLogin: CheckBox

    private val authApi: AuthAPI

    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_NAME = "SynergyPrefs"
    private val PREF_ID = "SavedId"
    private val PREF_AUTOLOGIN = "AutoLogin"
    private val PREF_TOKEN = "Token"
    private val PREF_NICKNAME = "Nickname"

    init {
        // API 호출하기 위한 세팅
        val retrofit = Retrofit.Builder()
            .baseUrl("https://synergy.hyeonwoo.com/") // 기본 URL 설정
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        this.authApi = retrofit.create(AuthAPI::class.java)
    }

    // POST /user/signin api를 실제로 호출하는 곳
    private suspend fun callSignInAPI(request: SignInBody): ApiResponse<SignInResult>? {
        return withContext(Dispatchers.IO) {
            try {
                val call = authApi.signin(request)
                val response = call.execute()
                if (response.isSuccessful) {
                    Log.d("LoginActivity", "callSignInAPI Success: ${response.body()}")
                    response.body()
                } else {
                    Log.e("LoginActivity", "callSignInAPI Failed: ${response.errorBody()?.string()}")
                    null
                }
            } catch (e: Exception) {
                Log.e("LoginActivity", "callSignInAPI Exception: ${e.message}")
                null
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin = findViewById(R.id.btnLogin)
        editTextId = findViewById(R.id.editTextId)
        editTextPassword = findViewById(R.id.editTextPassword)
        btnRegister = findViewById(R.id.btnRegister)
        btnFind = findViewById(R.id.btnFindIdPassword)
        checkBoxAutoLogin = findViewById(R.id.checkBoxAutoLogin)

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        // 자동로그인
        if (sharedPreferences.getBoolean(PREF_AUTOLOGIN, false)) {
            val savedId = sharedPreferences.getString(PREF_ID, "")
            editTextId.setText(savedId)
            checkBoxAutoLogin.isChecked = true
        }

        // 로그인 버튼 클릭
        btnLogin.setOnClickListener {
            val authId = editTextId.text.toString()
            val pw = editTextPassword.text.toString()

            // 빈칸 제출시 Toast
            if (authId.isEmpty() || pw.isEmpty()) {
                Toast.makeText(this@LoginActivity, "아이디와 비밀번호를 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                // 코루틴을 사용하여 네트워크 작업 실행
                CoroutineScope(Dispatchers.Main).launch {
                    val body = SignInBody(authId, pw)
                    val res = callSignInAPI(body)
                    Log.d("LoginActivity", "SignIn Response: $res")

                    // 로그인 성공 시
                    if (res?.success == true) {
                        // 로그인 성공 시 닉네임과 토큰을 SharedPreferences에 저장
                        saveLoginInfo(res.data)

                        if (checkBoxAutoLogin.isChecked) {
                            // Save ID and auto-login preference
                            with(sharedPreferences.edit()) {
                                putString(PREF_ID, authId)
                                putBoolean(PREF_AUTOLOGIN, true)
                                apply()
                            }
                        } else {
                            // Clear auto-login preference
                            with(sharedPreferences.edit()) {
                                remove(PREF_ID)
                                putBoolean(PREF_AUTOLOGIN, false)
                                apply()
                            }
                        }

                        Toast.makeText(this@LoginActivity, "로그인 되었습니다.", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()  // 로그인 후 액티비티 종료
                    }
                    // 로그인 실패 시
                    else {
                        Log.e("LoginActivity", "SignIn Error: ${res?.err?.msg}")
                        Toast.makeText(
                            this@LoginActivity,
                            res?.err?.msg ?: "로그인에 실패하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        // 회원가입 버튼 클릭시
        btnRegister.setOnClickListener {
            val loginIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(loginIntent)
        }

        // 아이디/비밀번호 찾기 버튼 클릭시
        btnFind.setOnClickListener {
            val findIntent = Intent(this@LoginActivity, FindIdPasswordActivity::class.java)
            startActivity(findIntent)
        }
    }

    private fun saveLoginInfo(signInResult: SignInResult?) {
        signInResult?.let {
            with(sharedPreferences.edit()) {
                putString(PREF_TOKEN, it.accessToken)
                putString(PREF_NICKNAME, it.user.nickname)
                apply()
            }
        }
    }
}
