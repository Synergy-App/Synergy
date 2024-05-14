package com.sungkyul.synergy.edu_courses.settings

import android.view.Gravity
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
            cover.isClickable = true
            dialog.contentText = "화면 밝기와<br>글자 크기에 대해서<br>배워보겠습니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 300.0f
            dialog.bottom = 300.0f
            dialog.start = 50.0f
            dialog.end = 50.0f
            dialog.visibility = true
            cover.visibility = true
        })

        list.add(EduData().apply {
            dialog.contentText = "맨 아래까지 스크롤해주세요."
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
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
            cover.boxVisibility = true
            cover.boxStrokeVisibility = true
        })

        list.add(EduData().apply {
            dialog.contentText = "디스플레이를 클릭해주세요."
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.boxVisibility = false
            cover.boxStrokeVisibility = false
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
