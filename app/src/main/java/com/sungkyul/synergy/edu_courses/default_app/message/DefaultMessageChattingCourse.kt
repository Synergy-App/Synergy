package com.sungkyul.synergy.edu_courses.default_app.message

import android.view.Gravity
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

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            cover.isClickable = true
            dialog.contentText = "여기는 그동안<br>주고받은 메시지를<br>확인할 수 있어요."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 50.0f
            dialog.bottom = 550.0f
            dialog.start = 50.0f
            dialog.end = 50.0f
            cover.boxLeft = 0.0f
            cover.boxTop = 280.0f
            cover.boxRight = width-0.0f
            cover.boxBottom = height-60.0f
            dialog.visibility = true
            cover.visibility = true
            cover.boxStrokeVisibility = true
            cover.boxVisibility = true
        })

        list.add(EduData().apply {
            dialog.duration = 1000
            cover.duration = 750
            arrow.duration = 1000
            dialog.contentText = "이 버튼은 새로운<br>메시지를 작성할 수 있어요."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 350.0f
            dialog.bottom = 250.0f
            cover.boxLeft = width-80.0f
            cover.boxTop = 630.0f
            cover.boxRight = width-10.0f
            cover.boxBottom = height-70.0f
            arrow.endTo = EduScreen.BOX
            arrow.visibility = true
        })

        list.add(EduData().apply {
            dialog.duration = 750
            arrow.duration = 750
            dialog.contentText = "메시지를 작성해볼까요?"
            dialog.top = 300.0f
            dialog.bottom = 300.0f
            arrow.endTo = EduScreen.DIALOG
            cover.boxVisibility = false
            cover.boxStrokeVisibility = false
        })

        list.add(EduData().apply {
            cover.duration = 0
            dialog.contentText = "시너지를 클릭해주세요."
            dialog.top = 250.0f
            dialog.bottom = 350.0f
            cover.boxLeft = 0.0f
            cover.boxTop = 135.0f
            cover.boxRight = width-0.0f
            cover.boxBottom = 200.0f
            arrow.endTo = EduScreen.BOX
            cover.boxVisibility = true
            cover.boxStrokeVisibility = true
            arrow.visibility = true
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.boxVisibility = false
            cover.boxStrokeVisibility = false
            arrow.visibility = false
            action.id = "click_message_chatting_item"
            action.message = "시너지"
            hands.add(
                EduHand(
                    id = "tap",
                    x = width/2,
                    y = 170.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
