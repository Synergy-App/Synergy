package com.sungkyul.synergy.training_space.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungkyul.synergy.databinding.ActivityLearningIconBinding
import com.sungkyul.synergy.training_space.NewScreenPracticeActivity

/**아이콘 실습 버튼 시작 클릭시 화면 전환 하는 화면
 * 기본교육으로 감
 * */

class LearningDefaultAppActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLearningIconBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearningIconBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.iconStartBtn.setOnClickListener {
            val intent = Intent(this, NewScreenPracticeActivity::class.java)
            startActivity(intent)
        }
    }
}