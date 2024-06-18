package com.sungkyul.synergy.com.sungkyul.synergy.edu_courses.screen_layout

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class NaverFromScreenHomeCourse2(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            action.id = "click_ad2"

            hands.add(
                EduHand(
                    id = "tap",
                    x = 0.5f,
                    y = 0.7f,
                    gesture = HandGestures::tapGesture
                )
            )
        })
        list.add(EduData().apply {
            action.id = "click_back"

            hands.add(
                EduHand(
                    id = "tap",
                    x = 0.7f,
                    y = 0.8f,
                    rotation = 180f,
                    gesture = HandGestures::tapGesture
                )
            )
        })

        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "다음과 같이 이전 단계로<br>이동하게 됩니다."
            dialog.background = R.drawable.edu_dialog_bg
            dialog.contentFont = R.font.pretendard_semibold
            dialog.contentSize = 24.0f
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 0.4f
            dialog.bottom = 0.4f
            dialog.start = 0.05f
            dialog.end = 0.05f

            cover.isClickable = true
            cover.visibility = true
        })
    }
}
