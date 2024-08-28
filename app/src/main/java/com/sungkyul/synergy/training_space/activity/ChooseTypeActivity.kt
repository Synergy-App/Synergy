package com.sungkyul.synergy.training_space.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.training_space.NewScreenPracticeActivity

class ChooseTypeActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_type)

        // SharedPreferences 초기화
        sharedPreferences = getSharedPreferences("SynergyPrefs", Context.MODE_PRIVATE)

        // receivedId 로드
        val receivedId = sharedPreferences.getInt("receivedId", -1)
        Log.d("ChooseTypeActivity", "Loaded receivedId from SharedPreferences: $receivedId")

        val buttonObjective = findViewById<FrameLayout>(R.id.buttonObjective)
        val buttonSubjective = findViewById<FrameLayout>(R.id.buttonSubjective)

        // 객체에 따라 다른 액티비티로 이동하는 로직
        buttonObjective.setOnClickListener {
            val intent = Intent(this, NewScreenPracticeActivity::class.java)
            intent.putExtra("receivedId", receivedId)
            Log.d("ChooseTypeActivity", "Passing receivedId to NewScreenPracticeActivity: $receivedId")
            startActivity(intent)
        }

        buttonSubjective.setOnClickListener {
            navigateToSubjectiveActivity(receivedId)
        }
    }

    private fun navigateToSubjectiveActivity(educationId: Int) {
        val intent = when (educationId) {
/* 수진이 연결 구간
            1 -> Intent(this, SubjectiveActivity1::class.java) //기초
            2 -> Intent(this, SubjectiveActivity2::class.java) //화면구성
            3 -> Intent(this, SubjectiveActivity3::class.java) //카메라
            4 -> Intent(this, SubjectiveActivity4::class.java) //전화
            5 -> Intent(this, SubjectiveActivity5::class.java) //문자
            6 -> Intent(this, SubjectiveActivity6::class.java) //환경 설정
            7 -> Intent(this, SubjectiveActivity7::class.java) //계정 생성
            8 -> Intent(this, SubjectiveActivity8::class.java) //앱 설치
            9 -> Intent(this, SubjectiveActivity9::class.java) //카카오톡
            10 -> Intent(this, SubjectiveActivity10::class.java) //네이버
*/

            else -> {
                Toast.makeText(this, "유효하지 않은 교육 ID입니다.", Toast.LENGTH_SHORT).show()
                return
            }
        }

        Log.d("ChooseTypeActivity", "Navigating to activity for educationId: $educationId")
        startActivity(intent)
    }
}
