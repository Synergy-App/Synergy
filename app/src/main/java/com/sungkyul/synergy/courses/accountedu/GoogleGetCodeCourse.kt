package com.sungkyul.synergy.courses.accountedu

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AdaptiveUtils
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.Models
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class GoogleGetCodeCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "다음은 계정을 만들때<br>" +
                    "거치는 휴대전화 인증<br>" +
                    "단계입니다."
            dialog.contentFont = R.font.pretendard_medium
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 500.0f/930.0f
            dialog.bottom = 250.0f/930.0f
            dialog.start = 24.0f/412.0f
            dialog.end = 24.0f/412.0f
            dialog.visibility = true
            cover.visibility = false
            cover.isClickable = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
        })
        list.add(EduData().apply {
            dialog.contentText = "전화번호 기입란에 자신의 전화번호를 010부터 입력해주세요."
            dialog.top = 350.0f/930.0f
            dialog.bottom = 350.0f/930.0f
            dialog.start = 24.0f/412.0f
            dialog.end = 24.0f/412.0f
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
            cover.visibility = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg
        })
        list.add(EduData().apply {
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id ="phone_input"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 150.0f/412.0f,
                    y = Models.tunePos(210.0f/930.0f, 300.0f/930.0f, 210.0f/930.0f),

                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id ="btn_check"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 330.0f/412.0f,
                    y = Models.tunePos(380.0f/930.0f, 530.0f/930.0f, 380.0f/930.0f),

                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
