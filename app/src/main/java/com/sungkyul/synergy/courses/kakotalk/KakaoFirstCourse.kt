package com.sungkyul.synergy.courses.settings

import android.os.Build
import android.util.Log
import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.utils.GALAXY_NOTE9
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduScreen

data class KakaoFirstCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        Log.i("model?", Build.MODEL)
        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "카카오톡 교육을<br>시작하겠습니다."
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
            bottomDialog.titleText = "카카오톡"
            bottomDialog.titleFont= R.font.pretendard_bold
            bottomDialog.titleSize = when(Build.MODEL) {
                GALAXY_NOTE9 -> 26.0f
                else -> 30.0f
            }
            bottomDialog.contentText = "카카오톡이란 문자 메세지를<br>보내거나 음성, 영상통화를<br>할 수 있는 필수적인 앱입니다."
            bottomDialog.contentColor = R.color.black
            bottomDialog.background = R.drawable.edu_bottom_dialog_bg
            bottomDialog.contentFont= R.font.pretendard_semibold
            bottomDialog.contentSize = when(Build.MODEL) {
                GALAXY_NOTE9 -> 22.0f
                else -> 26.0f
            }
        })

        list.add(EduData().apply {
            bottomDialog.contentText = "문자보다 편리하게 상대방과<br>대화를 나누고 사진이나<br>영상을 주고 받을 수 있죠."
        })

        list.add(EduData().apply {
            bottomDialog.contentText = "그렇지만 카카오톡에는<br>다양한 기능이 있어<br>복잡하고 어려울 수 있습니다."
        })

        list.add(EduData().apply {
            bottomDialog.contentText = "필수적으로 알아야하는<br>기능을 쉽게 설명해<br>드리겠습니다."
        })
    }
}
