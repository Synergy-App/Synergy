package com.sungkyul.synergy.edu_space.settingedu2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R

class Setting2MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting2_main)

        // setting_display_btn 버튼 찾기
        val settingDisplayBtn = findViewById<Button>(R.id.setting_display_btn)

        // setting_display_btn 클릭 이벤트 처리
        settingDisplayBtn.setOnClickListener {
            // Setting2DisplayActivity로 이동하는 Intent 생성
            val intent = Intent(this, Setting2DisplayActivity::class.java)
            startActivity(intent)
        }
    }
}
