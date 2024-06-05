package com.sungkyul.synergy.my_profile

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.my_profile.Time
import java.util.concurrent.TimeUnit

class CheckMyResultActivity : AppCompatActivity() {
    private lateinit var textViewAverageTime: TextView
    private lateinit var textViewTodayTime: TextView
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_my_result)

        textViewAverageTime = findViewById(R.id.textViewAverageTime)
        textViewTodayTime = findViewById(R.id.textViewTodayTime)

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
        val timeString = String.format("%d시간 %02d분", hours, minutes)
        textViewAverageTime.text = timeString

        // 오늘 학습 시간 업데이트
        val todayHours = TimeUnit.SECONDS.toHours(elapsedSeconds)
        val todayMinutes = TimeUnit.SECONDS.toMinutes(elapsedSeconds) % 60
        val todaySeconds = elapsedSeconds % 60
        val todayTimeString = String.format("%02d:%02d:%02d", todayHours, todayMinutes, todaySeconds)
        textViewTodayTime.text = todayTimeString
    }
}
