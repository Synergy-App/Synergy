package com.sungkyul.synergy.com.sungkyul.synergy.edu_courses.screen_layout

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class ScreenMenuCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "메뉴 화면입니다."
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
            dialog.contentFont = R.font.pretendard_semibold
            dialog.contentSize = 24.0f
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 0.05f
            dialog.bottom = 0.8f
            dialog.start = 0.05f
            dialog.end = 0.05f

            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.contentText = "휴대폰에 설치되어 있는<br>앱을 볼 수 있는<br>화면입니다."
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
            dialog.top = 0.35f
            dialog.bottom = 0.35f

            cover.visibility = true
        })

        list.add(EduData().apply {
            dialog.contentText = "메뉴 화면에 있는 앱을<br>홈 화면으로 꺼내볼까요?"
        })

        list.add(EduData().apply {
            dialog.contentText = "\"Play 스토어\" 앱을<br>표시된 곳에<br>이동시켜보세요."
            dialog.background = R.drawable.edu_dialog_green_bg
            dialog.contentColor = R.color.white
        })

        list.add(EduData().apply {
            dialog.visibility = false

            cover.isClickable = false
            cover.visibility = false

            action.id = "long_click_play_store_icon"

            hands.add(
                EduHand(
                    id = "touch",
                    x = 0.1f,
                    y = 0.6f,
                    gesture = HandGestures::tapGesture
                )
            )
        })
    }
}
