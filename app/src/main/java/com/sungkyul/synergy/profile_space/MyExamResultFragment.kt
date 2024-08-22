package com.sungkyul.synergy.profile_space

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.learning_space.ResultPair
import com.sungkyul.synergy.databinding.FragmentMyExamResultBinding
import com.sungkyul.synergy.utils.GALAXY_NOTE9
import com.sungkyul.synergy.utils.GalaxyNote9
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class EducationInfo(val name: String, val imageRes: Int, var educationId: Int = 0)

class MyExamResultFragment : Fragment() {
    private lateinit var binding: FragmentMyExamResultBinding
    private lateinit var adapter: ExamResultAdapter
    private lateinit var sharedPreferences: SharedPreferences

    private var standardSize_X: Int = 0
    private var standardSize_Y: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyExamResultBinding.inflate(inflater, container, false)

        // 화면의 기준 사이즈를 계산
        getStandardSize()

        // 텍스트 크기를 기준 사이즈를 이용해 설정
        binding.headerTitle.textSize = (standardSize_X / 12).toFloat()
        binding.headerSubtitle.textSize = (standardSize_X / 16).toFloat()

        // SharedPreferences 초기화
        sharedPreferences = requireContext().getSharedPreferences("SynergyPrefs", Context.MODE_PRIVATE)

        // 교육 ID에 따른 이름과 이미지 설정
        val educationInfoList = listOf(
            getEducationInfo(-1),
            getEducationInfo(2),
            getEducationInfo(3),
            getEducationInfo(4),
            getEducationInfo(5),
            getEducationInfo(6),
            getEducationInfo(7),
            getEducationInfo(8),
            getEducationInfo(9),
            getEducationInfo(10)
        )

        // 날짜가 있는 항목만 필터링하여 표시
        val examResults = educationInfoList.mapNotNull { educationInfo ->
            val savedDate = loadSavedDate(educationInfo.educationId)
            if (savedDate != null) {
                ExamResult(
                    educationInfo.name + "시험결과",
                    "",
                    savedDate,
                    educationInfo.imageRes,
                    loadResultListFromSharedPreferences(educationInfo.educationId)
                )
            } else {
                null // 날짜가 없는 경우 리스트에서 제외
            }
        }

        // 문제 총 갯수 설정
        val totalQuestions = 10  // 예시로 10으로 설정, 실제 데이터에 맞게 설정

        // RecyclerView 초기화
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ExamResultAdapter(examResults, totalQuestions)
        binding.recyclerView.adapter = adapter

        if (Build.MODEL == GALAXY_NOTE9) {
            GalaxyNote9.setTitleSize(binding.headerTitle)
            GalaxyNote9.setSubtitleSize(binding.headerSubtitle)
            GalaxyNote9.setHeaderHeight(requireContext(), binding.headerImage)
        }

        return binding.root
    }

    /**
     * educationId에 따라 교육 이름과 이미지를 반환하는 메서드
     */
    private fun getEducationInfo(educationId: Int): EducationInfo {
        return when (educationId) {
            -1 -> EducationInfo("기초 ", R.drawable.ic_edu_note)
            2 -> EducationInfo("화면구성 ", R.drawable.ic_edu_gall)
            3 -> EducationInfo("카메라 ", R.drawable.ic_camera)
            4 -> EducationInfo("전화 ", R.drawable.ic_call)
            5 -> EducationInfo("문자 ", R.drawable.ic_message)
            6 -> EducationInfo("환경 설정 ", R.drawable.ic_edubutton_setting)
            7 -> EducationInfo("계정 생성 ", R.drawable.ic_edu_create)
            8 -> EducationInfo("앱 설치 ", R.drawable.ic_edubutton_download)
            9 -> EducationInfo("카카오톡 ", R.drawable.ic_edubutton_kakaotalk)
            10 -> EducationInfo("네이버 ", R.drawable.ic_edubutton_naver)
            else -> EducationInfo("기타 교육", R.drawable.ic_edu_note) // 기본 이미지 및 이름
        }.apply {
            this.educationId = educationId
        }
    }

    /**
     * SharedPreferences에서 저장된 결과 리스트를 가져오는 메서드
     */
    private fun loadResultListFromSharedPreferences(educationId: Int): ArrayList<ResultPair> {
        val gson = Gson()
        val jsonResultList = sharedPreferences.getString("resultList_$educationId", null)
        return if (jsonResultList != null) {
            val type = object : TypeToken<ArrayList<ResultPair>>() {}.type
            gson.fromJson(jsonResultList, type)
        } else {
            ArrayList()
        }
    }

    /**
     * SharedPreferences에서 저장된 날짜를 불러오는 메서드
     */
    private fun loadSavedDate(educationId: Int): String? {
        val dateString = sharedPreferences.getString("lastQuizDate_$educationId", null)
        return dateString?.let { formatDateString(it) }
    }

    /**
     * 날짜 문자열을 포맷팅하는 메서드
     */
    private fun formatDateString(dateString: String): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("MM월 dd일", Locale.getDefault())
            val date: Date = inputFormat.parse(dateString) ?: Date()
            outputFormat.format(date)
        } catch (e: Exception) {
            "시험기록없음"
        }
    }

    // 디스플레이 사이즈를 반환하는 메서드
    private fun getScreenSize(): Point {
        val windowManager = requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size
    }

    // 기기의 해상도와 밀도를 기준으로 기준 사이즈를 계산하는 메서드
    private fun getStandardSize() {
        val screenSize = getScreenSize()
        val displayMetrics: DisplayMetrics = resources.displayMetrics
        val density = displayMetrics.density

        standardSize_X = (screenSize.x / density).toInt()
        standardSize_Y = (screenSize.y / density).toInt()
    }
}
