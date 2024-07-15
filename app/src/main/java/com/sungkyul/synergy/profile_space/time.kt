package com.sungkyul.synergy.profile_space

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Time : Application() {

    companion object {
        var elapsedTime: Long = 0
            private set

        private lateinit var handler: Handler
        private lateinit var runnable: Runnable

        fun startTimeCounter() {
            handler = Handler(Looper.getMainLooper())
            runnable = object : Runnable {
                override fun run() {
                    elapsedTime += 1
                    Log.d("Time", "Elapsed Time: $elapsedTime seconds")
                    handler.postDelayed(this, 1000)
                }
            }
            handler.post(runnable)
        }

        fun stopTimeCounter() {
            handler.removeCallbacks(runnable)
        }

        fun getCurrentDate(): String {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val currentDate = dateFormat.format(Date())
            Log.d("Time", "Current Date: $currentDate")
            return currentDate
        }
    }

    override fun onCreate() {
        super.onCreate()
        startTimeCounter()
        Log.d("Time", "Time Counter Started")
    }
}
