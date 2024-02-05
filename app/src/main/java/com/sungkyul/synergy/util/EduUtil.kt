package com.sungkyul.synergy.util

import android.app.Activity
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

// TODO: 어떻게 해야 교육 안내 메시지를 적은 반복 작업으로 보여줄 수 있을까?
class EduUtil {
    companion object {
        fun test(fragmentManager: FragmentManager, screenID: Int) {
            val eduScreenFragment = EduScreenFragment()
            replaceFragment(fragmentManager, screenID, eduScreenFragment)
        }

        // 레이아웃 내 모든 뷰를 활성화/비활성화하는 재귀 함수
        fun setAllViewsEnabled(viewGroup: ViewGroup, enabled: Boolean) {
            for(i in 0..viewGroup.childCount) {
                val child = viewGroup.getChildAt(i)

                try {
                    (child as Button).isEnabled = enabled
                } catch (e: Exception) {
                    Log.i("setAllViewsEnabled", e.toString())
                }

                if(child is ViewGroup) {
                    setAllViewsEnabled(child, enabled)
                }
            }
        }

        private fun replaceFragment(fragmentManager: FragmentManager, screenID: Int, fragment: Fragment) {
            fragmentManager.beginTransaction()
                .replace(screenID, fragment)
                .commit()
        }
    }
}
