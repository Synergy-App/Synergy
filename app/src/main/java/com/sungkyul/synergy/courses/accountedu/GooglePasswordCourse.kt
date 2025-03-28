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

data class GooglePasswordCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "다음은 비밀번호를<br>만드는 화면입니다."
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
            dialog.contentText = "문자, 숫자, 기호를 조합하여 비밀번호를 만들면 됩니다."
            dialog.top = 400.0f/930.0f
            dialog.bottom = 300.0f/930.0f
            dialog.start = 24.0f/412.0f
            dialog.end = 24.0f/412.0f
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "해당 버튼을 누르면 보안을 위해 가려져 있던 비밀번호가 보이게 됩니다."
            dialog.top = Models.tunePos(400.0f/930.0f, 500.0f/930.0f, 400.0f/930.0f)
            dialog.bottom = Models.tunePos(300.0f/930.0f, 200.0f/930.0f, 300.0f/930.0f)

            cover.boxLeft = 10.0f/412.0f
            cover.boxRight = (412.0f-260.0f)/412.0f
            cover.boxTop = Models.tunePos(300.0f/930.0f, 440.0f/930.0f, 300.0f/930.0f)
            cover.boxBottom = Models.tunePos(335.0f/930.0f, 495.0f/930.0f, 335.0f/930.0f)
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
        })

        list.add(EduData().apply {
            dialog.contentText = "비밀번호란에 원하는 비밀번호를 입력해주세요."
            dialog.top = 500.0f/930.0f
            dialog.bottom = 250.0f/930.0f
            dialog.start = 24.0f/412.0f
            dialog.end = 24.0f/412.0f
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg
        })

        list.add(EduData().apply {
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id ="pw_input"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 150.0f/412.0f,
                    y = Models.tunePos(210.0f/930.0f, 280.0f/930.0f, 210.0f/930.0f),

                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        list.add(EduData().apply {
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id ="pw_check_input"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 150.0f/412.0f,
                    y = Models.tunePos(280.0f/930.0f, 380.0f/930.0f, 280.0f/930.0f),

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
                    y = Models.tunePos(380.0f/930.0f, 550.0f/930.0f, 380.0f/930.0f),

                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
