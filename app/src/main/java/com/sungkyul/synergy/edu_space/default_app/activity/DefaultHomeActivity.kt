package com.sungkyul.synergy.edu_space.default_app.activity

import ImageViewPagerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.sungkyul.synergy.R

class DefaultHomeActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var imageViewPagerAdapter: ImageViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default_menu)

        viewPager = findViewById(R.id.sliderViewPager)
        imageViewPagerAdapter = ImageViewPagerAdapter(this)
        viewPager.adapter = imageViewPagerAdapter
    }
}