package com.sungkyul.synergy.learning_space.accountedu

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.sungkyul.synergy.R
import com.sungkyul.synergy.courses.accountedu.GoogleFirstCourse
import com.sungkyul.synergy.databinding.ActivityGoogleFirstBinding
import com.sungkyul.synergy.home.activity.MainActivity

class GoogleFirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGoogleFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoogleFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            binding.eduScreen.course = GoogleFirstCourse(binding.eduScreen)

            binding.sebookTitle.visibility = LinearLayout.INVISIBLE
            binding.sebookSmile.visibility = ImageView.VISIBLE
            binding.iconImage.visibility = ConstraintLayout.INVISIBLE
            binding.eduScreen.setOnNextListener {num->
                if(num in 1..5) {
                    binding.sebookTitle.visibility = LinearLayout.VISIBLE
                    binding.sebookSmile.visibility = ImageView.INVISIBLE
                } else if(num in 6..7) {
                    binding.sebookTitle.visibility = LinearLayout.INVISIBLE
                    binding.iconImage.visibility = ConstraintLayout.VISIBLE
                    binding.image.setImageResource(R.drawable.google_exp1)
                }else if(num in 8..8) {
                    binding.image.setImageResource(R.drawable.google_exp2)
                }else if(num in 9..11) {
                    binding.image.setImageResource(R.drawable.google_exp3)
                }else if(num in 12..12) {
                    binding.sebookSmile.visibility = ImageView.VISIBLE
                    binding.iconImage.visibility = ConstraintLayout.INVISIBLE
                    binding.sebookSmile.setImageResource(R.drawable.sebook_basic)
                }
            }
            binding.eduScreen.setOnFinishedCourseListener {
                // 교육 코스가 끝났을 때 어떻게 할지 처리하는 곳이다.
                val intent = Intent(binding.root.context, GoogleMainActivity::class.java)
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
