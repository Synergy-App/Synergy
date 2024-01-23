package com.sungkyul.synergy.screenEdu

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityScreenEduBinding

class ScreenEdu_ToolBar_Navigation_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityScreenEduBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenEduBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideSystemUI()

        // "다음" 버튼 클릭 시 다음 화면으로 이동
        binding.nextBtn.setOnClickListener {
            val intent = Intent(this, ScreenEduMainActivity::class.java)
            startActivity(intent)
        }

        // handIv 초기에는 보이도록 설정
        binding.handIv.visibility = View.VISIBLE

        // 전체 레이아웃에 클릭 이벤트 추가
        binding.nextBtn.setOnClickListener {
            // 클릭 시 TextView 내용 변경
            binding.changeTv.text = "상단바를 아래로 내려주세요."

            // 상단바를 아래로 내리는 코드 추가
            hideSystemUI()
        }
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }
}
