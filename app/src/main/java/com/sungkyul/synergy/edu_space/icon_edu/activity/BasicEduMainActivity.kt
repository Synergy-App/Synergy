package com.sungkyul.synergy.edu_space.icon_edu.activity


import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R

class BasicEduMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basicedu_main)

        val basicMoveButton = findViewById<Button>(R.id.basic_move_btn)
        val wordDicButton = findViewById<Button>(R.id.word_dic_btn)

        basicMoveButton.setOnClickListener {

        // 기본 동작 교육 버튼 클릭 이벤트 처리
        }

        wordDicButton.setOnClickListener {
            // 용어 사전 버튼 클릭 이벤트 처리
        }
    }
}