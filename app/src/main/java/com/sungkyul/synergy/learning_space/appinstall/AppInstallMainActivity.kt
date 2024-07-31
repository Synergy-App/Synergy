package com.sungkyul.synergy.learning_space.appinstall

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.AbsoluteSizeSpan
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.home.activity.MainActivity

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

        val appInstallEdit: EditText = findViewById(R.id.app_install_edit)

        // EditText에 텍스트 변경 이벤트 리스너 추가
        appInstallEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                // 입력된 텍스트가 "카카오톡"이면 검색을 활성화
                if (s.toString().equals("카카오톡", ignoreCase = true)) {
                    appInstallEdit.setOnEditorActionListener { _, actionId, event ->
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                            (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER)
                        ) {
                            // 검색 버튼(엔터)을 눌렀을 때만 검색 화면으로 이동
                            val intent = Intent(this@AppInstallMainActivity, AppInstallSearchActivity::class.java)
                            startActivity(intent)
                            return@setOnEditorActionListener true
                        }
                        return@setOnEditorActionListener false
                    }
                }
            }
        })

        /*
        // 뒤로 가기 키를 눌렀을 때의 이벤트를 처리한다.
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // MainActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        })

         */

    }
}
