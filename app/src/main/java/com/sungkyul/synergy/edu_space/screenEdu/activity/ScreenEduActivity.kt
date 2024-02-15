package com.sungkyul.synergy.edu_space.screenEdu.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R

class ScreenEduActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_screen_main) // activity_screen_toolbar_left.xml 레이아웃을 설정합니다.
    }
}