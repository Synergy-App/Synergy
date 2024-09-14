package com.sungkyul.synergy.courses.accountedu

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AdaptiveUtils
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class GoogleMainCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "구글 사이트의<br>메인 화면입니다."
            dialog.contentFont = R.font.pretendard_medium
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 26.0f/930.0f
            dialog.bottom = 700.0f/930.0f
            dialog.start = 24.0f/412.0f
            dialog.end = 24.0f/412.0f
            dialog.visibility = true
            cover.visibility = false
            cover.isClickable = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "로그인 버튼을 눌러볼까요?"
            dialog.top = 70.0f/930.0f
            dialog.bottom = 650.0f/930.0f
            dialog.start = 24.0f/412.0f
            dialog.end = 24.0f/412.0f
            dialog.visibility = true
            cover.visibility = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg

            cover.boxLeft = 280.0f/412.0f
            cover.boxRight = AdaptiveUtils.ratio((412.0f-8.0f)/412.0f)
            cover.boxTop = 2.0f/930.0f
            cover.boxBottom = 60.0f/930.0f
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
            hands.add(
                EduHand(
                    id = "tap",
                    x = AdaptiveUtils.ratio((412.0f-60.0f)/412.0f),
                    y = 50.0f/930.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
