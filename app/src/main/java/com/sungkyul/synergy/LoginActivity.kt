package com.sungkyul.synergy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

    private val authApi: AuthAPI

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
                } else {
                    Log.e("LoginActivity", "callSignInAPI Failed: ${response.errorBody()?.string()}")
                }
                response.body()
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
                        // TODO: 앱 안에 영구적으로 저장할 수 있는 곳에 res.data.accessToken을 저장해야함. 나중에 다른 API 호출할 때 이 accessToken을 함께 서버에 넘겨주기 때문.

                        Toast.makeText(this@LoginActivity, "로그인 되었습니다.", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                    // 로그인 실패 시
                    else {
                        Log.e("LoginActivity", "SignIn Error: ${res?.err?.msg}")
                        Toast.makeText(this@LoginActivity, res?.err?.msg ?: "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
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
}
