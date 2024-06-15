package com.sungkyul.synergy.com.sungkyul.synergy.edu_courses.screen_layout

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class ScreenHomeCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "홈 화면입니다."
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont= R.font.pretendard_semibold
            dialog.contentSize = 26.0f
            dialog.top = 0.05f
            dialog.bottom = 0.8f
            dialog.start = 0.05f
            dialog.end = 0.05f

            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.contentText = "잠금 화면에서<br>잠금을 해제한 상태로<br>휴대폰을 사용할 수 있는<br>화면입니다."
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
            dialog.top = 0.35f
            dialog.bottom = 0.35f

            cover.visibility = true
        })

        list.add(EduData().apply {
            dialog.contentText = "배경화면이라고<br>부르기도 합니다."
            dialog.top = 0.4f
            dialog.bottom = 0.4f
        })

        list.add(EduData().apply {
            dialog.contentText = "홈 화면에<br>자주 사용하는<br>앱을 꺼내 편리하게<br>이용할 수 있습니다."
            dialog.top = 0.35f
            dialog.bottom = 0.35f
        })

        list.add(EduData().apply {
            dialog.contentText = "앱을 홈 화면에<br>꺼내기 위해서는<br>메뉴 화면으로<br>이동해야 합니다."
            dialog.top = 0.35f
            dialog.bottom = 0.35f
        })

        list.add(EduData().apply {
            dialog.contentText = "메뉴 화면으로\n이동하세요."
            dialog.background = R.drawable.edu_dialog_green_bg
            dialog.contentColor = R.color.white
            dialog.top = 0.4f
            dialog.bottom = 0.4f
        })

        list.add(EduData().apply {
            dialog.visibility = false

            cover.visibility = false
            cover.isClickable = false

            action.id = "show_menu_screen"

            hands.add(
                EduHand(
                    id = "drag",
                    x = 0.45f,
                    y = 0.75f,
                    gesture = HandGestures::homeVerticalDragGesture
                )
            )
        })
    }
}
