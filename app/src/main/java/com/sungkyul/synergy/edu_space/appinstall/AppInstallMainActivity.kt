package com.sungkyul.synergy.edu_space.appinstall

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R

class AppInstallMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appinstall_main)

        // 레이아웃의 텍스트뷰에 대한 참조 가져오기
        val recommendTextView: TextView = findViewById(R.id.install_recommend_text)

        // 텍스트 가져오기
        val text = recommendTextView.text.toString()

        // "추천" 부분의 인덱스 찾기
        val index = text.indexOf("추천")

        // "추천" 부분의 텍스트 크기 변경
        recommendTextView.textSize = 16f // 기본 크기로 설정
        val spannable = SpannableString(text)
        spannable.setSpan(AbsoluteSizeSpan(20, true), index, index + 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        recommendTextView.text = spannable
    }
}