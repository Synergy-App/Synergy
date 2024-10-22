package com.sungkyul.synergy.courses.accountedu

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AdaptiveUtils
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.Models
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
            dialog.contentText = "나의 이름을 입력하는<br>화면입니다."
            dialog.contentFont = R.font.pretendard_medium
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 600.0f/930.0f
            dialog.bottom = 90.0f/930.0f
            dialog.start = 24.0f/412.0f
            dialog.end = 24.0f/412.0f
            dialog.visibility = true
            cover.visibility = false
            cover.isClickable = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "성과 이름을 입력해 주세요."
            dialog.top = 350.0f/930.0f
            dialog.bottom = 350.0f/930.0f
            dialog.start = 24.0f/412.0f
            dialog.end = 24.0f/412.0f
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
                    x = 150.0f/412.0f,
                    y = Models.tunePos(200.0f/930.0f, 250.0f/930.0f, 200.0f/930.0f),
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
                    x = 150.0f/412.0f,
                    y = Models.tunePos(270.0f/930.0f, 350.0f/930.0f, 270.0f/930.0f),
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
                    x = 330.0f/412.0f,
                    y = Models.tunePos(350.0f/930.0f, 450.0f/930.0f, 350.0f/930.0f),
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

    }
}
