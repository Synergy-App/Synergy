package com.sungkyul.synergy.training_space.naver

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.widget.Toast
import com.sungkyul.synergy.R
import com.sungkyul.synergy.courses.naver.NaverSearchInfoCourse
import com.sungkyul.synergy.databinding.ActivityNaverSearchInfoBinding
import com.sungkyul.synergy.databinding.ActivityPracticeNaver2Binding
import com.sungkyul.synergy.databinding.ActivityPracticeNaver3Binding
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.learning_space.naver.activity.NaverActivity
import com.sungkyul.synergy.training_space.naver.result.ExamNaverResultActivity
import com.sungkyul.synergy.utils.edu.EduScreen

class PracticeNaver3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeNaver3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeNaver3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3초 후 화면 전환
        Handler(Looper.getMainLooper()).postDelayed({
            // 화면을 전환할 Activity로 Intent 생성
            val intent = Intent(this, ExamNaverResultActivity::class.java)
            startActivity(intent)
        }, 3000)

        val naverbutton = binding.naverButton
        naverbutton.setOnClickListener {
            if (binding.eduScreen.onAction("click_naver_button")) {
                val intent = Intent(this, NaverActivity::class.java)
                intent.putExtra("from", "NaverSearchInfoActivity")
                startActivity(intent)
            }
        }

        val textViewText = binding.relatedText.text.toString()
        val startIndex = textViewText.indexOf("연관")
        val endIndex = startIndex + "연관".length
        val spannableString = SpannableString(textViewText)
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.relatedText.text = spannableString


        // 첫 번째 텍스트뷰 처리 "된장찌개 만드는법 누구나 쉽게 따라하는 레시피 알려드립니다. 재료도 간략하게! 십분내로 조리가능!!"
        val textViewText1 = binding.naverBlogSubInfoText2.text.toString()
        val startIndex1 = textViewText1.indexOf("된장찌개 만드는법")
        val endIndex1 = startIndex1 + "된장찌개 만드는법".length
        val spannableString1 = SpannableString(textViewText1)
        spannableString1.setSpan(
            StyleSpan(Typeface.BOLD),
            startIndex1,
            endIndex1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.naverBlogSubInfoText2.text = spannableString1


    }
}
