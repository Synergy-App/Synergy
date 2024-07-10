package com.sungkyul.synergy.training_space.intent

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.training_space.NewScreenPracticeActivity

class LearningIconActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning_icon)

        Log.d("ActivityLifecycle", "LearningIconActivity onCreate")

        val startButton: Button = findViewById(R.id.iconStartBtn)
        startButton.setOnClickListener {
            val intent = Intent(this, NewScreenPracticeActivity::class.java)
            Log.d("ActivityLifecycle", "Starting NewScreenPracticeActivity")
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("ActivityLifecycle", "LearningIconActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ActivityLifecycle", "LearningIconActivity onResume")
    }
}
