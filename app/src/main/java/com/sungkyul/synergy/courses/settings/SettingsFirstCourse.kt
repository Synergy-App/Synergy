package com.sungkyul.synergy.courses.settings

import android.os.Build
import android.util.Log
import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.utils.GALAXY_NOTE9
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class SettingsFirstCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        Log.i("model?", Build.MODEL)
        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "지금부터<br>환경설정 교육을<br>시작하겠습니다."
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

            bottomDialog.visibility = true

            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.visibility = false

            bottomDialog.sebookImageVisibility = true
            bottomDialog.height = 0.3f
            bottomDialog.titleText = "환경설정"
            bottomDialog.titleFont= R.font.pretendard_bold
            bottomDialog.titleSize = when(Build.MODEL) {
                GALAXY_NOTE9 -> 26.0f
                else -> 30.0f
            }
            bottomDialog.contentText = "환경설정은 휴대폰의 기본<br>정보나 여러 기능을 설정<br>할 수 있는 앱입니다."
            bottomDialog.contentColor = R.color.black
            bottomDialog.background = R.drawable.edu_bottom_dialog_bg
            bottomDialog.contentFont= R.font.pretendard_semibold
            bottomDialog.contentSize = when(Build.MODEL) {
                GALAXY_NOTE9 -> 22.0f
                else -> 26.0f
            }
        })

        list.add(EduData().apply {
            bottomDialog.contentText = "많은 정보와 기능이 있어<br>헷갈릴 수 있는데요."
        })

        list.add(EduData().apply {
            bottomDialog.contentText = "주로 사용하게 될 기능인<br>글자 크기 조절을 교육<br>하려 합니다."
        })
    }
}
