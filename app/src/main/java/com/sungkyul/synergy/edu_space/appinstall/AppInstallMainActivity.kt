package com.sungkyul.synergy.edu_space.appinstall

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.AbsoluteSizeSpan
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R

class AppInstallMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appinstall_main)

        val recommendTextView: TextView = findViewById(R.id.install_recommend_text)

        // "추천" 부분의 인덱스 찾기
        val text = recommendTextView.text.toString()
        val index = text.indexOf("추천")

        // "추천" 부분의 텍스트 크기 변경
        recommendTextView.textSize = 16f // 기본 크기로 설정
        val spannable = SpannableString(text)
        spannable.setSpan(AbsoluteSizeSpan(20, true), index, index + 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        recommendTextView.text = spannable

        // EditText에 대한 참조 가져오기
        val appInstallEdit: EditText = findViewById(R.id.app_install_edit)

        // EditText에 텍스트 변경 이벤트 리스너 추가
        appInstallEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val searchText = s.toString().trim()

                // 입력된 텍스트가 "카카오톡"일 경우 새로운 액티비티로 이동
                if (searchText.equals("카카오톡", ignoreCase = true)) {
                    val intent = Intent(this@AppInstallMainActivity, AppInstallSearchActivity::class.java)
                    startActivity(intent)
                }
            }
        })
    }
}
