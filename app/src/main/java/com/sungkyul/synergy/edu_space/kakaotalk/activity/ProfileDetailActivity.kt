package com.sungkyul.synergy.edu_space.kakaotalk.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.kakaotalk.data.profileItem

/** 카카오톡 프로필 디테일 액티비티 */
class ProfileDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_detail)

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
    }
}