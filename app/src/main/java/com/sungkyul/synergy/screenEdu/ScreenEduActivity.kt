package com.sungkyul.synergy.screenEdu

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityScreenEduBinding


//전체?
class ScreenEduActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScreenEduBinding
    //private var isHandVisible = false // handIv가 보이는지 여부를 추적하는 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenEduBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideSystemUI()
        //hideActionBar()
        //hideNavigationBar()

        // "다음" 버튼 클릭 시 다음 화면으로 이동
        binding.nextBtn.setOnClickListener {
            // 화면 전환을 위한 Intent 생성
            val intent = Intent(this, ScreenEduMainActivity::class.java)
            // Intent를 사용하여 다음 화면으로 이동
            startActivity(intent)
        }
        /*
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
        */

    }

   /*
    // 상단바 숨기기
    private fun hideActionBar() {
        supportActionBar?.hide() // 액션바 숨기기
    }

    // 하단바 숨기기
    private fun hideNavigationBar() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }
    */

    // 상단바와 하단바 같이 숨기기
    //위에 코드처럼 쓰면 두개 중에 하나만 사라져서 아래 코드로 수정
    private fun hideSystemUI() {
        window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }
}