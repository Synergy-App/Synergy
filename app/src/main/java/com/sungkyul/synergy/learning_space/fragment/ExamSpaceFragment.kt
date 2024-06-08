package com.sungkyul.synergy.com.sungkyul.synergy.learning_space.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.databinding.FragmentExamResultBinding
import com.sungkyul.synergy.databinding.FragmentExamSpaceBinding
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

                    //val intent = Intent(requireContext(), DefaultPhoneActivity::class.java)
                    //startActivity(intent)
                }
            }
            true
        }

        binding.allExerciseButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }

                MotionEvent.ACTION_UP -> {
                    (view as GalaxyButton).startTouchUpAnimation()

                    //val intent = Intent(requireContext(), DefaultPhoneActivity::class.java)
                    //startActivity(intent)
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

                    //val intent = Intent(requireContext(), DefaultPhoneActivity::class.java)
                    //startActivity(intent)
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

                    //val intent = Intent(requireContext(), DefaultPhoneActivity::class.java)
                    //startActivity(intent)
                }
            }
            true
        }

        return binding.root
    }
}
