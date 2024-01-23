package com.sungkyul.synergy.screenEdu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.sungkyul.synergy.R

//화면 구성 교육 - 메인 화면
class ScreenEduMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_edu_main)

        // 상단바 없애기
        supportActionBar?.hide()

        // 하단바 없애기
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        // "다음" 버튼 클릭 시 다음 화면으로 이동
        val nextBtn: Button = findViewById(R.id.next_btn)
        nextBtn.setOnClickListener {
            // 화면 전환을 위한 Intent 생성
            val intent = Intent(this, ScreenEduBottomActivity::class.java)
            // Intent를 사용하여 다음 화면으로 이동
            startActivity(intent)
        }
    }
}
