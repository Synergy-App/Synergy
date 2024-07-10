package com.sungkyul.synergy.courses.default_app.message

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class DefaultMessageChattingCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    //문자 첫 화면~1:1채팅 누르는거까지
    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "문자 화면 입니다."
            dialog.contentFont = R.font.pretendard_medium
            dialog.contentSize = 26.0f
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 26.0f/930.0f
            dialog.bottom = 700.0f/930.0f
            dialog.start = 24.0f/412.0f
            dialog.end = 24.0f/412.0f
            cover.visibility = false
            cover.isClickable = true
            dialog.visibility = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "이 부분을 통해<br>이전에 주고 받은 메세지<br>내역을 확인할 수 있습니다."
            dialog.top = 50.0f/930.0f
            dialog.bottom = 600.0f/930.0f
            cover.boxVisibility = true
            cover.boxLeft = 0.0f
            cover.boxTop = 280.0f/930.0f
            cover.boxRight = 1.0f
            cover.boxBottom = (930.0f-60.0f)/930.0f
            cover.visibility = true
            cover.boxBorderVisibility = true
            cover.boxBorderColor = R.color.black
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "메세지를 보내는<br>버튼입니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 380.0f/930.0f
            dialog.bottom = 200.0f/930.0f
            cover.boxLeft = 0.75f
            cover.boxTop = 0.725f
            cover.boxRight = 0.95f
            cover.boxBottom = 0.825f
            cover.boxBorderColor = R.color.lime
        })

        list.add(EduData().apply {
            dialog.contentText = "메시지를 보내볼까요?"
            dialog.top = 300.0f/930.0f
            dialog.bottom = 400.0f/930.0f
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id = "message_button"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 340.0f/412.0f,
                    y = 710.0f/930.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id = "one_message_button"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 340.0f/412.0f,
                    y = 570.0f/930.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })


    }
}
