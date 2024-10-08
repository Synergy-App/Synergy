package com.sungkyul.synergy.courses.screen_layout

import android.os.Build
import android.util.Log
import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.GALAXY_NOTE9
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduScreen

data class ScreenFirstCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        Log.i("model?", Build.MODEL)
        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "화면구성 교육을<br>시작하겠습니다."
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont= R.font.pretendard_semibold
            dialog.contentSize = when(Build.MODEL) {
                GALAXY_NOTE9 -> 22.0f
                else -> 26.0f
            }
            dialog.top = 0.4f
            dialog.bottom = 0.35f
            dialog.start = 0.05f
            dialog.end = 0.05f

            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.contentText = "휴대폰을 편리하게<br>사용하기 위해서<br>우리 눈에 보이는<br>화면들의 구성을<br>이해할 필요가 있습니다."
            dialog.top = 0.4f
            dialog.bottom = 0.25f
        })

        list.add(EduData().apply {
            dialog.contentText = "화면구성에는<br>잠금화면, 홈 화면,<br>메뉴화면, 상단바,<br>하단바가 있습니다."
        })

        list.add(EduData().apply {
            dialog.contentText = "먼저 잠금화면에 대해<br>알아볼까요?"

            dialog.top = 0.4f
            dialog.bottom = 0.35f
            dialog.start = 0.05f
            dialog.end = 0.05f
        })
    }
}
