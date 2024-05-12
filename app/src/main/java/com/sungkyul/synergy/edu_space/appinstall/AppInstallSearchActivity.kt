package com.sungkyul.synergy.edu_space.appinstall

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R

class AppInstallSearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appinstall_search)

        // 설치 버튼에 대한 클릭 리스너 설정
        val installButton = findViewById<Button>(R.id.kakao_install_btn)
        installButton.setOnClickListener {
            // 다음 액티비티로 이동하는 Intent 생성
            val intent = Intent(this, AppInstallLoadingActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION) // 애니메이션 없이 액티비티 시작
            startActivity(intent) // 다음 액티비티로 이동
            overridePendingTransition(0, 0) // 애니메이션 없이 액티비티 전환
        }
    }
}
