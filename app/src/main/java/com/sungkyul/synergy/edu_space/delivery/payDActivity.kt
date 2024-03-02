package com.sungkyul.synergy.edu_space.delivery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.kakaotaxi.adapter.ViewPagerAdapter

class payDActivity : AppCompatActivity() {

    private lateinit var sliderViewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_dactivity)


        sliderViewPager = findViewById(R.id.sliderViewPager) // R.id.sliderViewPager는 XML 레이아웃에서 설정한 ID로 변경
        sliderViewPager.adapter = ViewPagerAdapter(getPaymentList())
        sliderViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

    }

    private fun getPaymentList(): ArrayList<Int> {
        return arrayListOf<Int>(R.drawable.card, R.drawable.card, R.drawable.pay)
    }
}