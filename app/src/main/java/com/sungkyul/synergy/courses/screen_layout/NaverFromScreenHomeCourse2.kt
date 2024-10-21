package com.sungkyul.synergy.courses.screen_layout

import android.os.Build
import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.GALAXY_NOTE9
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.GALAXY_NOTE9_EMU
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.Models
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


            when(Build.MODEL) {
                GALAXY_NOTE9 -> hands.add(
                    EduHand(
                        id = "tap",
                        x = 0.5f,
                        y = 0.55f,
                        gesture = HandGestures::tapGesture
                    )
                )
                else -> hands.add(
                    EduHand(
                        id = "tap",
                        x = 0.5f,
                        y = Models.tunePos(0.55f, 0.7f, 0.45f),
                        gesture = HandGestures::tapGesture
                    )
                )
            }
        })
        list.add(EduData().apply {
            action.id = "click_back"

            when(Build.MODEL) {
                GALAXY_NOTE9 -> hands.add(
                    EduHand(
                        id = "tap",
                        x = 0.75f,
                        y = 0.85f,
                        rotation = 180f,
                        gesture = HandGestures::tapGesture
                    )
                )
                GALAXY_NOTE9_EMU -> hands.add(
                    EduHand(
                        id = "tap",
                        x = 0.75f,
                        y = 0.85f,
                        rotation = 180f,
                        gesture = HandGestures::tapGesture
                    )
                )
                else -> hands.add(
                    EduHand(
                        id = "tap",
                        x = 0.75f,
                        y = Models.tunePos(0.85f, 0.85f, 0.75f),
                        rotation = 180f,
                        gesture = HandGestures::tapGesture
                    )
                )
            }
        })

        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "다음과 같이 이전 단계로<br>이동하게 됩니다."
            dialog.background = R.drawable.edu_dialog_bg
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont= R.font.pretendard_semibold
            dialog.contentSize = when(Build.MODEL) {
                GALAXY_NOTE9 -> 22.0f
                else -> 26.0f
            }
            dialog.top = 0.4f
            dialog.bottom = 0.4f
            dialog.start = 0.05f
            dialog.end = 0.05f

            cover.isClickable = true
            cover.visibility = true
        })
    }
}
