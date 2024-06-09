package com.sungkyul.synergy.fragments

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
import com.sungkyul.synergy.RegisterActivity

class FragmentRegisterId : Fragment() {

    private lateinit var editTextId: EditText
    private lateinit var btnNext: Button
    private lateinit var textView180: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register_id, container, false)

        editTextId = view.findViewById(R.id.editTextId)
        btnNext = view.findViewById(R.id.btnNext_Id)
        textView180 = view.findViewById(R.id.textView180)

        // "아이디" 텍스트 색상 변경
        val text = "아이디를\n입력해주세요."
        val spannable = SpannableString(text)
        val start = text.indexOf("아이디")
        val end = start + "아이디".length
        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor("#CE3232")),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView180.text = spannable

        editTextId.addTextChangedListener(object : TextWatcher {
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
            val userId = editTextId.text.toString().trim()
            if (userId.isNotEmpty()) {
                (activity as RegisterActivity).navigateToPasswordFragment(userId)
            }
        }

        return view
    }
}
