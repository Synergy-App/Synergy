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
        val educationId = 1  // 예시 교육 ID
        val educationInfo = getEducationInfo(educationId)

        // 예제 데이터 생성
        val examResults = listOf(
            ExamResult(
                educationInfo.name,
                "시험결과",
                loadSavedDate() ?: "날짜 없음",
                educationInfo.imageRes,
                loadResultListFromSharedPreferences(educationId)
            )
        )

        // 문제 총 갯수 가져오기 (예제)
        val totalQuestions = 10  // 예시로 10으로 설정, 실제 데이터에 맞게 설정

        // RecyclerView 초기화
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ExamResultAdapter(examResults, educationId, totalQuestions)
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
            1 -> EducationInfo("1차 기초", R.drawable.ic_edu_note) // 예제 이미지 및 이름
            2 -> EducationInfo("1차 화면구성", R.drawable.ic_edu_gall) // 실제 이미지 및 이름 설정
            3 -> EducationInfo("1차 카메라", R.drawable.ic_camera) // 실제 이미지 및 이름 설정
            4 -> EducationInfo("1차 전화", R.drawable.ic_call) // 예제 이미지 및 이름
            5 -> EducationInfo("1차 문자", R.drawable.ic_message) // 실제 이미지 및 이름 설정
            6 -> EducationInfo("1차 환경 설정", R.drawable.ic_edubutton_setting) // 실제 이미지 및 이름 설정
            7 -> EducationInfo("1차 계정 생성", R.drawable.ic_edu_create) // 실제 이미지 및 이름 설정
            8 -> EducationInfo("1차 앱 설치", R.drawable.ic_edubutton_download) // 실제 이미지 및 이름 설정
            9 -> EducationInfo("1차 카카오톡", R.drawable.ic_edubutton_kakaotalk) // 실제 이미지 및 이름 설정
            10 -> EducationInfo("1차 네이버", R.drawable.ic_edubutton_naver) // 실제 이미지 및 이름 설정
            // 추가적인 educationId에 따른 매핑 추가
            else -> EducationInfo("기타 교육", R.drawable.ic_edu_note) // 기본 이미지 및 이름
        }
    }

    /**
     * SharedPreferences에서 저장된 결과 리스트를 가져오는 메서드
     */
    private fun loadResultListFromSharedPreferences(educationId: Int): ArrayList<ResultPair> {
        val gson = Gson()
        val jsonResultList = sharedPreferences.getString("resultList", null)
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
    private fun loadSavedDate(): String? {
        val dateString = sharedPreferences.getString("lastQuizDate", null)
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
            "날짜 변환 오류"
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

/**
 * 교육 정보 클래스
 */
data class EducationInfo(val name: String, val imageRes: Int)
