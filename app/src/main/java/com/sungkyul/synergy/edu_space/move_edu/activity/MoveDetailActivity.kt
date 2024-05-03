package com.sungkyul.synergy.edu_space.move_edu.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.databinding.ActivityMoveDetailBinding
import com.sungkyul.synergy.edu_space.move_edu.data.MoveInfo

class MoveDetailActivity : AppCompatActivity() {
    private lateinit var activityBinding: ActivityMoveDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMoveDetailBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        val moveInfo = intent.getSerializableExtra("moveInfo") as MoveInfo

        activityBinding.moveTv1.text = moveInfo.moveText
        activityBinding.moveDetailIv.setImageResource(moveInfo.moveImageResId)
        activityBinding.moveTv2.text = moveInfo.moveDescription
    }
}