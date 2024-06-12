package com.sungkyul.synergy

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityCheckLearningAbilityBinding
import com.sungkyul.synergy.my_profile.Time
import java.util.concurrent.TimeUnit

class CheckLearningAbilityActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckLearningAbilityBinding
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckLearningAbilityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setDynamicTextSize()

        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable {
            override fun run() {
                updateElapsedTime(Time.elapsedTime)
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(runnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }

    private fun updateElapsedTime(elapsedSeconds: Long) {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val totalElapsedTime = sharedPreferences.getLong("totalElapsedTime", 0) + elapsedSeconds
        val totalDaysUsed = sharedPreferences.getLong("totalDaysUsed", 1)

        val averageTime = totalElapsedTime / totalDaysUsed

        val hours = TimeUnit.SECONDS.toHours(averageTime)
        val minutes = TimeUnit.SECONDS.toMinutes(averageTime) % 60
        val seconds = TimeUnit.SECONDS.toSeconds(averageTime) % 60
        val timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        binding.textViewAverageTime.text = timeString

        // 오늘 학습 시간 업데이트
        val todayHours = TimeUnit.SECONDS.toHours(elapsedSeconds)
        val todayMinutes = TimeUnit.SECONDS.toMinutes(elapsedSeconds) % 60
        val todaySeconds = elapsedSeconds % 60
        val todayTimeString = String.format("%02d:%02d:%02d", todayHours, todayMinutes, todaySeconds)
        binding.textViewTodayTime.text = todayTimeString
    }

    private fun getScreenSize(): Point {
        val display = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
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
        binding.textViewAverageTime.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 10).toFloat())
        binding.textViewTodayTime.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 10).toFloat())
        binding.learningInfoTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 15).toFloat())

        // 여기에 추가적으로 필요한 TextView를 설정합니다.
        binding.averageTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 18).toFloat())
        binding.todayTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, (standardSizeX / 18).toFloat())
    }
}
