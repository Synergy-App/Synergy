package com.sungkyul.synergy.learning_space

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityExamResultListBinding
import com.sungkyul.synergy.databinding.ActivityNewScreenPracticeBinding

class NewScreenPracticeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewScreenPracticeBinding


    private lateinit var optionButtons: Array<CardView>
    private val cardViewIds = arrayOf(
        R.id.choose_option1_btn,
        R.id.choose_option2_btn,
        R.id.choose_option3_btn,
        R.id.choose_option4_btn
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewScreenPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        optionButtons = cardViewIds.map { findViewById<CardView>(it) }.toTypedArray()
        for (cardView in optionButtons) {
            cardView.setOnClickListener { selectOption(cardView) }
        }

        // 백 버튼
        binding.backBtn.setOnClickListener {
//            val intent = Intent(this, NewScreenPracticeActivity::class.java)
//            startActivity(intent)
        }
        // 다음 버튼
        binding.backBtn.setOnClickListener {
//            val intent = Intent(this, NewScreenPracticeActivity::class.java)
//            startActivity(intent)
        }
    }
    private fun selectOption(selectedCardView: CardView) {
        for (cardView in optionButtons) {
            if (cardView == selectedCardView) {
                cardView.setCardBackgroundColor(Color.parseColor("#FFD231"))
            } else {
                cardView.setCardBackgroundColor(Color.WHITE)
            }
        }
    }
}