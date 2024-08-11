package com.sungkyul.synergy.courses.default_app.message

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AdaptiveUtils
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
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            dialog.contentGravity = Gravity.CENTER
            dialog.top = AdaptiveUtils.ratio(0.4f)
            dialog.bottom = AdaptiveUtils.ratio(0.4f)
            dialog.start = AdaptiveUtils.ratio(0.1f)
            dialog.end = AdaptiveUtils.ratio(0.1f)
            cover.boxVisibility = true
            cover.visibility = false
            cover.isClickable = true
            dialog.visibility = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "상대방에게 보낼 글을<br>작성하는 곳입니다."
            dialog.top = AdaptiveUtils.ratio(630.0f/930.0f)
            dialog.bottom = AdaptiveUtils.ratio(100.0f/930.0f)
            cover.boxLeft = AdaptiveUtils.ratio(0.21f)
            cover.boxTop = AdaptiveUtils.ratio(0.86f)
            cover.boxRight = AdaptiveUtils.ratio(0.79f)
            cover.boxBottom = AdaptiveUtils.ratio(1.0f)
            cover.visibility = true
            cover.boxBorderVisibility = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "메세지를 전송하는<br>버튼입니다."
            cover.boxLeft = AdaptiveUtils.ratio(0.86f)
            cover.boxRight = AdaptiveUtils.ratio((412.0f)/412.0f)
        })

        list.add(EduData().apply {
            dialog.contentText = "글을 다 작성하고<br>이 버튼을 눌러야만<br>메세지가 전송됩니다."
            dialog.top = AdaptiveUtils.ratio(580.0f/930.0f)
            dialog.bottom = AdaptiveUtils.ratio(100.0f/930.0f)

        })

        list.add(EduData().apply {
            dialog.contentText = "상대방에게<br>'안녕하세요'를 보내볼까요?"
            dialog.top = AdaptiveUtils.ratio(300.0f/930.0f)
            dialog.bottom = AdaptiveUtils.ratio(450.0f/930.0f)
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
                    x = AdaptiveUtils.ratio((412.0f/2)/412.0f),
                    y = AdaptiveUtils.ratio(0.8f),
                    rotation = 180.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        list.add(EduData().apply {
            action.id = "click_send_button"
            EduHand(
                id = "tap",
                x = AdaptiveUtils.ratio(300.0f/412.0f),
                y = AdaptiveUtils.ratio((930.0f-200.0f)/930.0f),
                rotation = 180.0f,
                gesture = HandGestures.Companion::tapGesture
            )
        })

        list.add(EduData().apply {
            dialog.contentText = "메세지 전송에<br>성공했습니다!"
            dialog.top = AdaptiveUtils.ratio(300.0f/930.0f)
            dialog.bottom = AdaptiveUtils.ratio(430.0f/930.0f)
            cover.boxLeft = AdaptiveUtils.ratio(0.0f)
            cover.boxTop = AdaptiveUtils.ratio(1.0f)
            cover.boxRight = AdaptiveUtils.ratio(1.0f)
            cover.boxBottom = AdaptiveUtils.ratio(1.0f)
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
            dialog.background = R.drawable.edu_dialog_yellow_bg
            dialog.contentColor = R.color.black

        })

        list.add(EduData().apply {
            dialog.contentText = "내가 보낸 메세지는<br>우측에 뜨게 되고,"
            dialog.top = AdaptiveUtils.ratio(200.0f/930.0f)
            dialog.bottom = AdaptiveUtils.ratio(500.0f/930.0f)
            cover.boxTop = AdaptiveUtils.ratio(80.0f/930.0f)
            cover.boxLeft = AdaptiveUtils.ratio(0.5f)
            cover.boxBottom = AdaptiveUtils.ratio((930.0f-770.0f)/930.0f)
            cover.boxRight = AdaptiveUtils.ratio(1.0f)
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            dialog.background = R.drawable.edu_dialog_bg

        })

        list.add(EduData().apply {
            dialog.contentText = "상대방이 보낸 메세지는<br>좌측에 뜨게 됩니다."
            dialog.top = AdaptiveUtils.ratio(200.0f/930.0f)
            dialog.bottom = AdaptiveUtils.ratio(500.0f/930.0f)
            cover.boxTop = AdaptiveUtils.ratio(80.0f/930.0f)
            cover.boxLeft = AdaptiveUtils.ratio(0.0f)
            cover.boxBottom = AdaptiveUtils.ratio((930.0f-770.0f)/930.0f)
            cover.boxRight = AdaptiveUtils.ratio(0.5f)
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            dialog.background = R.drawable.edu_dialog_bg

        })

        list.add(EduData().apply {
            dialog.contentText = "다시 메세지 내역으로<br>이동해볼까요?"
            dialog.top = AdaptiveUtils.ratio(300.0f/930.0f)
            dialog.bottom = AdaptiveUtils.ratio(400.0f/930.0f)
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
            hands.add(EduHand(
                id = "tap",
                x = AdaptiveUtils.ratio(0.02f),
                y = AdaptiveUtils.ratio(0.02f),
                gesture = HandGestures.Companion::tapGesture
            ))
        })

    }
}
