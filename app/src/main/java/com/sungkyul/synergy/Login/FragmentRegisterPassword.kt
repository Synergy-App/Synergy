package com.sungkyul.synergy.com.sungkyul.synergy.Login

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.R

class FragmentRegisterPassword : Fragment() {

    private lateinit var editTextPassword: EditText
    private lateinit var btnNext: Button
    private lateinit var textView180: TextView

    companion object {
        private const val ARG_USER_ID = "user_id"

        fun newInstance(userId: String): FragmentRegisterPassword {
            val fragment = FragmentRegisterPassword()
            val args = Bundle()
            args.putString(ARG_USER_ID, userId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register_password, container, false)

        editTextPassword = view.findViewById(R.id.editTextPassword)
        btnNext = view.findViewById(R.id.btnNext_Password)
        textView180 = view.findViewById(R.id.textView180)

        // "비밀번호" 텍스트 색상 변경
        val text = "비밀번호를\n입력해주세요."
        val spannable = SpannableString(text)
        val start = text.indexOf("비밀번호")
        val end = start + "비밀번호".length
        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor("#CE3232")),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView180.text = spannable

        editTextPassword.addTextChangedListener(object : TextWatcher {
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
            val password = editTextPassword.text.toString().trim()
            val userId = arguments?.getString(ARG_USER_ID) ?: return@setOnClickListener
            if (password.isNotEmpty()) {
                (activity as RegisterActivity).navigateToNicknameFragment(userId, password)
            }
        }

        return view
    }
}
