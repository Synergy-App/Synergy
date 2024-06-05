package com.sungkyul.synergy.edu_courses.accountedu

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class GoogleMainCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "구글의 메인 화면입니다."
            dialog.contentFont = R.font.pretendard_medium
            dialog.contentSize = 26.0f
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 26.0f
            dialog.bottom = 500.0f
            dialog.start = 24.0f
            dialog.end = 24.0f
            dialog.visibility = true
            cover.visibility = false
            cover.isClickable = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "로그인 버튼을 눌러볼까요?"
            dialog.top = 70.0f
            dialog.bottom = 464.0f
            dialog.start = 24.0f
            dialog.end = 24.0f
            dialog.visibility = true
            cover.visibility = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg

            cover.boxLeft = 216.0f
            cover.boxRight = width-8.0f
            cover.boxTop = 2.0f
            cover.boxBottom = 60.0f
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
            hands.add(
                EduHand(
                    id = "tap",
                    //x = width-60.0f,
                    //y = 50.0f,
                    target = null,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
