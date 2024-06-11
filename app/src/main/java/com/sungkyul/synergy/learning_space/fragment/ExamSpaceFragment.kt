package com.sungkyul.synergy.com.sungkyul.synergy.learning_space.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.MainActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.SolvingFragment
import com.sungkyul.synergy.databinding.FragmentExamSpaceBinding
import com.sungkyul.synergy.learning_space.activity.ExamProblemActivity
import com.sungkyul.synergy.utils.GalaxyButton

class ExamSpaceFragment : Fragment() {
    private lateinit var binding: FragmentExamSpaceBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExamSpaceBinding.inflate(inflater, container, false)

        binding.typeExerciseButton.post { binding.typeExerciseButton.clipToRoundRect(27.0f) }
        binding.allExerciseButton.post { binding.allExerciseButton.clipToRoundRect(27.0f) }
        binding.attackingVulnerabilitiesButton.post { binding.attackingVulnerabilitiesButton.clipToRoundRect(27.0f) }
        binding.difficultyPracticeButton.post { binding.difficultyPracticeButton.clipToRoundRect(27.0f) }

        binding.typeExerciseButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }

                MotionEvent.ACTION_UP -> {
                    (view as GalaxyButton).startTouchUpAnimation()

                    // SolvingFragment로 이동하는 코드 추가
                    val mainActivity = activity as MainActivity
                    mainActivity.setFragment(MainActivity.Tag_solving, SolvingFragment())
                }
            }
            true
        }

        // 나머지 버튼들은 기존 로직을 유지합니다.
        binding.allExerciseButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }

                MotionEvent.ACTION_UP -> {
                    val intent = Intent(activity, ExamProblemActivity::class.java)
                    activity?.startActivity(intent)
                    (view as GalaxyButton).startTouchUpAnimation()
                }
            }
            true
        }

        binding.attackingVulnerabilitiesButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }

                MotionEvent.ACTION_UP -> {
                    (view as GalaxyButton).startTouchUpAnimation()
                }
            }
            true
        }

        binding.difficultyPracticeButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }

                MotionEvent.ACTION_UP -> {
                    (view as GalaxyButton).startTouchUpAnimation()
                }
            }
            true
        }

        return binding.root
    }
}
