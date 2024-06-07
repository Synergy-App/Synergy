package com.sungkyul.synergy.learning_space

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.sungkyul.synergy.R

class NewScreenPracticeActivity : AppCompatActivity() {

    private lateinit var optionButtons: Array<CardView>
    private val cardViewIds = arrayOf(
        R.id.choose_option1_btn,
        R.id.choose_option2_btn,
        R.id.choose_option3_btn,
        R.id.choose_option4_btn
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_screen_practice)

            optionButtons = cardViewIds.map { findViewById<CardView>(it) }.toTypedArray()
            for (cardView in optionButtons) {
                cardView.setOnClickListener { selectOption(cardView) }
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