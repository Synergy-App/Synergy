package com.sungkyul.synergy.learning_space.default_app.todo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ImageViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return NUM_PAGES
    }

    override fun createFragment(position: Int): Fragment {
        // Return a new instance of com.sungkyul.synergy.edu_space.default_app.activity.ImageFragment for each position
        return ImageFragment()
    }

    companion object {
        private const val NUM_PAGES = 2
    }
}
