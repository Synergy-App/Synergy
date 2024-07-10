package com.sungkyul.synergy.com.sungkyul.synergy.learning_space.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.com.sungkyul.synergy.home.fragment.SolvingFragment
import com.sungkyul.synergy.com.sungkyul.synergy.utils.GALAXY_NOTE9
import com.sungkyul.synergy.com.sungkyul.synergy.utils.GalaxyNote9
import com.sungkyul.synergy.databinding.FragmentExamSpaceBinding
import com.sungkyul.synergy.training_space.activity.ExamProblemActivity
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

        setDynamicTextSize() // 텍스트 크기 설정 함수 호출

        return binding.root
    }

    private fun getScreenSize(): Point {
        val display =
            (requireActivity().getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        val size = Point()
        display.getSize(size)
        return size
    }

    private fun getStandardSize(): Pair<Int, Int> {
        val screenSize = getScreenSize()
        val density = resources.displayMetrics.density

        val standardSizeX = (screenSize.x / density).toInt()
        val standardSizeY = (screenSize.y / density).toInt()

        return Pair(standardSizeX, standardSizeY)
    }

    private fun setDynamicTextSize() {
        val (standardSizeX, standardSizeY) = getStandardSize()

        // 각각의 텍스트 요소에 다른 크기 설정
        binding.learingTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 12).toFloat())
        binding.learingSubtitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 15).toFloat())
        binding.category.setTextSize(TypedValue.COMPLEX_UNIT_SP,(standardSizeX / 14).toFloat())
        binding.all.setTextSize(TypedValue.COMPLEX_UNIT_SP,(standardSizeX / 14).toFloat())
        binding.weak.setTextSize(TypedValue.COMPLEX_UNIT_SP,(standardSizeX / 14).toFloat())
        binding.difficulty2.setTextSize(TypedValue.COMPLEX_UNIT_SP,(standardSizeX / 14).toFloat())
        binding.categoryTest.setTextSize(TypedValue.COMPLEX_UNIT_SP,(standardSizeX / 18).toFloat())
        binding.allTest.setTextSize(TypedValue.COMPLEX_UNIT_SP,(standardSizeX / 18).toFloat())
        binding.weakTest.setTextSize(TypedValue.COMPLEX_UNIT_SP,(standardSizeX / 18).toFloat())
        binding.difficulty2Test.setTextSize(TypedValue.COMPLEX_UNIT_SP,(standardSizeX / 18).toFloat())

        // headerImage의 높이 설정
        val topbarImageHeight = (standardSizeY * 0.5).toInt() // 높이를 화면 높이의 50%로 설정
        binding.topBarImage.layoutParams.height = topbarImageHeight
        binding.topBarImage.requestLayout()

        if(Build.MODEL == GALAXY_NOTE9) {
            GalaxyNote9.setTitleSize(binding.learingTitle)
            GalaxyNote9.setSubtitleSize(binding.learingSubtitle)
            GalaxyNote9.setHeaderHeight(requireContext(), binding.topBarImage as ImageView)
        }
    }
}