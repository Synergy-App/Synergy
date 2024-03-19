package com.sungkyul.synergy.edu_space.appinstall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import com.sungkyul.synergy.R

class installActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_install)
        val searchQuery = intent.getStringExtra("search_query")


        //검색어 가져옴 출력까지
        val textView: TextView =findViewById(R.id.desEditText)
        textView.text=searchQuery
        val button43: Button = findViewById(R.id.button43)
        val textView175: TextView = findViewById(R.id.textView175)

        // 버튼 클릭 리스너 설정
        button43.setOnClickListener {
            // 현재 버튼의 텍스트를 가져옴
            val buttonText = button43.text.toString()

            // 버튼 텍스트에 따라 동작 수행
            button43.setOnClickListener {
                // 버튼 텍스트를 "열기"로 변경
                button43.text = "열기"
                // textView175의 문자를 "설치됨"으로 변경
                textView175.text = "설치됨"


            }
        }
    }



}