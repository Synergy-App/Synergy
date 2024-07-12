package com.sungkyul.synergy.home.activity

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.sungkyul.synergy.R
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
    lateinit var imageView: ImageView
    lateinit var textViewWelcome: TextView

    private val authApi: AuthAPI

    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_NAME = "SynergyPrefs"
    private val PREF_ID = "SavedId"
    private val PREF_PASSWORD = "SavedPassword" // 비밀번호 저장 키 추가
    private val PREF_AUTOLOGIN = "AutoLogin"
    private val PREF_TOKEN = "Token"
    private val PREF_NICKNAME = "Nickname"

    private var standardSize_X = 0
    private var standardSize_Y = 0
    private var density = 0f

    init {
        // API 호출하기 위한 세팅
        val retrofit = Retrofit.Builder()
            .baseUrl("https://sng.hyeonwoo.com/") // 기본 URL 설정
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
                    Log.e(
                        "LoginActivity",
                        "callSignInAPI Failed: ${response.errorBody()?.string()}"
                    )
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
        imageView = findViewById(R.id.imageView)
        textViewWelcome = findViewById(R.id.textView177)

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        // 자동 로그인 체크
        if (sharedPreferences.getBoolean(PREF_AUTOLOGIN, false)) {
            val savedId = sharedPreferences.getString(PREF_ID, "")
            val savedPassword = sharedPreferences.getString(PREF_PASSWORD, "")
            if (!savedId.isNullOrEmpty() && !savedPassword.isNullOrEmpty()) {
                autoLogin(savedId, savedPassword)
            }
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
                        saveLoginInfo(authId, pw, res.data)

                        if (checkBoxAutoLogin.isChecked) {
                            // Save ID and auto-login preference
                            with(sharedPreferences.edit()) {
                                putString(PREF_ID, authId)
                                putString(PREF_PASSWORD, pw)  // 비밀번호 저장
                                putBoolean(PREF_AUTOLOGIN, true)
                                apply()
                            }
                        } else {
                            // Clear auto-login preference
                            with(sharedPreferences.edit()) {
                                remove(PREF_ID)
                                remove(PREF_PASSWORD)  // 비밀번호 제거
                                putBoolean(PREF_AUTOLOGIN, false)
                                apply()
                            }
                        }

                        proceedToNextActivity()
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

        // Adjust the size of the ImageView and TextView
        adjustSizes()
    }

    private fun getScreenSize(): Point {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size
    }

    private fun getStandardSize() {
        val screenSize = getScreenSize()
        density = resources.displayMetrics.density
        standardSize_X = (screenSize.x / density).toInt()
        standardSize_Y = (screenSize.y / density).toInt()
    }

    private fun adjustSizes() {
        getStandardSize()

        // Adjust ImageView size
        val newWidth = (standardSize_X * 1.2).toInt()
        val newHeight = (standardSize_Y / 1.5).toInt()
        imageView.layoutParams.width = newWidth
        imageView.layoutParams.height = newHeight
        imageView.requestLayout()

        // 시너지 교육에 오신걸 환영합니다
        textViewWelcome.textSize = (standardSize_X / 15).toFloat()  // Example text size adjustment
        //아이디를 입력해주세요.
        editTextId.textSize = (standardSize_X / 18).toFloat()
        //비밀번호
        editTextPassword.textSize = (standardSize_X /18).toFloat()
        //로그인
        btnLogin.textSize = (standardSize_X / 18).toFloat()
        //회원가입
        btnRegister.textSize = (standardSize_X / 21).toFloat()

        // Adjust the heights of layouts
        val idLayout = findViewById<ConstraintLayout>(R.id.rounded_bg_layout_id)
        val passwordLayout = findViewById<ConstraintLayout>(R.id.rounded_bg_layout_password)
        val loginButton = findViewById<Button>(R.id.btnLogin)
        val RegisterButton = findViewById<Button>(R.id.btnRegister)

        // Set heights as a fraction of the screen height
        val layoutHeight = (standardSize_Y / 3.85).toInt()
        val RegisterButtonHeight = (standardSize_Y / 5).toInt() // Adjust height for register button

        idLayout.layoutParams.height = layoutHeight
        passwordLayout.layoutParams.height = layoutHeight
        loginButton.layoutParams.height = layoutHeight
        RegisterButton.layoutParams.height = RegisterButtonHeight

        // Apply the changes
        idLayout.requestLayout()
        passwordLayout.requestLayout()
        loginButton.requestLayout()
        RegisterButton.requestLayout()
    }

    private fun saveLoginInfo(authId: String, pw: String, signInResult: SignInResult?) {
        signInResult?.let {
            with(sharedPreferences.edit()) {
                putString(PREF_ID, authId)
                putString(PREF_PASSWORD, pw)  // 비밀번호 저장
                putString(PREF_TOKEN, it.accessToken)
                putString(PREF_NICKNAME, it.user.nickname)
                apply()
            }
            Log.d("LoginActivity", "Token saved: ${it.accessToken}")
            Log.d("LoginActivity", "Nickname saved: ${it.user.nickname}")
        }
    }

    private fun autoLogin(authId: String, pw: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val body = SignInBody(authId, pw)
            val res = callSignInAPI(body)
            Log.d("LoginActivity", "Auto Login Response: $res")

            // 로그인 성공 시
            if (res?.success == true) {
                proceedToNextActivity()
            } else {
                Log.e("LoginActivity", "Auto Login Error: ${res?.err?.msg}")
                Toast.makeText(this@LoginActivity, res?.err?.msg ?: "자동 로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun proceedToNextActivity() {
        Toast.makeText(this@LoginActivity, "로그인 되었습니다.", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@LoginActivity, SplashActivity::class.java)
        startActivity(intent)
        finish()  // 로그인 후 액티비티 종료
    }
}
