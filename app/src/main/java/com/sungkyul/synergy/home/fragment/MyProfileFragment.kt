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

        // 디스플레이 크기에 따라 글자 크기를 설정
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
        val token = sharedPreferences.getString("Token", null)
        val nickname = sharedPreferences.getString("Nickname", "사용자")
        val digitalAgeGrade = sharedPreferences.getString("DigitalAgeGrade", "default")

        if (nickname != null) {
            binding.textViewName.text = nickname
        }

        // SharedPreferences에 저장된 값에 따라 디지털 연령과 사용자 이미지를 설정
        binding.digitalAge.text = getDigitalAgeText(digitalAgeGrade)
        updateUserImage(digitalAgeGrade)

        if (token != null) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://sng.hyeonwoo.com/user/me")
                .addHeader("Authorization", "Bearer $token")
                .build()

            client.newCall(request).enqueue(object : okhttp3.Callback {
                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    Log.e("Profile", "Failed to load profile data", e)
                    requireActivity().runOnUiThread {
                        Toast.makeText(requireContext(), "프로필 데이터를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                    if (response.isSuccessful) {
                        val responseBody = response.body?.string()
                        if (responseBody != null) {
                            val json = JSONObject(responseBody)
                            val data = json.getJSONObject("data")
                            val apiDigitalAgeGrade = data.getString("digitalAgeGrade")

                            // API에서 가져온 digitalAgeGrade를 SharedPreferences에 저장
                            sharedPreferences.edit().putString("DigitalAgeGrade", apiDigitalAgeGrade).apply()

                            requireActivity().runOnUiThread {
                                binding.digitalAge.text = getDigitalAgeText(apiDigitalAgeGrade)
                                updateUserImage(apiDigitalAgeGrade)
                            }
                        }
                    } else {
                        Log.e("Profile", "Failed to load profile data: ${response.message}")
                        requireActivity().runOnUiThread {
                            Toast.makeText(requireContext(), "프로필 데이터를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }

    private fun getDigitalAgeText(digitalAgeGrade: String?): String {
        return when (digitalAgeGrade) {
            "baby" -> "어린이"
            "student" -> "학생"
            "adult" -> "어른"
            "parent" -> "중년"
            "old" -> "노인"
            "default" -> "아직 측정되지 않았습니다!"
            else -> "아직 측정되지 않았습니다!"
        }
    }

    private fun updateUserImage(digitalAgeGrade: String?) {
        val imageView = binding.userimage
        val imageRes = when (digitalAgeGrade) {
            "baby" -> R.drawable.duck_baby
            "student" -> R.drawable.duck_student
            "adult" -> R.drawable.duck_adult
            "parent" -> R.drawable.duck_parent
            "old" -> R.drawable.duck_old
            "default" -> R.drawable.my_character_default
            else -> R.drawable.sebook_sad_face
        }
        Glide.with(this).load(imageRes).into(imageView)
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
        requireActivity().finish() // 현재 액티비티를 종료하여 뒤로 가기 버튼을 눌렀을 때 다시 프로필 화면으로 돌아가지 않도록 함
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
