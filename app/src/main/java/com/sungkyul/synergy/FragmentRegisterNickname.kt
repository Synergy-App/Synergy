package com.sungkyul.synergy.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.LoginActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.backend.ApiResponse
import com.sungkyul.synergy.backend.auth.AuthAPI
import com.sungkyul.synergy.backend.auth.SignUpBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragmentRegisterNickname : Fragment() {

    private lateinit var editTextNick: EditText
    private lateinit var btnRegister: Button

    companion object {
        private const val ARG_USER_ID = "user_id"
        private const val ARG_PASSWORD = "password"

        fun newInstance(userId: String, password: String): FragmentRegisterNickname {
            val fragment = FragmentRegisterNickname()
            val args = Bundle()
            args.putString(ARG_USER_ID, userId)
            args.putString(ARG_PASSWORD, password)
            fragment.arguments = args
            return fragment
        }
    }

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
            try {
                val call = authApi.signup(request)
                val response = call.execute()
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register_nickname, container, false)

        editTextNick = view.findViewById(R.id.editTextNick)
        btnRegister = view.findViewById(R.id.btnRegister)

        editTextNick.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // No need to implement
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No need to implement
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    btnRegister.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
                } else {
                    btnRegister.setBackgroundColor(resources.getColor(android.R.color.holo_orange_light))
                }
            }
        })

        btnRegister.setOnClickListener {
            val nickname = editTextNick.text.toString().trim()
            val userId = arguments?.getString(ARG_USER_ID) ?: return@setOnClickListener
            val password = arguments?.getString(ARG_PASSWORD) ?: return@setOnClickListener

            if (nickname.isEmpty()) {
                Toast.makeText(activity, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    // 비밀번호 확인을 패스워드와 동일하게 설정하고 휴대폰 번호는 임의의 값으로 설정
                    val body = SignUpBody(userId, password, password, nickname, "00000000000")
                    val res = callSignUpAPI(body)

                    if (res?.success == true) {
                        Toast.makeText(activity, "가입되었습니다.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(activity, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(activity, res?.err?.msg ?: "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        return view
    }
}
