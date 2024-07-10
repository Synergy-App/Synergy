package com.sungkyul.synergy.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityNougatBinding
import com.sungkyul.synergy.learning_space.default_app.DefaultAppActivity
import com.sungkyul.synergy.utils.edu.EduScreen

class NougatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNougatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNougatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            // 교육 코스를 지정한다.
            binding.eduScreen.course = NougatCourse(binding.eduScreen)

            // 교육 코스가 끝났을 때 발생하는 이벤트 리스너를 설정한다.
            binding.eduScreen.setOnFinishedCourseListener {
                EduScreen.toTop(this, DefaultAppActivity::class.java)
            }

            // 교육을 시작한다.
            binding.eduScreen.start(this)
        }

        // 스마트폰의 이전 버튼을 누르면, 지정된 액티비티로 되돌아간다.
        EduScreen.navigateBackToTop(this, DefaultAppActivity::class.java)
    }
}
