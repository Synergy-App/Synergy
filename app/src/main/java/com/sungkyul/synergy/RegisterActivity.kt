package com.sungkyul.synergy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.sungkyul.synergy.backend.ApiResponse
import com.sungkyul.synergy.backend.auth.AuthAPI
import com.sungkyul.synergy.backend.auth.CheckIdBody
import com.sungkyul.synergy.backend.auth.CheckNicknameBody
import com.sungkyul.synergy.backend.auth.CheckResult
import com.sungkyul.synergy.backend.auth.SignUpBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {

    lateinit var editTextId: EditText
    lateinit var editTextPassword: EditText
    lateinit var editTextRePassword: EditText
    lateinit var editTextNick: EditText
    lateinit var editTextPhone: EditText
    lateinit var btnRegister: ImageButton
    lateinit var btnCheckId: Button
    var CheckId:Boolean=false
    lateinit var btnCheckNick: Button
    var CheckNick:Boolean=false

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
    fun callSignUpAPI(request: SignUpBody): ApiResponse<Nothing>? {
        val call = this.authApi.signup(request)
        val response = call.execute()
        return response.body()
    }

    // POST /user/check-id api를 실제로 호출하는 곳
    fun callCheckIdAPI(request: CheckIdBody): ApiResponse<CheckResult>? {
        val call = this.authApi.checkId(request)
        val response = call.execute()
        return response.body()
    }

    // POST /user/check-nickname api를 실제로 호출하는 곳
    fun callCheckNicknameAPI(request: CheckNicknameBody): ApiResponse<CheckResult>? {
        val call = this.authApi.checkNickname(request)
        val response = call.execute()
        return response.body()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editTextId = findViewById(R.id.editTextId_Reg)
        editTextPassword = findViewById(R.id.editTextPass_Reg)
        editTextRePassword = findViewById(R.id.editTextRePass_Reg)
        editTextNick = findViewById(R.id.editTextNick_Reg)
        editTextPhone = findViewById(R.id.editTextPhone_Reg)
        btnRegister = findViewById(R.id.btnRegister_Reg)
        btnCheckId = findViewById(R.id.btnCheckId_Reg)
        btnCheckNick = findViewById(R.id.btnCheckNick_Reg)


        btnCheckId.setOnClickListener {
            val user = editTextId.text.toString()

            if (user == "") {
                Toast.makeText(
                    this@RegisterActivity,
                    "아이디를 입력해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {

                // POST /user/check-id api 연동

                // api에 전달할 데이터
                val body = CheckIdBody(user)
                // api 호출 -> res에 응답데이터 저장
                val res = callCheckIdAPI(body)

                if (res?.success == true && res.data.available) {
                    CheckId = true
                    Toast.makeText(this@RegisterActivity, "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this@RegisterActivity, res?.err?.msg, Toast.LENGTH_SHORT).show()
                }

            }
        }

        // 닉네임 중복확인
        btnCheckNick.setOnClickListener {
            val nick = editTextNick.text.toString()

            if (nick == "") {
                Toast.makeText(
                    this@RegisterActivity,
                    "닉네임을 입력해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {

                // POST /user/check-nickname api 연동

                // api에 전달할 데이터
                val body = CheckNicknameBody(nick)
                // api 호출 -> res에 응답데이터 저장
                val res = callCheckNicknameAPI(body)

                if (res?.success == true && res.data.available) {
                    CheckNick = true
                    Toast.makeText(this@RegisterActivity, "사용 가능한 닉네임입니다.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this@RegisterActivity, res?.err?.msg, Toast.LENGTH_SHORT).show()
                }

            }
        }

        // 완료 버튼 클릭 시
        btnRegister.setOnClickListener {
            val user = editTextId.text.toString()
            val pass = editTextPassword.text.toString()
            val repass = editTextRePassword.text.toString()
            val nick = editTextNick.text.toString()
            val phone = editTextPhone.text.toString()

            // 사용자 입력이 비었을 때
            if (user == "" || pass == "" || repass == "" || nick == "" || phone == "") Toast.makeText(
                this@RegisterActivity,
                "회원정보를 모두 입력해주세요.",
                Toast.LENGTH_SHORT
            ).show()
            else {
                // 아이디 중복 확인이 됐을 때
                if (CheckId) {
                            // 닉네임 중복확인이 됐을 때
                            if (CheckNick) {

                                // POST /user/signup api 호출

                                // api에 전달할 데이터
                                val body = SignUpBody(user, pass, repass, nick, phone)
                                // api 호출 -> res에 응답데이터 저장
                                val res = callSignUpAPI(body)

                                // 가입 성공 시 Toast를 띄우고 메인 화면으로 전환
                                if (res?.success == true) {
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        "가입되었습니다.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val intent =
                                        Intent(applicationContext, MainActivity::class.java)
                                    startActivity(intent)
                                }
                                // 가입 실패 시
                                else {
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        res?.err?.msg,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }


                            }
                            // 닉네임 중복확인 하지 않았을 때
                            else {
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "닉네임 중복확인을 해주세요.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }


                }
                // 아이디 중복확인이 되지 않았을 때
                else {
                    Toast.makeText(this@RegisterActivity, "아이디 중복확인을 해주세요.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }
}