package com.sungkyul.synergy.edu_space.accountedu

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R

class GoogleLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_login)

        val textView = findViewById<TextView>(R.id.guest_code_text)
        val textData = textView.text.toString()

        val grayColor = Color.parseColor("#808080") // 회색
        val blueColor = Color.parseColor("#1B76EB") // 파란색

        // SpannableStringBuilder를 사용하여 텍스트의 일부분의 색상을 변경합니다.
        val builder = SpannableStringBuilder(textData)

        // "내 컴퓨터가" 부분을 회색으로 변경
        builder.setSpan(
            ForegroundColorSpan(grayColor),
            0,
            textData.indexOf("로그인하세요.") + 39, // "로그인하세요." 까지의 길이에 + 6을 해주어야 합니다.
            SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // "로그인하세요." 이후의 부분을 파란색으로 변경
        builder.setSpan(
            ForegroundColorSpan(blueColor),
            textData.indexOf("로그인하세요.") + 39, // "로그인하세요." 이후의 인덱스부터 시작
            textData.length,
            SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // 변경된 Spannable 문자열을 TextView에 설정합니다.
        textView.text = builder
    }
}
