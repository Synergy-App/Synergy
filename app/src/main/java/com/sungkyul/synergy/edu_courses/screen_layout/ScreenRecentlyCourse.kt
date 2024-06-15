package com.sungkyul.synergy.com.sungkyul.synergy.edu_courses.screen_layout

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class ScreenRecentlyCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "최근에 실행했던<br>앱 목록들이<br>보이게 됩니다."
            dialog.contentColor = R.color.white
            dialog.contentGravity = Gravity.CENTER
            dialog.background = R.drawable.edu_dialog_black_bg
            dialog.top = 0.8f
            dialog.bottom = 0.04f
            dialog.start = 0.1f
            dialog.end = 0.1f

            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.contentText = "이전에 실행했던 앱을<br>다시 실행해야 할 때<br>바로 실행할 수 있어<br>편리합니다."
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
            dialog.top = 0.4f
            dialog.bottom = 0.4f

            cover.visibility = true
        })

        list.add(EduData().apply {
            dialog.contentText = "뒤로 가기 버튼을 누르면<br>원래 화면으로 돌아갑니다."
            dialog.top = 0.6f
            dialog.bottom = 0.2f

            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            cover.boxTop = 0.925f
            cover.boxBottom = 1.0f
            cover.boxLeft = 0.7f
            cover.boxRight = 1.0f
        })

        list.add(EduData().apply {
            dialog.visibility = false

            cover.visibility = false
            cover.isClickable = false

            action.id = "on_back_pressed"

            hands.add(
                EduHand(
                    id = "tap",
                    x = 0.75f,
                    y = 0.85f,
                    rotation = 180.0f,
                    gesture = HandGestures::tapGesture
                )
            )
        })
    }
}
