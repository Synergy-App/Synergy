package com.sungkyul.synergy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungkyul.synergy.databinding.ActivityEduSpaceBinding
import com.sungkyul.synergy.learning_space.basic_edu.dictionary_edu.activity.IconEduFragment

/** 교육 공간 메뉴 화면 */
private lateinit var binding: ActivityEduSpaceBinding

class EduSpaceActivity : AppCompatActivity() {
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
