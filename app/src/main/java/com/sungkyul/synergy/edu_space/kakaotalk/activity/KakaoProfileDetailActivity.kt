package com.sungkyul.synergy.edu_space.kakaotalk.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityKakaoProfileDetailBinding
import com.sungkyul.synergy.edu_space.default_app.activity.DefaultAppActivity
import com.sungkyul.synergy.edu_space.kakaotalk.data.profileItem
import com.sungkyul.synergy.utils.EduCourses
import com.sungkyul.synergy.utils.EduScreen

/** 카카오톡 프로필 디테일 액티비티 */
class KakaoProfileDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKakaoProfileDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakaoProfileDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedData = intent.getSerializableExtra("profile_detail") as? profileItem

        receivedData?.let {
            // 받은 데이터로부터 필요한 정보 추출
            val name = receivedData.name
            val message = receivedData.message

            // 이름과 메시지를 보여줄 TextView 찾기
            val nameTextView = findViewById<TextView>(R.id.profileDetail_name)
            val messageTextView = findViewById<TextView>(R.id.profileDetail_message)

            // TextView에 이름과 메시지 설정
            nameTextView.text = "$name"
            messageTextView.text = "$message"
        }

        binding.startChattingLL.setOnClickListener {
            if(binding.eduScreen.onAction("click_chat11_button")) {
                val intent = Intent(this, KakaoChattingActivity::class.java)
                startActivity(intent)
            }
        }

        // 교육 추가
        binding.eduScreen.post {
            binding.eduScreen.course = EduCourses.kakaoProfileCourse(
                binding.eduScreen.context,
                binding.eduScreen.width.toFloat(),
                binding.eduScreen.height.toFloat()
            )
            binding.eduScreen.start(this)
        }

        // 뒤로 가기 키를 눌렀을 때의 이벤트를 처리한다.
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // DefaultAppActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, DefaultAppActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        })
    }
}
