package com.sungkyul.synergy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.sungkyul.synergy.backend.ApiResponse
import com.sungkyul.synergy.backend.auth.AuthAPI
import com.sungkyul.synergy.backend.auth.CheckIdBody
import com.sungkyul.synergy.backend.auth.CheckNicknameBody
import com.sungkyul.synergy.backend.auth.CheckResult
import com.sungkyul.synergy.backend.auth.SignUpBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {

    lateinit var editTextId: EditText
    lateinit var editTextPassword: EditText
    lateinit var editTextRePassword: EditText
    lateinit var editTextNick: EditText
    lateinit var editTextPhone: EditText
    lateinit var btnRegister: Button // ImageButton을 Button으로 변경
    lateinit var btnCheckId: Button
    var checkId: Boolean = false
    lateinit var btnCheckNick: Button
    var checkNick: Boolean = false

    private val authApi: AuthAPI

    init {
        // API 호출하기 위한 세팅
        val retrofit = Retrofit.Builder()
            .baseUrl("https://synergy.hyeonwoo.com/") // 기본 URL 설정
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        this.authApi = retrofit.create(AuthAPI::class.java)
    }

    // POST /user/signup api를 실제로 호출하는 곳
    private suspend fun callSignUpAPI(request: SignUpBody): ApiResponse<Nothing>? {
        return withContext(Dispatchers.IO) {
            val call = authApi.signup(request)
            val response = call.execute()
            response.body()
        }
    }

    // POST /user/check-id api를 실제로 호출하는 곳
    private suspend fun callCheckIdAPI(request: CheckIdBody): ApiResponse<CheckResult>? {
        return withContext(Dispatchers.IO) {
            val call = authApi.checkId(request)
            val response = call.execute()
            response.body()
        }
    }

    // POST /user/check-nickname api를 실제로 호출하는 곳
    private suspend fun callCheckNicknameAPI(request: CheckNicknameBody): ApiResponse<CheckResult>? {
        return withContext(Dispatchers.IO) {
            val call = authApi.checkNickname(request)
            val response = call.execute()
            response.body()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editTextId = findViewById(R.id.editTextId_Reg)
        editTextPassword = findViewById(R.id.editTextPass_Reg)
        editTextRePassword = findViewById(R.id.editTextRePass_Reg)
        editTextNick = findViewById(R.id.editTextNick_Reg)
        editTextPhone = findViewById(R.id.editTextPhone_Reg)
        btnRegister = findViewById(R.id.btnRegister_Reg) // ImageButton을 Button으로 변경
        btnCheckId = findViewById(R.id.btnCheckId_Reg)
        btnCheckNick = findViewById(R.id.btnCheckNick_Reg)

        btnCheckId.setOnClickListener {
            val user = editTextId.text.toString()

            if (user.isEmpty()) {
                Toast.makeText(this@RegisterActivity, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    val body = CheckIdBody(user)
                    val res = callCheckIdAPI(body)

                    if (res?.success == true && res.data.available) {
                        checkId = true
                        Toast.makeText(this@RegisterActivity, "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@RegisterActivity, res?.err?.msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btnCheckNick.setOnClickListener {
            val nick = editTextNick.text.toString()

            if (nick.isEmpty()) {
                Toast.makeText(this@RegisterActivity, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    val body = CheckNicknameBody(nick)
                    val res = callCheckNicknameAPI(body)

                    if (res?.success == true && res.data.available) {
                        checkNick = true
                        Toast.makeText(this@RegisterActivity, "사용 가능한 닉네임입니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@RegisterActivity, res?.err?.msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btnRegister.setOnClickListener {
            val user = editTextId.text.toString()
            val pass = editTextPassword.text.toString()
            val repass = editTextRePassword.text.toString()
            val nick = editTextNick.text.toString()
            val phone = editTextPhone.text.toString()

            if (user.isEmpty() || pass.isEmpty() || repass.isEmpty() || nick.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this@RegisterActivity, "회원정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                if (checkId) {
                    if (checkNick) {
                        CoroutineScope(Dispatchers.Main).launch {
                            val body = SignUpBody(user, pass, repass, nick, phone)
                            val res = callSignUpAPI(body)

                            if (res?.success == true) {
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "가입되었습니다.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent = Intent(applicationContext, LoginActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    this@RegisterActivity,
                                    res?.err?.msg,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } else {
                        Toast.makeText(this@RegisterActivity, "닉네임 중복확인을 해주세요.", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(this@RegisterActivity, "아이디 중복확인을 해주세요.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}
