package com.sungkyul.synergy

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.backend.ApiResponse
import com.sungkyul.synergy.backend.auth.AuthAPI
import com.sungkyul.synergy.backend.auth.ChangePasswordBody
import com.sungkyul.synergy.backend.auth.ChangePasswordResult
import com.sungkyul.synergy.backend.auth.FindIdBody
import com.sungkyul.synergy.backend.auth.FindIdResult
import com.sungkyul.synergy.backend.auth.VerifyUserBody
import com.sungkyul.synergy.backend.auth.VerifyUserResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FindIdPasswordActivity : AppCompatActivity() {

    private lateinit var editTextPhoneForId: EditText
    private lateinit var btnFindId: Button
    private lateinit var editTextPhoneForPassword: EditText
    private lateinit var editTextIdForPassword: EditText
    private lateinit var editTextNewPassword: EditText
    private lateinit var btnVerifyUser: Button
    private lateinit var btnChangePassword: Button
    private lateinit var layoutChangePassword: LinearLayout

    private val authApi: AuthAPI

    init {
        // API 호출하기 위한 세팅
        val retrofit = Retrofit.Builder()
            .baseUrl("https://synergy.hyeonwoo.com/") // 기본 URL 설정
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        this.authApi = retrofit.create(AuthAPI::class.java)
    }

    // POST /user/find-id api를 실제로 호출하는 곳
    private suspend fun callFindIdAPI(request: FindIdBody): ApiResponse<FindIdResult>? {
        return withContext(Dispatchers.IO) {
            val call = authApi.findIdByPhone(request)
            val response = call.execute()
            response.body()
        }
    }

    // POST /user/verify-user api를 실제로 호출하는 곳
    private suspend fun callVerifyUserAPI(request: VerifyUserBody): ApiResponse<VerifyUserResult>? {
        return withContext(Dispatchers.IO) {
            val call = authApi.verifyUser(request)
            val response = call.execute()
            response.body()
        }
    }

    // POST /user/change-password api를 실제로 호출하는 곳
    private suspend fun callChangePasswordAPI(request: ChangePasswordBody): ApiResponse<ChangePasswordResult>? {
        return withContext(Dispatchers.IO) {
            val call = authApi.changePassword(request)
            val response = call.execute()
            response.body()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_id_password)

        editTextPhoneForId = findViewById(R.id.editTextPhoneForId)
        btnFindId = findViewById(R.id.btnFindId)
        editTextPhoneForPassword = findViewById(R.id.editTextPhoneForPassword)
        editTextIdForPassword = findViewById(R.id.editTextIdForPassword)
        editTextNewPassword = findViewById(R.id.editTextNewPassword)
        btnVerifyUser = findViewById(R.id.btnVerifyUser)
        btnChangePassword = findViewById(R.id.btnChangePassword)
        layoutChangePassword = findViewById(R.id.layoutChangePassword)

        btnFindId.setOnClickListener {
            val phone = editTextPhoneForId.text.toString().trim()

            if (phone.isEmpty()) {
                Toast.makeText(this@FindIdPasswordActivity, "전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    val body = FindIdBody(phone)
                    val res = callFindIdAPI(body)

                    if (res?.success == true) {
                        val userId = res.data.userId
                        Toast.makeText(this@FindIdPasswordActivity, "아이디는 $userId 입니다.", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@FindIdPasswordActivity, res?.err?.msg ?: "아이디 찾기에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btnVerifyUser.setOnClickListener {
            val phone = editTextPhoneForPassword.text.toString().trim()
            val userId = editTextIdForPassword.text.toString().trim()

            if (phone.isEmpty() || userId.isEmpty()) {
                Toast.makeText(this@FindIdPasswordActivity, "전화번호와 아이디를 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    // Verify user before showing change password layout
                    val verifyBody = VerifyUserBody(userId, phone)
                    val verifyRes = callVerifyUserAPI(verifyBody)

                    if (verifyRes?.success == true) {
                        layoutChangePassword.visibility = LinearLayout.VISIBLE
                        Toast.makeText(this@FindIdPasswordActivity, "사용자 인증에 성공했습니다. 비밀번호를 변경하세요.", Toast.LENGTH_LONG).show()
                    } else {
                        layoutChangePassword.visibility = LinearLayout.GONE
                        Toast.makeText(this@FindIdPasswordActivity, verifyRes?.err?.msg ?: "사용자 인증에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btnChangePassword.setOnClickListener {
            val userId = editTextIdForPassword.text.toString().trim()
            val newPassword = editTextNewPassword.text.toString().trim()

            if (newPassword.isEmpty()) {
                Toast.makeText(this@FindIdPasswordActivity, "새 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    val changePasswordBody = ChangePasswordBody(userId, newPassword)
                    val changePasswordRes = callChangePasswordAPI(changePasswordBody)

                    if (changePasswordRes?.success == true) {
                        Toast.makeText(this@FindIdPasswordActivity, "비밀번호가 성공적으로 변경되었습니다.", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@FindIdPasswordActivity, changePasswordRes?.err?.msg ?: "비밀번호 변경에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
