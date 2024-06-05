package com.sungkyul.synergy.learning_space.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.databinding.FragmentExamResultBinding
import com.sungkyul.synergy.edu_space.default_app.phone.activity.DefaultPhoneActivity
import com.sungkyul.synergy.utils.GalaxyButton

class ExamResultFragment : Fragment() {
    private lateinit var binding: FragmentExamResultBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExamResultBinding.inflate(inflater, container, false)

        binding.backButton.post { binding.backButton.clipToRoundRect(27.0f) }
        binding.viewAllButton.post { binding.viewAllButton.clipToRoundRect(27.0f) }

        binding.backButton.setOnTouchListener { view, event ->
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

        binding.viewAllButton.setOnTouchListener { view, event ->
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
