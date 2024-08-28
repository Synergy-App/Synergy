package com.sungkyul.synergy.home.activity

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.home.fragment.LearningFragment
import com.sungkyul.synergy.com.sungkyul.synergy.home.fragment.MyProfileFragment
import com.sungkyul.synergy.com.sungkyul.synergy.home.fragment.SolvingFragment
import com.sungkyul.synergy.com.sungkyul.synergy.learning_space.fragment.ExamSpaceFragment
import com.sungkyul.synergy.databinding.ActivityMainBinding
import com.sungkyul.synergy.profile_space.CheckLearningAbilityFragment
import com.sungkyul.synergy.profile_space.MyExamResultFragment
import com.sungkyul.synergy.profile_space.Time
import com.sungkyul.synergy.training_space.fragment.ExamResultFragment
import com.sungkyul.synergy.training_space.intent.LearningScreenFragment

/** 시너지 앱 메인 네비게이션 바 + fragment */

class MainActivity : AppCompatActivity() {
    companion object {
        const val Tag_learning = "learn_fragment"
        const val Tag_examSpace = "examSpace_fragment"
        const val Tag_solving = "solving_fragment"
        const val Tag_review = "review_fragment"
        const val Tag_examResult = "MyExamResultFragment"
        const val Tag_myProfile = "myProfile_fragment"
    }

    private lateinit var binding: ActivityMainBinding

    private var backPressedOnce = false
    private val backPressHandler = Handler(Looper.getMainLooper())
    private val backPressRunnable = Runnable { backPressedOnce = false }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Start the time counter
        Time.startTimeCounter()

        // target_fragment 값을 확인하여 해당 프래그먼트를 설정합니다.
        val targetFragment = intent.getStringExtra("target_fragment")
        if (targetFragment != null) {
            when (targetFragment) {
                Tag_examSpace -> {
                    setFragment(Tag_examSpace, ExamSpaceFragment())
                    binding.mainNavigationView.selectedItemId = R.id.solvingFragment
                }

                else -> setFragment(Tag_learning, LearningFragment())
            }
        } else {
            setFragment(Tag_learning, LearningFragment())
        }

        // 선택된 navigation item을 확인하여 설정합니다.
        val selectedNavigationItem =
            intent.getIntExtra("selected_navigation_item", R.id.learingFragment)
        binding.mainNavigationView.selectedItemId = selectedNavigationItem

        binding.mainNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.learingFragment -> setFragment(Tag_learning, LearningFragment())
                R.id.solvingFragment -> setFragment(Tag_examSpace, ExamSpaceFragment())
                R.id.myProfileFrangment -> setFragment(Tag_myProfile, MyProfileFragment())
            }
            true
        }

        // 하단바까지도 변경하게
        val fragmentName = intent.getStringExtra("fragment")
        when (fragmentName) {
            "SolvingFragment" -> {
                setFragment(Tag_solving, SolvingFragment())
                binding.mainNavigationView.selectedItemId = R.id.solvingFragment
            }

            "LearningScreenFragment" -> {
                setFragment("LearningScreenFragment", LearningScreenFragment())
                binding.mainNavigationView.selectedItemId = R.id.learingFragment
            }

            "MyExamResultFragment" -> {
                setFragment("MyExamResultFragment", MyExamResultFragment())
                binding.mainNavigationView.selectedItemId = R.id.myProfileFrangment
            }

            "CheckLearningAbilityFragment" -> {
                setFragment("CheckLearningAbilityFragment", CheckLearningAbilityFragment())
                binding.mainNavigationView.selectedItemId = R.id.myProfileFrangment
            }
        }
    }

    public fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()
        fragTransaction.replace(R.id.mainMainFrameLayout, fragment, tag)
        fragTransaction.addToBackStack(tag)
        fragTransaction.commitAllowingStateLoss()
    }
}