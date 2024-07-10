package com.sungkyul.synergy.learning_space.delivery

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityPayDactivityBinding
import com.sungkyul.synergy.courses.delivery.PayDCourse
import com.sungkyul.synergy.learning_space.kakaotaxi.adapter.ViewPagerAdapter

class payDActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPayDactivityBinding
    private lateinit var sliderViewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayDactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            binding.eduScreen.course = PayDCourse(binding.eduScreen)

            binding.eduScreen.setOnFinishedCourseListener {
                // 교육 코스가 끝났을 때 어떻게 할지 처리하는 곳이다.

                // MainActivity로 되돌아 간다.
                val intent = Intent(binding.root.context, MainActivity::class.java)
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

        sliderViewPager = findViewById(R.id.sliderViewPager) // R.id.sliderViewPager는 XML 레이아웃에서 설정한 ID로 변경
        sliderViewPager.adapter = ViewPagerAdapter(getPaymentList())
        sliderViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

    }

    private fun getPaymentList(): ArrayList<Int> {
        return arrayListOf<Int>(R.drawable.card, R.drawable.card, R.drawable.pay)
    }
}