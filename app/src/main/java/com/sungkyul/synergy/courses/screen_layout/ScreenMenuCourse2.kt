package com.sungkyul.synergy.com.sungkyul.synergy.edu_courses.screen_layout

import android.os.Build
import com.sungkyul.synergy.utils.GALAXY_NOTE9
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class ScreenMenuCourse2(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            cover.boxBorderVisibility = true
            cover.boxTop = when(Build.MODEL) {
                GALAXY_NOTE9 -> 0.105f
                else -> 0.1f
            }
            cover.boxBottom = 0.25f
            cover.boxLeft = when(Build.MODEL) {
                GALAXY_NOTE9 -> 0.255f
                else -> 0.25f
            }
            cover.boxRight = 0.5f

            action.id = "click_naver"

            hands.add(
                EduHand(
                    id = "touch",
                    x = 0.35f,
                    y = 0.2f,
                    gesture = HandGestures::tapGesture
                )
            )
        })
    }
}
