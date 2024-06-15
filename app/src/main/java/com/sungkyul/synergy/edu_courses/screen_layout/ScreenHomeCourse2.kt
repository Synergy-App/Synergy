package com.sungkyul.synergy.com.sungkyul.synergy.edu_courses.screen_layout

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class ScreenHomeCourse2(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "다음은 상단바입니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont = R.font.pretendard_semibold
            dialog.contentSize = 24.0f
            dialog.top = 0.4f
            dialog.bottom = 0.4f
            dialog.start = 0.05f
            dialog.end = 0.05f

            cover.visibility = true
            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.contentText = "상단바란<br>휴대폰 상단에 있는<br>작은 상태 표시줄로<br>와이파이, 벨소리 조절,<br>밝기 조절 등<br>여러 기능들을<br>이용할 수 있습니다."
            dialog.top = 0.1f
            dialog.bottom = 0.5f

            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            cover.boxTop = 0.0f
            cover.boxBottom = 0.06f
            cover.boxLeft = 0.0f
            cover.boxRight = 1.0f
        })

        list.add(EduData().apply {
            dialog.contentText = "상단바를 보기 위해서는<br>상단바를 밑으로<br>끌어내려야 합니다."
            dialog.top = 0.1f
            dialog.bottom = 0.6f
        })

        list.add(EduData().apply {
            dialog.contentText = "상단바를 내려볼까요?"
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg
            dialog.top = 0.4f
            dialog.bottom = 0.4f

            cover.boxVisibility = false
            cover.boxBorderVisibility = false
        })

        list.add(EduData().apply {
            dialog.visibility = false

            cover.visibility = false
            cover.isClickable = false

            action.id = "show_topbar_screen"

            hands.add(
                EduHand(
                    id = "drag",
                    x = 0.5f,
                    y = 0.025f,
                    gesture = HandGestures::homeTopbarDragGesture
                )
            )
        })
    }
}
