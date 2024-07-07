package com.sungkyul.synergy.learning_space.screen

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.utils.GALAXY_NOTE9

class PracticeRecentlyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_recently)


        // 3초 후에 새로운 액티비티로 이동
        Handler().postDelayed({
            val intent = Intent(this, PracticeResultActivity::class.java)
            startActivity(intent)
            finish() // 현재 액티비티 종료
        }, 3000) // 3초 뒤에 실행

        if (Build.MODEL == GALAXY_NOTE9) {
            findViewById<TextView>(R.id.timerTextView).textSize = 18.0f
            findViewById<TextView>(R.id.problemText).textSize = 18.0f
        }
    }
}