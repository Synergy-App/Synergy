package com.sungkyul.synergy.profile_space

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityCheckLearningAbilityBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CheckLearningAbilityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckLearningAbilityBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckLearningAbilityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setDynamicTextSize()
        loadLocalData()
        handler.post(updateTask)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(updateTask)
    }

    private fun loadLocalData() {
        val sharedPreferences = getSharedPreferences("SynergyPrefs", MODE_PRIVATE)
        totalStudyTimeInMinutes = sharedPreferences.getInt("totalStudyTimeInMinutes", 0)
        studyDaysCount = sharedPreferences.getInt("studyDaysCount", 1)
        todayStudyTimeInMinutes = sharedPreferences.getInt("todayStudyTimeInMinutes", 0)

        val lastDate = sharedPreferences.getString("lastDate", "")
        val currentDate = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date())

        if (lastDate != currentDate) {
            // 날짜가 바뀌었으므로 오늘 학습 시간을 초기화하고 일 수를 증가시킴
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
        val sharedPreferences = getSharedPreferences("SynergyPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("totalStudyTimeInMinutes", totalStudyTimeInMinutes)
        editor.putInt("studyDaysCount", studyDaysCount)
        editor.putInt("todayStudyTimeInMinutes", todayStudyTimeInMinutes)
        editor.putString("lastDate", SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date()))
        editor.apply()
    }

    private fun loadProfileData() {
        val sharedPreferences = getSharedPreferences("SynergyPrefs", MODE_PRIVATE)
        val token = sharedPreferences.getString("Token", null)

        if (token != null) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://sng.hyeonwoo.com/user/me")
                .addHeader("Authorization", "Bearer $token")
                .build()

            client.newCall(request).enqueue(object : okhttp3.Callback {
                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    runOnUiThread {
                        Toast.makeText(
                            this@CheckLearningAbilityActivity,
                            "프로필 데이터를 불러오는데 실패했습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                    if (response.isSuccessful) {
                        val responseBody = response.body?.string()
                        if (responseBody != null) {
                            val json = JSONObject(responseBody)
                            val data = json.getJSONObject("data")
                            val nickname = data.optString("nickname", "사용자")

                            totalStudyTimeInMinutes = data.optInt("total_study_time", 0)
                            studyDaysCount = data.optInt("study_days_count", 1) // 기본값을 1로 설정하여 0으로 나누는 오류 방지
                            todayStudyTimeInMinutes = data.optInt("today_study_time", 0)

                            runOnUiThread {
                                binding.titleText.text = "$nickname 님의 취약 유형"
                                updateStudyTimeViews()
                                saveLocalData()
                            }
                        } else {
                            runOnUiThread {
                                Toast.makeText(
                                    this@CheckLearningAbilityActivity,
                                    "프로필 데이터를 불러오는데 실패했습니다.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } else {
                        runOnUiThread {
                            Toast.makeText(
                                this@CheckLearningAbilityActivity,
                                "프로필 데이터를 불러오는데 실패했습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
        } else {
            Toast.makeText(this, "토큰이 없습니다.", Toast.LENGTH_SHORT).show()
        }
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

    private fun getScreenSize(): Point {
        val display = (getSystemService(WINDOW_SERVICE) as WindowManager).defaultDisplay
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
        binding.titleText.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 15).toFloat())
        binding.learningInfoTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 15).toFloat())

        // 여기에 추가적으로 필요한 TextView를 설정합니다.
        binding.averageTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 18).toFloat())
        binding.todayTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 18).toFloat())
    }
}
