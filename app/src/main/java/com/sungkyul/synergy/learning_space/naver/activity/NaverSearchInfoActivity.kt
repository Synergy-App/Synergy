package com.sungkyul.synergy.learning_space.naver.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import com.sungkyul.synergy.courses.naver.NaverCourse
import com.sungkyul.synergy.courses.naver.NaverSearchInfoCourse
import com.sungkyul.synergy.courses.screen_layout.NaverFromScreenHomeCourse
import com.sungkyul.synergy.courses.screen_layout.NaverFromScreenHomeCourse2
import com.sungkyul.synergy.databinding.ActivityNaverSearchInfoBinding
import com.sungkyul.synergy.home.activity.MainActivity
import com.sungkyul.synergy.learning_space.EduCompletionActivity
import com.sungkyul.synergy.utils.edu.EduScreen

class NaverSearchInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNaverSearchInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNaverSearchInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 교육을 정의해보자!
        binding.eduScreen.post {
            // 교육 코스를 지정한다.
                binding.eduScreen.course = NaverSearchInfoCourse(binding.eduScreen)

            // 교육 코스가 끝났을 때 발생하는 이벤트 리스너를 설정한다.
            binding.eduScreen.setOnFinishedCourseListener {
                    EduScreen.toTop(this, MainActivity::class.java)
            }

            // 교육을 시작한다.
            binding.eduScreen.start(this)
        }

        val naverbutton = binding.naverButton
        naverbutton.setOnClickListener{
            if(binding.eduScreen.onAction("click_naver_button")){
                val intent = Intent(this, NaverActivity::class.java)
                intent.putExtra("from","NaverSearchInfoActivity")
                startActivity(intent)
            }
        }

        val textViewText = binding.relatedText.text.toString()
        val startIndex = textViewText.indexOf("연관")
        val endIndex = startIndex + "연관".length
        val spannableString = SpannableString(textViewText)
        spannableString.setSpan(
            StyleSpan(android.graphics.Typeface.BOLD),
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
            StyleSpan(android.graphics.Typeface.BOLD),
            startIndex1,
            endIndex1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.naverBlogSubInfoText2.text = spannableString1




        // 두 번째 텍스트뷰 처리
        val textViewText2 = binding.naverBlogMainInfoText2.text.toString()
        val startIndex2 = textViewText2.indexOf("된장찌개 만드는법")
        val endIndex3 = startIndex2 + "된장찌개 만드는법".length

        val spannableString2 = SpannableString(textViewText2)
        spannableString2.setSpan(
            StyleSpan(android.graphics.Typeface.BOLD),
            startIndex2,
            endIndex3,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.naverBlogMainInfoText2.text = spannableString2



        // 세 번째 텍스트뷰 처리 "배추된장찌개 만드는법 간단하고 누구나 만들기 쉬운 레시피로, 두부를 넣어 만든 레시피입니다."
        val textViewText3 = binding.naverBlogSubInfoText1.text.toString()
        val startIndex3 = textViewText3.indexOf("된장찌개 만드는법")
        val endIndex5 = startIndex3 + "된장찌개 만드는법".length
        val spannableString3 = SpannableString(textViewText3)
        spannableString3.setSpan(
            StyleSpan(android.graphics.Typeface.BOLD),
            startIndex3,
            endIndex5,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.naverBlogSubInfoText1.text = spannableString3



        // 네 번째 텍스트뷰 처리 "쉽게 된장찌개 만드는법 10분 간편 레시피"
        val textViewText4 = binding.naverBlogMainInfoText1.text.toString()
        val startIndex4 = textViewText4.indexOf("된장찌개 만드는법")
        val endIndex7 = startIndex4 + "된장찌개 만드는법".length
        val spannableString4 = SpannableString(textViewText4)
        spannableString4.setSpan(
            StyleSpan(android.graphics.Typeface.BOLD),
            startIndex4,
            endIndex7,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.naverBlogMainInfoText1.text = spannableString4







    }
}
