package com.sungkyul.synergy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungkyul.synergy.databinding.ActivityEduSpaceBinding
import com.sungkyul.synergy.edu_space.icon_edu.activity.IconEduActivity
import com.sungkyul.synergy.screenEdu.ScreenEduActivity

/** 교육 공간 메뉴 화면 */
private lateinit var binding: ActivityEduSpaceBinding

class EduSpaceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEduSpaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iconEduBtn.setOnClickListener{
            val intent= Intent(this, IconEduActivity::class.java)
            startActivity(intent)
        }

        binding.screenEduBtn.setOnClickListener{
            val intent= Intent(this, ScreenEduActivity::class.java)
            startActivity(intent)
        }
    }
}
