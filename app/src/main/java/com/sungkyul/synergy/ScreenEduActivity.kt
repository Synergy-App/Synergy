package com.sungkyul.synergy

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sungkyul.synergy.databinding.ActivityScreenEduBinding

private lateinit var binding: ActivityScreenEduBinding
class ScreenEduActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScreenEduBinding
    private var isHandVisible = false // handIv가 보이는지 여부를 추적하는 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenEduBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideActionBar()

        // handIv 초기에는 보이도록 설정
        binding.handIv.visibility = View.VISIBLE


        // 전체 레이아웃에 클릭 이벤트 추가
        binding.nextBtn.setOnClickListener {
            // 클릭 시 TextView 내용 변경
            binding.changeTv.text = "상단바를 누르고 아래로 내려주세요."

            // handIv에 애니메이션 효과 주기
            val anim = ObjectAnimator.ofFloat(binding.handIv, "translationY", 0f, 500f)
            anim.duration = 600 // 애니메이션 지속 시간(ms)
            anim.start()
        }
    }

    // 상단바 숨기기
    fun hideActionBar() {
        supportActionBar?.hide() // 액션바 숨기기
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }
}
