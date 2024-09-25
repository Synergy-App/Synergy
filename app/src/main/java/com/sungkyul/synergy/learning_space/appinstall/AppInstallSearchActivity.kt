package com.sungkyul.synergy.learning_space.appinstall

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.courses.app_installation.InstallMainCourse
import com.sungkyul.synergy.courses.app_installation.InstallSearchCourse
import com.sungkyul.synergy.databinding.ActivityAppinstallMainBinding
import com.sungkyul.synergy.databinding.ActivityAppinstallSearchBinding
import com.sungkyul.synergy.home.activity.MainActivity

class AppInstallSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppinstallSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppinstallSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        // 교육을 정의해보자!
//        binding.eduScreen.post {
//            binding.eduScreen.course = InstallSearchCourse(binding.eduScreen)
//
//            binding.eduScreen.setOnFinishedCourseListener {
//                // 교육 코스가 끝났을 때 어떻게 할지 처리하는 곳이다.
//
//                // MainActivity로 되돌아 간다.
//                val intent = Intent(binding.root.context, MainActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
//                startActivity(intent)
//            }
//            // 교육을 시작한다.
//            binding.eduScreen.start(this)
//        }

        // 설치 버튼에 대한 클릭 리스너 설정
        val installButton = findViewById<Button>(R.id.kakao_install_btn)
        installButton.setOnClickListener {
            if(binding.eduScreen.onAction("click_install_button")){
                // 다음 액티비티로 이동하는 Intent 생성
                val intent = Intent(this, AppInstallLoadingActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION) // 애니메이션 없이 액티비티 시작
                startActivity(intent) // 다음 액티비티로 이동
                overridePendingTransition(0, 0) // 애니메이션 없이 액티비티 전환
            }

        }
    }
}
