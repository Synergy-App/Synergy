package com.sungkyul.synergy.edu_courses.settings

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class SettingsDisplayCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "먼저 스마트폰<br>밝기 조절을<br>배워보겠습니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 300.0f
            dialog.bottom = 300.0f
            dialog.start = 50.0f
            dialog.end = 50.0f
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.contentText = "손가락을 따라<br>움직여주세요."
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id = "change_light_bar"
            action.message = "100"
            hands.add(
                EduHand(
                    id = "drag",
                    x = 220.0f,
                    y = 510.0f,
                    gesture = HandGestures.Companion::displayLightDragGesture
                )
            )
        })

        list.add(EduData().apply {
            dialog.contentText = "이번엔 글자 크기를<br>조절해보겠습니다."
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
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
                    source = R.drawable.hand,
                    x = 200.0f,
                    y = 650.0f,
                    width = 50.0f,
                    height = 75.0f,
                    gesture = HandGestures.Companion::displayScrollGesture
                )
            )
        })

        list.add(EduData().apply {
            action.id = "tap_font_item"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 250.0f,
                    y = 475.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
