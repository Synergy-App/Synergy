package com.sungkyul.synergy.edu_courses.default_app.message

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

class DefaultMessageCourse1 (val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    //문자 보낼 상대방 선택하기
    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "문자를 보낼 상대방을<br>선택하는 화면 입니다."
            dialog.contentFont = R.font.pretendard_medium
            dialog.contentSize = 26.0f
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 26.0f
            dialog.bottom = 670.0f
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
            dialog.contentText = "보내고 싶은 상대를<br>클릭해주세요."
            dialog.top = 300.0f
            dialog.bottom = 400.0f
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
            cover.visibility = true
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id = "click_captain_contact_item"
            hands.add(
                EduHand(
                    id = "tap",
                    //x = 150.0f,
                    //y = height/2,
                    target = null,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        //여기에 계속 추가


        //마지막
        }
}