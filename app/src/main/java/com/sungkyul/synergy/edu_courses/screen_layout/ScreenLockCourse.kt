package com.sungkyul.synergy.com.sungkyul.synergy.edu_courses.screen_layout

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class ScreenLockCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "잠금화면입니다."
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 0.05f
            dialog.bottom = 0.8f
            dialog.start = 0.05f
            dialog.end = 0.05f

            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.contentText = "휴대폰의 전원버튼만<br>켠 상태로,<br><br>아직 잠금을 풀지 않은<br>상태입니다."
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
            dialog.top = 0.3f
            dialog.bottom = 0.3f

            cover.visibility = true
        })

        list.add(EduData().apply {
            dialog.contentText = "비밀번호나 패턴을 통해<br>남들이 보지 못하도록<br>휴대폰을 잠글 수 있습니다."
            dialog.top = 0.4f
            dialog.bottom = 0.4f
        })

        list.add(EduData().apply {
            dialog.contentText = "잠금을 해제하세요."
            dialog.background = R.drawable.edu_dialog_green_bg
            dialog.contentColor = R.color.white
            dialog.top = 0.42f
            dialog.bottom = 0.42f
        })

        list.add(EduData().apply {
            dialog.visibility = false

            cover.visibility = false
            cover.isClickable = false

            hands.add(EduHand(
                id = "drag",
                x = 0.45f,
                y = 0.75f,
                gesture = HandGestures::lockVerticalDragGesture
            ))
        })
    }
}
