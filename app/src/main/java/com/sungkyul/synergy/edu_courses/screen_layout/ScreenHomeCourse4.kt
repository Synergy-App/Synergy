package com.sungkyul.synergy.com.sungkyul.synergy.edu_courses.screen_layout

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class ScreenHomeCourse4(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "홈 화면으로 이동하는<br>버튼입니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont = R.font.pretendard_semibold
            dialog.contentSize = 24.0f
            dialog.top = 0.7f
            dialog.bottom = 0.1f
            dialog.start = 0.05f
            dialog.end = 0.05f

            cover.visibility = true
            cover.isClickable = true
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            cover.boxTop = 0.9f
            cover.boxBottom = 1.0f
            cover.boxLeft = 0.4f
            cover.boxRight = 0.6f
        })

        list.add(EduData().apply {
            dialog.contentText = "메뉴 화면에서 \"네이버\"를 터치해 실행한 후 다시 홈 화면으로 이동하세요."
            dialog.background = R.drawable.edu_dialog_green_bg
            dialog.contentColor = R.color.white
            dialog.top = 0.25f
            dialog.bottom = 0.45f

            cover.boxVisibility = false
            cover.boxBorderVisibility = false
        })

        list.add(EduData().apply {
            dialog.visibility = false

            cover.visibility = false
            cover.isClickable = false

            action.id = "show_menu_screen"

            hands.add(
                EduHand(
                    id = "drag",
                    x = 0.5f,
                    y = 0.5f,
                    gesture = HandGestures::homeVerticalDragGesture
                )
            )
        })
    }
}
