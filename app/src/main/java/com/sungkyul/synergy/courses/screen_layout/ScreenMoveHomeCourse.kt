package com.sungkyul.synergy.courses.screen_layout

import android.os.Build
import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.GALAXY_NOTE9
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduScreen

data class ScreenMoveHomeCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            action.id = "release_icon"
        })

        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "성공적으로<br>배치하였습니다!"
            dialog.contentFont= R.font.pretendard_semibold
            dialog.contentSize = when(Build.MODEL) {
                GALAXY_NOTE9 -> 22.0f
                else -> 26.0f
            }
            dialog.background = R.drawable.edu_dialog_yellow_bg
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 0.35f
            dialog.bottom = 0.35f
            dialog.start = 0.05f
            dialog.end = 0.05f

            cover.isClickable = true
            cover.visibility = true
        })

        list.add(EduData().apply {
            dialog.contentText = "자주 사용하는 앱을<br>홈 화면에 배치하면<br>빠르고 간편하게<br>사용할 수 있습니다."
            dialog.background = R.drawable.edu_dialog_bg
        })
    }
}
