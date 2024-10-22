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

data class GooglePutCodeCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "이렇게 문자로 인증코드가 옵니다."
            dialog.contentFont = R.font.pretendard_medium
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 350.0f/930.0f
            dialog.bottom = 350.0f/930.0f
            dialog.start = 24.0f/412.0f
            dialog.end = 24.0f/412.0f
            dialog.visibility = true
            cover.visibility = false
            cover.isClickable = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
        })

        list.add(EduData().apply {
            dialog.contentText="<p style='color:red'><b>잠깐!</b></p><br>현재 보이는<br>" +
                    "인증코드 메세지는<br>" +
                    "사생활 문제로 <br>" +
                    "실제 메세지 화면과<br>" +
                    "다릅니다!<br>가상의 화면일 뿐이랍니다.<br>참고해주세요!"
            dialog.background = R.drawable.edu_dialog_bg
            dialog.contentColor=R.color.black
            dialog.top=0.2f
            dialog.bottom=0.2f
            cover.visibility = true
        })

        list.add(EduData().apply {
            dialog.contentText = "코드 입력란에 인증 코드를 입력해주세요."
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
            action.id ="code_input"
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
                    y = Models.tunePos(290.0f/930.0f, 440.0f/930.0f, 290.0f/930.0f),

                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
