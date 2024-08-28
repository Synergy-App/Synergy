package com.sungkyul.synergy.profile_space

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.learning_space.ResultPair
import com.sungkyul.synergy.databinding.FragmentCheckLearningAbilityBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.min

class CheckLearningAbilityFragment : Fragment() {

    private lateinit var binding: FragmentCheckLearningAbilityBinding
    private var totalStudyTimeInMinutes = 0
    private var studyDaysCount = 0
    private var todayStudyTimeInMinutes = 0

    private val handler = Handler(Looper.getMainLooper())
    private val updateInterval: Long = 60000 // 1분 (60초)

    private val updateTask = object : Runnable {
        override fun run() {
            updateStudyTime()
            handler.postDelayed(this, updateInterval)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckLearningAbilityBinding.inflate(inflater, container, false)

        setDynamicTextSize()
        loadLocalData()
        loadProfileData() // 닉네임을 로드하여 제목에 반영
        handler.post(updateTask)

        calculateWeakness() // 취약 유형 계산 및 업데이트

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(updateTask)
    }

    private fun loadLocalData() {
        val sharedPreferences = requireContext().getSharedPreferences("SynergyPrefs", Context.MODE_PRIVATE)
        totalStudyTimeInMinutes = sharedPreferences.getInt("totalStudyTimeInMinutes", 0)
        studyDaysCount = sharedPreferences.getInt("studyDaysCount", 1)
        todayStudyTimeInMinutes = sharedPreferences.getInt("todayStudyTimeInMinutes", 0)

        val lastDate = sharedPreferences.getString("lastDate", "")
        val currentDate = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date())

        if (lastDate != currentDate) {
            studyDaysCount += 1
            todayStudyTimeInMinutes = 0
            val editor = sharedPreferences.edit()
            editor.putInt("studyDaysCount", studyDaysCount)
            editor.putInt("todayStudyTimeInMinutes", todayStudyTimeInMinutes)
            editor.putString("lastDate", currentDate)
            editor.apply()
        }

        updateStudyTimeViews()
    }

    private fun saveLocalData() {
        val sharedPreferences = requireContext().getSharedPreferences("SynergyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("totalStudyTimeInMinutes", totalStudyTimeInMinutes)
        editor.putInt("studyDaysCount", studyDaysCount)
        editor.putInt("todayStudyTimeInMinutes", todayStudyTimeInMinutes)
        editor.putString("lastDate", SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date()))
        editor.apply()
    }

    private fun updateStudyTime() {
        todayStudyTimeInMinutes += 1
        totalStudyTimeInMinutes += 1
        saveLocalData()
        updateStudyTimeViews()
    }

    private fun updateStudyTimeViews() {
        val averageStudyTimeInMinutes = totalStudyTimeInMinutes / studyDaysCount
        val averageHours = averageStudyTimeInMinutes / 60
        val averageMinutes = averageStudyTimeInMinutes % 60

        val todayHours = todayStudyTimeInMinutes / 60
        val todayMinutes = todayStudyTimeInMinutes % 60

        binding.textViewAverageTime.text = String.format("%02d시간 %02d분", averageHours, averageMinutes)
        binding.textViewTodayTime.text = String.format("%02d시간 %02d분", todayHours, todayMinutes)
    }

    private fun loadProfileData() {
        val sharedPreferences = requireContext().getSharedPreferences("SynergyPrefs", Context.MODE_PRIVATE)
        val nickname = sharedPreferences.getString("Nickname", "사용자")
        if (nickname != null) {
            binding.titleText.text = "$nickname 님의 취약 유형"
        }
    }

    private fun calculateWeakness() {
        val sharedPreferences = requireContext().getSharedPreferences("SynergyPrefs", Context.MODE_PRIVATE)

        val educationInfoList = listOf(
            EducationInfo("기초", R.drawable.ic_edu_note, -1),
            EducationInfo("화면구성", R.drawable.ic_edu_gall, 2),
            EducationInfo("카메라", R.drawable.ic_camera, 3),
            EducationInfo("전화", R.drawable.ic_call, 4),
            EducationInfo("문자", R.drawable.ic_message, 5),
            EducationInfo("환경 설정", R.drawable.ic_edubutton_setting, 6),
            EducationInfo("계정 생성", R.drawable.ic_edu_create, 7),
            EducationInfo("앱 설치", R.drawable.ic_edubutton_download, 8),
            EducationInfo("카카오톡", R.drawable.ic_edubutton_kakaotalk, 9),
            EducationInfo("네이버", R.drawable.ic_edubutton_naver, 10)
        )

        var weakestEducation: EducationInfo? = null
        var lowestCorrectRatio = Double.MAX_VALUE

        for (educationInfo in educationInfoList) {
            val resultListJson = sharedPreferences.getString("resultList_${educationInfo.educationId}", null)
            if (resultListJson != null) {
                val resultList = Gson().fromJson(resultListJson, Array<ResultPair>::class.java).toList()
                val totalQuestions = resultList.size
                val correctAnswers = resultList.count { it.isCorrect }
                val correctRatio = if (totalQuestions > 0) correctAnswers.toDouble() / totalQuestions else 1.0

                if (correctRatio < lowestCorrectRatio) {
                    lowestCorrectRatio = correctRatio
                    weakestEducation = educationInfo
                }
            }
        }

        // 최약 유형이 있으면 이미지와 텍스트를 설정
        if (weakestEducation != null) {
            binding.statusText.text = "${weakestEducation.name}실습의 정답률이 가장 낮습니다."
            Glide.with(this).load(weakestEducation.imageRes).into(binding.statusImage)
        } else {
            binding.statusText.text = "아직 측정되지 않았습니다."
            binding.statusImage.setImageResource(R.drawable.sebook_sad_face) // 기본 이미지 설정
        }
    }

    private fun getScreenSize(): Point {
        val display = (requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
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

        binding.headerTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 12).toFloat())
        binding.titleText.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 15).toFloat())
        binding.learningInfoTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 15).toFloat())

        binding.averageTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 18).toFloat())
        binding.todayTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 18).toFloat())
    }
}
