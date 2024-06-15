package com.sungkyul.synergy.com.sungkyul.synergy.edu_courses.screen_layout

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class ScreenTopBarCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "상단바입니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
            dialog.top = 0.3f
            dialog.bottom = 0.55f
            dialog.start = 0.05f
            dialog.end = 0.05f

            cover.visibility = true
            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.contentText = "와이파이를 켜고,<br>벨소리를 소리나게<br>바꿔볼까요?"
            dialog.background = R.drawable.edu_dialog_green_bg
            dialog.top = 0.4f
            dialog.bottom = 0.4f
        })

        list.add(EduData().apply {
            dialog.visibility = false

            cover.visibility = false
            cover.isClickable = false

            action.id = "click_wifi"

            hands.add(
                EduHand(
                    id = "touch",
                    x = 0.1f,
                    y = 0.095f,
                    gesture = HandGestures::tapGesture
                )
            )
        })

        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "밝기를 줄여볼까요?"

            cover.visibility = true
            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.visibility = false

            cover.visibility = false
            cover.isClickable = false

            action.id = "drag_light"

            hands.add(
                EduHand(
                    id = "drag",
                    x = 0.9f,
                    y = 0.1275f,
                    gesture = HandGestures::topbarLightDragGesture
                )
            )
        })
    }
}
