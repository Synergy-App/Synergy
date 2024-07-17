package com.sungkyul.synergy.com.sungkyul.synergy.home.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.R
import com.sungkyul.synergy.backend.auth.AuthAPI
import com.sungkyul.synergy.home.activity.RegisterActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragmentRegisterNickname : Fragment() {

    private lateinit var editTextNick: EditText
    private lateinit var btnNext: Button
    private lateinit var textView180: TextView
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
            .baseUrl("https://sng.hyeonwoo.com/")
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
        btnNext = view.findViewById(R.id.btnNext_Nickname)
        textView180 = view.findViewById(R.id.textView180)

        // "이름" 텍스트 색상 변경
        val text = "이름을\n입력해주세요."
        val spannable = SpannableString(text)
        val start = text.indexOf("이름")
        val end = start + "이름".length
        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor("#CE3232")),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView180.text = spannable

        editTextNick.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // No need to implement
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No need to implement
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    btnNext.setBackgroundResource(R.drawable.login_border_background_white)
                } else {
                    btnNext.setBackgroundResource(R.drawable.login_border_background_yellow)
                }
            }
        })

        btnNext.setOnClickListener {
            val nickname = editTextNick.text.toString().trim()
            val userId = arguments?.getString(ARG_USER_ID) ?: return@setOnClickListener
            val password = arguments?.getString(ARG_PASSWORD) ?: return@setOnClickListener

            if (nickname.isEmpty()) {
                Toast.makeText(activity, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                (activity as RegisterActivity).navigateToPhoneFragment(userId, password, nickname)
            }
        }

        return view
    }
}
