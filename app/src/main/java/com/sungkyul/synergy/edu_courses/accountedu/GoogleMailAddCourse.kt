package com.sungkyul.synergy.edu_courses.accountedu

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class GoogleMailAddCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "복구 이메일을 추가하는 화면입니다."
            dialog.contentFont = R.font.pretendard_medium
            dialog.contentSize = 26.0f
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 470.0f
            dialog.bottom = 30.0f
            dialog.start = 24.0f
            dialog.end = 24.0f
            dialog.visibility = true
            cover.visibility = false
            cover.isClickable = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
        })
        list.add(EduData().apply {
            dialog.contentText = "로그인에 문제가 생겼을 때 구글 측에서 연락을 주는 용도로 복구 이메일 주소를 추가하는 부분입니다."
            dialog.top = 390.0f
            dialog.bottom = 15.0f
            dialog.start = 24.0f
            dialog.end = 24.0f
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
            cover.visibility = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
        })
        list.add(EduData().apply {
            dialog.contentText = "구글이 아닌 다른 이메일 계정을 소유하고 있다면 입력하고 다음을 누르면 됩니다."
            dialog.top = 400.0f
            dialog.bottom = 15.0f
        })

        list.add(EduData().apply {
            dialog.contentText = "하지만 입력하지않아도 큰 문제는 없으니 저희는 건너뛰어 보겠습니다."
        })
        list.add(EduData().apply {
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id ="pass"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 210.0f,
                    y = 370.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
