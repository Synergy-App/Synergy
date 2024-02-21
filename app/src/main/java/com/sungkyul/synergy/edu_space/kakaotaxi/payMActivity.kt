package com.sungkyul.synergy.edu_space.kakaotaxi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.kakaotaxi.adapter.ViewPagerAdapter
class payMActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_mactivity)



        sliderViewPager.adapter = ViewPagerAdapter(getPaymentList())
        sliderViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

    }

    private fun getPaymentList(): ArrayList<Int> {
        return arrayListOf<Int>(R.drawable.card1, R.drawable.card2, R.drawable.card3)
    }
}