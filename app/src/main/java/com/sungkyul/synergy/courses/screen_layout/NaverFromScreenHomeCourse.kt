package com.sungkyul.synergy.courses.screen_layout

import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class NaverFromScreenHomeCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {

        list.add(EduData().apply {
            action.id = "click_main"

            hands.add(
                EduHand(
                    id = "tap",
                    x = 0.44f,
                    y = 0.85f,
                    rotation = 180f,
                    gesture = HandGestures::tapGesture
                )
            )
        })
    }
}
