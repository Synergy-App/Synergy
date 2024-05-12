package com.sungkyul.synergy.edu_courses.default_app.phone

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class DefaultPhoneCallCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "전화 연결에 성공하였습니다!"
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 300.0f
            dialog.bottom = 300.0f
            dialog.start = 50.0f
            dialog.end = 50.0f
            dialog.background = R.drawable.edu_dialog_yellow_bg
            dialog.visibility = true

            cover.visibility = true
        })

        list.add(EduData().apply {
            dialog.contentText = "지금 현재 상대방에게<br>통화하고 있는 중이에요."
            dialog.background = R.drawable.edu_dialog_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "근데 상대방이 전화를<br>안 받는 것 같아요.<br>통화를 끊어볼까요?"
        })

        list.add(EduData().apply {
            dialog.visibility = false

            cover.visibility = false

            action.id = "click_hang_up_button"

            hands.add(
                EduHand(
                    id = "tap",
                    x = 190.0f,
                    y = 700.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        list.add(EduData())
    }
}
