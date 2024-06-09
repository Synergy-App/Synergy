package com.sungkyul.synergy.edu_courses.default_app.message

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class DefaultMessageCourse2(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    //메세지 채팅
    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "메세지를 작성하는<br>화면입니다."
            dialog.contentFont = R.font.pretendard_medium
            dialog.contentSize = 26.0f
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 55.0f
            dialog.bottom = 600.0f
            dialog.start = 24.0f
            dialog.end = 24.0f
            cover.boxVisibility = true
            cover.visibility = false
            cover.isClickable = true
            dialog.visibility = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "상대방에게 보낼 글을<br>작성하는 곳입니다."
            dialog.top = 630.0f
            dialog.bottom = 100.0f
            cover.boxLeft = 90.0f
            cover.boxTop = height-60.0f
            cover.boxRight = width-90.0f
            cover.boxBottom = height
            cover.visibility = true
            cover.boxBorderVisibility = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "메세지를 전송하는<br>버튼입니다."
            cover.boxLeft = width-60.0f
            cover.boxRight = width
        })

        list.add(EduData().apply {
            dialog.contentText = "글을 다 작성하고<br>이 버튼을 눌러야만<br>메세지가 전송됩니다."
            dialog.top = 580.0f
            dialog.bottom = 100.0f

        })

        list.add(EduData().apply {
            dialog.contentText = "'안녕하세요'를<br>보내볼까요?"
            dialog.top = 300.0f
            dialog.bottom = 400.0f
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
        })



        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            arrow.visibility = false
            action.id = "click_message_edit_text"
            hands.add(
                EduHand(
                    id = "tap",
                    //x = width/2,
                    //y = height-110.0f,
                    x = 0.0f, y = 0.0f,
                    rotation = 180.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        list.add(EduData().apply {
            action.id = "click_send_button"
            EduHand(
                id = "tap",
                //x = 300.0f,
                //y = height-110.0f,
                x = 0.0f, y = 0.0f,
                rotation = 180.0f,
                gesture = HandGestures.Companion::tapGesture
            )
        })

        list.add(EduData().apply {
            dialog.contentText = "문자 메시지를<br>성공적으로 보냈습니다!"
            dialog.top = 300.0f
            dialog.bottom = 430.0f
            cover.boxLeft = 0.0f
            cover.boxTop = height
            cover.boxRight = width
            cover.boxBottom = height
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
            dialog.background = R.drawable.edu_dialog_yellow_bg
            dialog.contentColor = R.color.black

        })

        list.add(EduData().apply {
            dialog.contentText = "내가 보낸 메세지는<br>우측에 위치합니다."
            dialog.top = 200.0f
            dialog.bottom = 500.0f
            cover.boxTop = 80.0f
            cover.boxLeft = 400.0f
            cover.boxBottom = height-680.0f
            cover.boxRight = width/2
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            dialog.background = R.drawable.edu_dialog_bg

        })

        list.add(EduData().apply {
            dialog.contentText = "다시 메세지 내역으로<br>돌아가볼까요?"
            dialog.top = 300.0f
            dialog.bottom = 400.0f
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
        })

        list.add(EduData().apply {
            cover.visibility = false
            cover.isClickable = false
            dialog.visibility = false
            action.id = "menu_button"
            EduHand(
                id = "tap",
                //x = 300.0f,
                //y = height-110.0f,
                x = 0.0f, y = 0.0f,
                gesture = HandGestures.Companion::tapGesture
            )
        })

    }
}
