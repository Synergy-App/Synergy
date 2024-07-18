package com.sungkyul.synergy.learning_space.appinstall

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.courses.app_installation.InstallFirstCourse
import com.sungkyul.synergy.databinding.ActivityAppInstallFirstBinding
import com.sungkyul.synergy.home.activity.MainActivity

class AppInstallFirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppInstallFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppInstallFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            binding.eduScreen.course = InstallFirstCourse(binding.eduScreen)

            binding.sebookSmile.visibility = ImageView.VISIBLE
            binding.sebookBasic.visibility = ImageView.INVISIBLE
            binding.iconImage.visibility = LinearLayout.INVISIBLE
            binding.eduScreen.setOnNextListener {num->
                if(num==1) {
                    binding.sebookSmile.visibility = ImageView.INVISIBLE
                    binding.sebookBasic.visibility = ImageView.VISIBLE
                    binding.iconImage.visibility = LinearLayout.INVISIBLE
                }
                if(num==2) {
                    binding.sebookSmile.visibility = ImageView.INVISIBLE
                    binding.sebookBasic.visibility = ImageView.INVISIBLE
                    binding.iconImage.visibility = LinearLayout.VISIBLE
                }
            }
            binding.eduScreen.setOnFinishedCourseListener {
                // 교육 코스가 끝났을 때 어떻게 할지 처리하는 곳이다.
                val intent = Intent(binding.root.context, AppInstallMainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
            // 교육을 시작한다.
            binding.eduScreen.start(this)
        }

        // 뒤로 가기 키를 눌렀을 때의 이벤트를 처리한다.
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // MainActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        })
    }
}
