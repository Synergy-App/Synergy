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
            }

            "LearningScreenFragment" -> {
                setFragment("LearningScreenFragment", LearningScreenFragment())
            }

            "MyExamResultFragment" -> {
                setFragment(Tag_examResult, MyExamResultFragment())
            }

            "CheckLearningAbilityFragment" -> {
                setFragment("CheckLearningAbilityFragment", CheckLearningAbilityFragment())
            }

            else -> {
                // 기본 프래그먼트 설정
                setFragment(Tag_learning, LearningFragment())
            }
        }

    }

    public fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val currentFragment = manager.findFragmentById(R.id.mainMainFrameLayout)

        // 현재 표시된 프래그먼트가 교체하려는 프래그먼트와 동일한지 확인
        if (currentFragment != null && currentFragment.tag == tag) {
            return  // 동일한 프래그먼트를 다시 추가하지 않음
        }

        val existingFragment = manager.findFragmentByTag(tag)

        val transaction = manager.beginTransaction()

        if (existingFragment == null) {
            // 새로운 프래그먼트를 추가
            transaction.replace(R.id.mainMainFrameLayout, fragment, tag)
            transaction.addToBackStack(tag)
        } else {
            // 기존 프래그먼트가 백 스택에 있는 경우 복원
            transaction.replace(R.id.mainMainFrameLayout, existingFragment)
        }

        transaction.commitAllowingStateLoss()
    }
}