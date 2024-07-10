package com.sungkyul.synergy.home.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityEduSpaceBinding
import com.sungkyul.synergy.learning_space.basic_edu.dictionary_edu.activity.IconEduFragment

class EduSpaceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEduSpaceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEduSpaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iconEduBtn.setOnClickListener{
            val intent= Intent(this, IconEduFragment::class.java)
            startActivity(intent)
        }

        binding.screenEduBtn.setOnClickListener{
            /*val intent= Intent(this, ScreenEduMainActivity::class.java)
            startActivity(intent)*/
        }
    }
}