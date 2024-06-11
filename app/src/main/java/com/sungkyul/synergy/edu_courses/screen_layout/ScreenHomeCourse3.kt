package com.sungkyul.synergy.com.sungkyul.synergy.edu_courses.screen_layout

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class ScreenHomeCourse3(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "하단바 화면입니다."
            dialog.background = R.drawable.edu_dialog_black_bg
            dialog.contentColor = R.color.white
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 0.8f
            dialog.bottom = 0.1f
            dialog.start = 0.05f
            dialog.end = 0.05f

            cover.visibility = true
            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.contentText = "하단바란<br>휴대폰 하단에<br>세 개의 버튼으로 존재하며<br>화면을 이동할 때<br>사용합니다."
            dialog.background = R.drawable.edu_dialog_bg
            dialog.contentColor = R.color.black
            dialog.top = 0.5f
            dialog.bottom = 0.15f

            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            cover.boxTop = 0.9f
            cover.boxBottom = 1.0f
            cover.boxLeft = 0.0f
            cover.boxRight = 1.0f
        })

        list.add(EduData().apply {
            dialog.contentText = "최근에 실행한 앱을<br>보는 버튼입니다."
            dialog.top = 0.7f

            cover.boxLeft = 0.125f
            cover.boxRight = 0.325f
        })

        list.add(EduData().apply {
            dialog.contentText = "최근 실행 앱 버튼을<br>터치하세요."
            dialog.background = R.drawable.edu_dialog_green_bg
            dialog.contentColor = R.color.white
            dialog.top = 0.4f
            dialog.bottom = 0.4f

            cover.boxVisibility = false
            cover.boxBorderVisibility = false
        })

        list.add(EduData().apply {
            dialog.visibility = false

            cover.visibility = false
            cover.isClickable = false

            action.id = "show_recently_screen"

            hands.add(
                EduHand(
                    id = "touch",
                    x = 0.15f,
                    y = 0.825f,
                    rotation = 180.0f,
                    gesture = HandGestures::tapGesture
                )
            )
        })
    }
}
