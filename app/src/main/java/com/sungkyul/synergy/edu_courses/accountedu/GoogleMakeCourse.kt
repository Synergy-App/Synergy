package com.sungkyul.synergy.edu_courses.accountedu

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class GoogleMakeCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "이름을 입력하는 화면입니다."
            dialog.contentFont = R.font.pretendard_medium
            dialog.contentSize = 26.0f
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 500.0f
            dialog.bottom = 40.0f
            dialog.start = 24.0f
            dialog.end = 24.0f
            dialog.visibility = true
            cover.visibility = false
            cover.isClickable = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "성과 이름을 입력해 주세요."
            dialog.top = 245.0f
            dialog.bottom = 255.0f
            dialog.start = 24.0f
            dialog.end = 24.0f
            dialog.visibility = true
            cover.visibility = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id ="first_name_input"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 150.0f,
                    y = 200.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id ="name_input"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 150.0f,
                    y = 270.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id ="btn_check"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 250.0f,
                    y = 380.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

    }
}
