package com.sungkyul.synergy.screenEdu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityScreenEduSpaceBinding

class ScreenEdu_select_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityScreenEduSpaceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenEduSpaceBinding.inflate(layoutInflater)
        setContentView(binding.root)
/*
        binding.screenEdu_home_btn.setOnClickListener {
            // 홈화면 버튼 클릭 시 수행할 동작
            // 예: 홈화면으로 이동
            // val intent = Intent(this, HomeActivity::class.java)
            // startActivity(intent)
        }

 */

       /* binding.screenEdu_main_btn.setOnClickListener {
            // 메인화면 버튼 클릭 시 수행할 동작
            // 예: 메인화면으로 이동
            // val intent = Intent(this, MainActivity::class.java)
            // startActivity(intent)
        }

        */

        binding.screenEduToolbarBtn.setOnClickListener {
            // 상단바 교육 버튼 클릭 시 수행할 동작
            val intent = Intent(this, ScreenEdu_ToolBar_Navigation_Activity::class.java)
            startActivity(intent)
        }

        binding.screenEduBottomBtn.setOnClickListener {
            // 하단바 교육 버튼 클릭 시 수행할 동작
            val intent = Intent(this, ScreenEduActivity::class.java)
            startActivity(intent)
        }


    }
}
