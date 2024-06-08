package com.sungkyul.synergy.edu_space.icon_edu.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.move_edu.activity.MoveEduActivity


class BasicEduMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basicedu_main)

        val basicMoveButton = findViewById<Button>(R.id.basic_move_btn)
        val wordDicButton = findViewById<Button>(R.id.word_dic_btn)

        basicMoveButton.setOnClickListener {
            // 기본 동작 교육 버튼 클릭 이벤트 처리
            val intent = Intent(this, MoveEduActivity::class.java)
            startActivity(intent)
        }

        wordDicButton.setOnClickListener {
            // 용어 사전 버튼 클릭 이벤트 처리
            val intent = Intent(this, IconEduActivity::class.java)
            startActivity(intent)
        }
    }
}
