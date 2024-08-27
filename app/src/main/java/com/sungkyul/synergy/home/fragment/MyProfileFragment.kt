package com.sungkyul.synergy.com.sungkyul.synergy.home.fragment

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.GALAXY_NOTE9
import com.sungkyul.synergy.utils.GalaxyNote9
import com.sungkyul.synergy.databinding.FragmentMyProfileBinding
import com.sungkyul.synergy.profile_space.CheckLearningAbilityFragment
import com.sungkyul.synergy.home.activity.LoginActivity
import com.sungkyul.synergy.home.activity.MainActivity
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import com.sungkyul.synergy.profile_space.EducationInfo




import android.content.SharedPreferences

class MyProfileFragment : Fragment() {

    private lateinit var binding: FragmentMyProfileBinding

    private var backPressedOnce = false
    private val backPressHandler = Handler(Looper.getMainLooper())
    private val backPressRunnable = Runnable { backPressedOnce = false }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.examResultCardView.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            intent.putExtra("fragment", "MyExamResultFragment")
            startActivity(intent)
        }

        binding.CheckResultCardView.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            intent.putExtra("fragment", "CheckLearningAbilityFragment")
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            logout()
        }

        setDynamicTextSize()
        loadProfileData()
    }

    override fun onResume() {
        super.onResume()
        loadProfileData() // 프래그먼트가 다시 활성화될 때 프로필 데이터를 업데이트
    }

    private fun getScreenSize(): Point {
        val display = (requireActivity().getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
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
        binding.headerTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 12).toFloat())
        binding.headerSubtitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 15).toFloat())
        binding.textViewName.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 13).toFloat())
        binding.digitalAge.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 13).toFloat())
        binding.digitalageName.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 13).toFloat())

        // headerImage의 높이 설정
        val headerImageHeight = (standardSizeY * 0.5).toInt()
        binding.headerImage.layoutParams.height = headerImageHeight
        binding.headerImage.requestLayout()

        // 버튼 내부 텍스트 크기 설정
        val examResultTextView = binding.examResultCardView.findViewById<TextView>(R.id.examResultText)
        examResultTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 15).toFloat())

        val checkResultTextView = binding.CheckResultCardView.findViewById<TextView>(R.id.checkResultText)
        checkResultTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 15).toFloat())

        if (Build.MODEL == GALAXY_NOTE9) {
            GalaxyNote9.setTitleSize(binding.headerTitle)
            GalaxyNote9.setSubtitleSize(binding.headerSubtitle)
            GalaxyNote9.setHeaderHeight(requireContext(), binding.headerImage)

            binding.textViewName.textSize = 20.0f
            binding.digitalAge.textSize = 20.0f

            examResultTextView.textSize = 20.0f
            checkResultTextView.textSize = 20.0f
        }
    }

    private fun loadProfileData() {
        val sharedPreferences = requireContext().getSharedPreferences("SynergyPrefs", Context.MODE_PRIVATE)
        val nickname = sharedPreferences.getString("Nickname", "사용자")
        if (nickname != null) {
            binding.textViewName.text = nickname
        }

        // 완료한 시험 수를 가져와 디지털 나이를 설정합니다.
        val completedExamCount = getCompletedExamCount(sharedPreferences)
        updateDigitalAge(completedExamCount)
    }

    private fun getCompletedExamCount(sharedPreferences: SharedPreferences): Int {
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

        // 저장된 날짜가 있는지 확인하여 완료된 시험 수를 계산
        return educationInfoList.count { educationInfo ->
            sharedPreferences.getString("lastQuizDate_${educationInfo.educationId}", null) != null
        }
    }

    private fun updateDigitalAge(completedExamCount: Int) {
        val (digitalAgeGrade, digitalAgeImage) = when (completedExamCount) {
            0 -> "old" to R.drawable.duck_old
            1 -> "parent" to R.drawable.duck_parent
            2 -> "adult" to R.drawable.duck_adult
            3 -> "student" to R.drawable.duck_student
            else -> "baby" to R.drawable.duck_baby
        }

        // SharedPreferences에 저장된 디지털 나이 업데이트
        val sharedPreferences = requireContext().getSharedPreferences("SynergyPrefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("DigitalAgeGrade", digitalAgeGrade)
            apply()
        }

        // 사용자 이미지와 텍스트 업데이트
        updateUserImage(digitalAgeImage)
        binding.digitalAge.text = getDigitalAgeText(digitalAgeGrade)
    }

    private fun getDigitalAgeText(digitalAgeGrade: String): String {
        return when (digitalAgeGrade) {
            "baby" -> "어린이"
            "student" -> "학생"
            "adult" -> "어른"
            "parent" -> "중년"
            "old" -> "노인"
            else -> "아직 측정되지 않았습니다!"
        }
    }

    private fun updateUserImage(imageRes: Int) {
        Glide.with(this).load(imageRes).into(binding.userimage)
    }

    private fun logout() {
        val sharedPreferences = requireContext().getSharedPreferences("SynergyPrefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            remove("Token")
            remove("Nickname")
            remove("DigitalAgeGrade")
            remove("AutoLogin")
            remove("SavedId")
            remove("SavedPassword")
            apply()
        }
        val intent = Intent(requireActivity(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
        Toast.makeText(requireContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
    }

    fun handleOnBackPressed(): Boolean {
        if (backPressedOnce) {
            activity?.finish()
            return true
        }

        this.backPressedOnce = true
        Toast.makeText(requireContext(), "뒤로가기를 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        backPressHandler.postDelayed(backPressRunnable, 2000)
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        backPressHandler.removeCallbacks(backPressRunnable)
    }
}
