package com.sungkyul.synergy.courses.naver

import android.os.Build
import android.util.Log
import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AdaptiveUtils
import com.sungkyul.synergy.utils.GALAXY_NOTE9
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class NaverCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        Log.i("model?", Build.MODEL)
        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "네이버의 메인화면입니다."
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont= R.font.pretendard_semibold
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            dialog.top = 0.2f
            dialog.bottom = 0.65f
            dialog.start = 0.05f
            dialog.end = 0.05f

            bottomDialog.visibility = true

            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.contentText = "네이버는 쇼핑, 홈,<br>콘텐츠, 클립으로 구분되어<br>있습니다."
            dialog.background = R.drawable.edu_dialog_bg
            dialog.contentColor = R.color.black
            dialog.top = 0.6f
            dialog.bottom = 0.2f

            cover.boxLeft = 0.0f
            cover.boxRight = 1.0f
            cover.boxTop = 0.85f
            cover.boxBottom = 0.95f
            cover.visibility = true
            cover.boxVisibility = true
            cover.boxBorderVisibility=  true
            cover.boxBorderColor = R.color.black
        })

        list.add(EduData().apply {
            dialog.titleText="홈"
            dialog.titleSize = AdaptiveUtils.dialogTitleMedium()
            dialog.contentText = "네이버의 메인 화면<br>입니다"
            dialog.contentGravity = Gravity.LEFT

            cover.boxLeft = 0.3f
            cover.boxRight = 0.5f
        })

        list.add(EduData().apply {
            dialog.titleText="쇼핑"
            dialog.contentText = "휴대폰으로 쇼핑을<br>즐길 수 있습니다."

            cover.boxLeft = 0.1f
            cover.boxRight = 0.3f
            cover.boxBorderColor = R.color.lime
        })


        list.add(EduData().apply {
            dialog.titleText="콘텐츠"
            dialog.contentText = "뉴스, 경제, 스포츠, 살림<br>등 여러 정보를 볼 수 있습니다."

            cover.boxLeft = 0.5f
            cover.boxRight = 0.7f
        })

        list.add(EduData().apply {
            dialog.titleText="클립"
            dialog.contentText = "짧은 영상들을 볼 수<br>있습니다."

            cover.boxLeft = 0.7f
            cover.boxRight = 0.9f
        })

        list.add(EduData().apply {
            dialog.titleText=""
            dialog.contentText = "이 부분은 검색창으로<br>원하는 정보를 이 곳에<br> 검색할 수 있습니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 0.3f
            dialog.bottom = 0.5f

            cover.boxLeft = 0.0f
            cover.boxRight = 1.0f
            cover.boxTop = 0.15f
            cover.boxBottom = 0.25f
        })

        list.add(EduData().apply {
            dialog.contentText = "예를 들어 내가 가고 싶은<br>식당의 영업 시간을<br>검색해서 찾아볼 수도<br>있고"
        })

        list.add(EduData().apply {
            dialog.contentText = "스포츠 경기의 일정이나<br>결과를 검색해서 찾아볼<br>수도 있으며"
        })

        list.add(EduData().apply {
            dialog.contentText = "원하는 요리법을 검색해<br>찾아볼 수도 있습니다."
        })

        list.add(EduData().apply {
            dialog.contentText = "\"된장찌개 만드는 법\"을<br>검색해서 요리법을<br>찾아보세요."
            dialog.background = R.drawable.edu_dialog_green_bg
            dialog.contentColor = R.color.white
            dialog.top = 0.4f
            dialog.bottom = 0.4f

            cover.boxBorderVisibility=false
            cover.boxVisibility = false
        })

        list.add(EduData().apply {
            cover.visibility = false
            cover.isClickable = false
            dialog.visibility = false
            action.id = "click_naver_search_view"
            hands.add(
                EduHand(
                id = "tap",
                x = 0.5f,
                y = 0.2f,
                gesture = HandGestures.Companion::tapGesture
            )
            )
        })
    }
}
