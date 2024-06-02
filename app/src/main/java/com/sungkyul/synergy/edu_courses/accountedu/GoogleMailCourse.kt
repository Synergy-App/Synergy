package com.sungkyul.synergy.edu_courses.accountedu

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

// 액티비티: GoogleMailActivity
data class GoogleMailCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "아이디를 만드는 화면입니다."
            dialog.contentFont = R.font.pretendard_medium
            dialog.contentSize = 26.0f
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 500.0f
            dialog.bottom = 40.0f
            dialog.start = 24.0f
            dialog.end = 24.0f
            dialog.visibility = true
            cover.visibility = false
            cover.isClickable = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "구글은 아이디를 이메일 주소 형식으로 사용하여 아이디가<br>아이디명@gmail.com로 통일됩니다."
            dialog.top = 385.0f
            dialog.bottom = 15.0f
            dialog.start = 24.0f
            dialog.end = 24.0f
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "나의 이름이나 정보를 토대로 구글이 아이디를 추천해주기도 합니다."
            dialog.top = 400.0f
            dialog.bottom = 15.0f
            dialog.start = 24.0f
            dialog.end = 24.0f
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg

            cover.boxLeft = 10.0f
            cover.boxRight = width-10.0f
            cover.boxTop = 200.0f
            cover.boxBottom = 335.0f
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
        })
        list.add(EduData().apply {
            dialog.contentText = "아이디를 어떤것으로 만들지 모르겠다면 이 추천 아이디를 사용해도 됩니다."
        })
        list.add(EduData().apply {
            dialog.contentText = "하지만 내 아이디를 직접 만들고 싶다면 이 부분을 이용하면 됩니다."

            cover.boxLeft = 10.0f
            cover.boxRight = width-10.0f
            cover.boxTop = 330.0f
            cover.boxBottom = 395.0f
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
        })
        list.add(EduData().apply {
            dialog.contentText = "아이디를 만들어볼까요?"
            dialog.top = 70.0f
            dialog.bottom = 464.0f
            dialog.start = 24.0f
            dialog.end = 24.0f
            dialog.visibility = true
            cover.visibility = true
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg
        })
        list.add(EduData().apply {
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id ="mail_click"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 25.0f,
                    y = 335.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
        list.add(EduData().apply {
            cover.visibility = false
            cover.isClickable = false
            action.id ="mail_input"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 135.0f,
                    y = 435.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
        list.add(EduData().apply {
            cover.visibility = false
            cover.isClickable = false
            action.id ="btn_check"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 270.0f,
                    y = 555.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
