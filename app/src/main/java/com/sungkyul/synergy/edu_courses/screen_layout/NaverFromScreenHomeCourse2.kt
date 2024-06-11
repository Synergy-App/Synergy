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
                    y = 0.75f,
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
    }
}
