package com.sungkyul.synergy.edu_courses.default_app.message

import android.view.Gravity
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class DefaultMessageCourse(val eduScreen: EduScreen): EduCourse {
    override val eduDataList = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        eduDataList.add(EduData().apply {
            dialog.contentText = "여기는 채팅방으로<br>상대방과 메시지를<br>주고받을 수 있어요."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 300.0f
            dialog.bottom = 300.0f
            dialog.start = 50.0f
            dialog.end = 50.0f
            dialog.visibility = true
            cover.visibility = true
        })

        eduDataList.add(EduData().apply {
            dialog.duration = 750
            arrow.duration = 750
            dialog.contentText = "여기서 문자 메시지를<br>입력할 수 있어요."
            dialog.top = 450.0f
            dialog.bottom = 150.0f
            cover.boxLeft = 90.0f
            cover.boxTop = height-60.0f
            cover.boxRight = width-90.0f
            cover.boxBottom = height
            arrow.endTo = EduScreen.BOX
            cover.boxVisibility = true
            cover.boxStrokeVisibility = true
            arrow.visibility = true
        })

        eduDataList.add(EduData().apply {
            dialog.duration = 750
            cover.duration = 750
            arrow.duration = 750
            dialog.contentText = "이 버튼을 누르면<br>메시지가 전송돼요."
            cover.boxLeft = width-55.0f
            cover.boxRight = width
        })

        eduDataList.add(EduData().apply {
            dialog.contentText = "시너지에게<br>메시지를 보내볼까요?"
            dialog.top = 300.0f
            dialog.bottom = 300.0f
            arrow.endTo = EduScreen.DIALOG
            cover.boxVisibility = false
            cover.boxStrokeVisibility = false
        })

        eduDataList.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            arrow.visibility = false
            action.id = "click_message_edit_text"
            hands.add(
                EduHand(
                    id = "tap",
                    x = width/2,
                    y = height-110.0f,
                    rotation = 180.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        eduDataList.add(EduData().apply {
            action.id = "click_send_button"
        })

        eduDataList.add(EduData().apply {
            dialog.duration = 0
            cover.duration = 0
            dialog.contentText = "문자 메시지를<br>성공적으로 보냈습니다!"
            dialog.top = 250.0f
            dialog.bottom = 350.0f
            cover.boxLeft = 0.0f
            cover.boxTop = height
            cover.boxRight = width
            cover.boxBottom = height
            dialog.visibility = true
            cover.visibility = true
        })

        eduDataList.add(EduData().apply {
            dialog.duration = 750
            cover.duration = 750
            dialog.contentText = "자신이 보낸 메시지는<br>아래에 보이는 것처럼<br>확인할 수 있어요."
            dialog.top = 150.0f
            dialog.bottom = 450.0f
            cover.boxTop = 360.0f
            cover.boxBottom = height-60.0f
            cover.boxVisibility = true
            cover.boxStrokeVisibility = true
        })
    }
}
