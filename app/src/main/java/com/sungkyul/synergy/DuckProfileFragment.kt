package com.sungkyul.synergy

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.sungkyul.synergy.databinding.FragmentDuckProfileBinding
import com.sungkyul.synergy.edu_space.default_app.phone.activity.DefaultPhoneActivity
import com.sungkyul.synergy.my_profile.CheckMyResultActivity
import com.sungkyul.synergy.my_profile.MyExamResultActivity
import com.sungkyul.synergy.utils.GalaxyButton
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

class DuckProfileFragment2 : Fragment() {
    private lateinit var binding: FragmentDuckProfileBinding

    private var backPressedOnce = false
    private val backPressHandler = Handler(Looper.getMainLooper())
    private val backPressRunnable = Runnable { backPressedOnce = false }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDuckProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initially hide the digi_age1 TextView
        binding.digiAge1.visibility = View.GONE

        binding.examGalaxyButton.post { binding.examGalaxyButton.clipToRoundRect(27.0f) }
        binding.checkGalaxyButton.post { binding.checkGalaxyButton.clipToRoundRect(27.0f) }

        binding.examGalaxyButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }
                MotionEvent.ACTION_UP -> {
                    (view as GalaxyButton).startTouchUpAnimation()

                    val intent = Intent(requireActivity(), MyExamResultActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
        binding.checkGalaxyButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                }
                MotionEvent.ACTION_UP -> {
                    (view as GalaxyButton).startTouchUpAnimation()

                    val intent = Intent(requireActivity(), CheckLearningAbilityActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }

        loadProfileData()
    }

    override fun onResume() {
        super.onResume()
        loadProfileData() // Update profile data when the fragment resumes
    }

    private fun loadProfileData() {
        val sharedPreferences = requireContext().getSharedPreferences("SynergyPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("Token", null)
        val nickname = sharedPreferences.getString("Nickname", "사용자")
        var digitalAgeGrade = sharedPreferences.getString("DigitalAgeGrade", "none") ?: "none"

        if (nickname != null) {
            binding.textViewName.text = nickname
        }

        // Set the digital age and user image based on the saved value in SharedPreferences
        binding.digitalAge.text = getDigitalAgeText(digitalAgeGrade)
        updateUserImage(digitalAgeGrade)

        if (token != null) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://synergy.hyeonwoo.com/user/me")
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
                            val apiDigitalAgeGrade = data.optString("digitalAgeGrade", "none")

                            // Update UI with the fetched data
                            requireActivity().runOnUiThread {
                                binding.digitalAge.text = getDigitalAgeText(apiDigitalAgeGrade)
                                updateUserImage(apiDigitalAgeGrade)

                                // Show digi_age1 TextView if the digitalAgeGrade is not "none"
                                if (apiDigitalAgeGrade != "none") {
                                    binding.digiAge1.visibility = View.VISIBLE
                                }
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
            "none" -> "아직 측정되지 않았습니다!"
            else -> "알 수 없음"
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
            "none" -> R.drawable.my_character_default
            else -> R.drawable.sebook_sad_face
        }
        Glide.with(this).load(imageRes).into(imageView)
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
