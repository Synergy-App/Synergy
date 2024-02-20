package com.sungkyul.synergy.training.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.sungkyul.synergy.MyProfileFragment
import com.sungkyul.synergy.R
import com.sungkyul.synergy.ReviewFragment
import com.sungkyul.synergy.SolvingFragment
import com.sungkyul.synergy.databinding.ActivityStudyMainBinding
import com.sungkyul.synergy.databinding.ActivityTrainingBinding
import com.sungkyul.synergy.training.fragment.TrainingFragment

/** 시너지 앱 메인 네비게이션 바 + fragment */

class TrainingActivity: AppCompatActivity() {
    lateinit var binding: ActivityTrainingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}