package com.sungkyul.synergy.edu_courses.settings

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class SettingsCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "환경설정의<br>메인 화면입니다."
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
            dialog.contentText = "휴대폰의 상세 설정 목록들이 나열되어 있는데요."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 40.0f
            dialog.bottom = 400.0f
            dialog.start = 24.0f
            dialog.end = 24.0f
            dialog.visibility = true
            cover.visibility = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "맨 아래까지 스크롤해주세요."
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id = "scroll_to_bottom"
            hands.add(
                EduHand(
                    id = "drag",
                    x = 200.0f,
                    y = 650.0f,
                    gesture = HandGestures.Companion::settingsScrollDownGesture
                )
            )
        })

        list.add(EduData().apply {
            dialog.contentText = "<b>디스플레이</b>는<br>스마트폰의 다양한 설정을 할 수<br>있습니다."
            dialog.top = 450.0f
            dialog.bottom = 150.0f
            cover.boxLeft = 20.0f
            cover.boxTop = 320.0f
            cover.boxRight = width-20.0f
            cover.boxBottom = 390.0f
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
        })

        list.add(EduData().apply {
            dialog.contentText = "디스플레이를 클릭해주세요."
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
            action.id = "tap_display_item"
            hands.add(
                EduHand(
                    id = "tap",
                    x = width/2,
                    y = 360.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}