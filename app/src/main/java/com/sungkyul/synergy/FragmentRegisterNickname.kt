package com.sungkyul.synergy.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.backend.ApiResponse
import com.sungkyul.synergy.backend.auth.AuthAPI
import com.sungkyul.synergy.backend.auth.SignUpBody
import com.sungkyul.synergy.backend.auth.SignInBody
import com.sungkyul.synergy.backend.auth.SignInResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragmentRegisterNickname : Fragment() {

    private lateinit var editTextNick: EditText
    private lateinit var btnRegister: Button
    private lateinit var authApi: AuthAPI

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://synergy.hyeonwoo.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        authApi = retrofit.create(AuthAPI::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register_nickname, container, false)

        editTextNick = view.findViewById(R.id.editTextNick)
        btnRegister = view.findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val nickname = editTextNick.text.toString().trim()
            val userId = arguments?.getString(ARG_USER_ID) ?: return@setOnClickListener
            val password = arguments?.getString(ARG_PASSWORD) ?: return@setOnClickListener

            if (nickname.isEmpty()) {
                Toast.makeText(activity, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    registerAndLogin(userId, password, nickname)
                }
            }
        }

        return view
    }

    // 로그인 API 호출
    private suspend fun callSignInAPI(request: SignInBody): ApiResponse<SignInResult>? {
        return withContext(Dispatchers.IO) {
            try {
                val call = authApi.signin(request)
                val response = call.execute()
                if (response.isSuccessful) {
                    response.body()
                } else {
                    Log.e("FragmentRegisterNickname", "SignIn API Error: ${response.errorBody()?.string()}")
                    null
                }
            } catch (e: Exception) {
                Log.e("FragmentRegisterNickname", "SignIn API Exception: ${e.message}")
                null
            }
        }
    }

    // 회원가입 후 로그인 및 사용자 정보 저장
    private suspend fun registerAndLogin(userId: String, password: String, nickname: String) {
        withContext(Dispatchers.IO) {
            val signUpBody = SignUpBody(userId, password, password, nickname, "00000000000")
            val signUpResponse = authApi.signup(signUpBody).execute()

            if (signUpResponse.isSuccessful) {
                val responseBody = signUpResponse.body()
                if (responseBody?.success == true) {
                    Log.d("FragmentRegisterNickname", "SignUp Success")
                    val signInBody = SignInBody(userId, password)
                    val signInResponse = callSignInAPI(signInBody)
                    if (signInResponse?.success == true) {
                        withContext(Dispatchers.Main) {
                            saveUserInfoToSharedPreferences(signInResponse.data)
                            val intent = Intent(activity, MainActivity::class.java)
                            startActivity(intent)
                            activity?.finish()
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(activity, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Log.e("FragmentRegisterNickname", "SignUp Error: ${responseBody?.err?.msg}")
                    withContext(Dispatchers.Main) {
                        Toast.makeText(activity, responseBody?.err?.msg ?: "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Log.e("FragmentRegisterNickname", "SignUp API Error: ${signUpResponse.errorBody()?.string()}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(activity, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun saveUserInfoToSharedPreferences(signInResult: SignInResult) {
        val sharedPreferences = activity?.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putString("token", signInResult.accessToken)
        editor?.putString("nickname", signInResult.user.nickname)
        editor?.apply()
        Log.d("FragmentRegisterNickname", "User Info Saved: ${signInResult.user.nickname}")
    }
}
