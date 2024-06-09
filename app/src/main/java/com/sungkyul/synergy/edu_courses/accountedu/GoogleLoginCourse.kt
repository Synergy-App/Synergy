package com.sungkyul.synergy.edu_courses.accountedu

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class GoogleLoginCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "로그인 화면입니다."
            dialog.contentFont = R.font.pretendard_medium
            dialog.contentSize = 26.0f
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 514.0f
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
            dialog.contentText = "구글 계정이 있다면 본인의 아이디와 비밀번호를 입력해 로그인을 하면 됩니다."
            dialog.top = 125.0f
            dialog.bottom = 295.0f
            dialog.start = 24.0f
            dialog.end = 24.0f
            dialog.visibility = true
            cover.visibility = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "하지만 아이디가 기억이 안나거나 새 계정을 만들고 싶을 때를 위해"
        })

        list.add(EduData().apply {
            dialog.contentText = "계정을 만드는 방법을 알려드리겠습니다."
            dialog.top = 145.0f
            dialog.bottom = 325.0f
        })

        list.add(EduData().apply {
            dialog.contentText = "먼저 계정 만들기 버튼을 클릭해주세요."
            dialog.top = 145.0f
            dialog.bottom = 325.0f
            dialog.start = 24.0f
            dialog.end = 24.0f
            dialog.visibility = true
            cover.visibility = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg
        })

        list.add(EduData().apply {
            cover.boxLeft = 16.0f
            cover.boxRight = 106.0f
            cover.boxTop = 400.0f
            cover.boxBottom = 460.0f
            cover.boxVisibility = true
            cover.boxBorderVisibility = true

            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            hands.add(
                EduHand(
                    id = "tap",
                    //x = 40.0f,
                    //y = 440.0f,
                    x = 0.0f, y = 0.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
