package com.sungkyul.synergy.courses.app_installation

import android.os.Build
import android.util.Log
import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.GALAXY_NOTE9
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.Models
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduScreen

data class InstallFirstCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        Log.i("model?", Build.MODEL)
        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "앱 설치 교육을<br>시작하겠습니다."
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
            dialog.contentText = "앱을 설치하려면<br>\"Play스토어\"라는<br>앱을 사용합니다."
        })

        list.add(EduData().apply {
            dialog.visibility = false

            bottomDialog.sebookImageVisibility = true
            bottomDialog.height = Models.tunePos(0.3f, 0.4f, 0.3f)
            bottomDialog.titleText = "Play 스토어"
            bottomDialog.titleFont= R.font.pretendard_bold
            bottomDialog.titleSize = when(Build.MODEL) {
                GALAXY_NOTE9 -> 26.0f
                else -> 30.0f
            }
            bottomDialog.contentText = "간단하게 말해서 스마트폰의<br>유용한 '상점'이라고 생각하시면<br>됩니다."
            bottomDialog.contentColor = R.color.black
            bottomDialog.background = R.drawable.edu_bottom_dialog_bg
            bottomDialog.contentFont= R.font.pretendard_semibold
            bottomDialog.contentSize = when(Build.MODEL) {
                GALAXY_NOTE9 -> 22.0f
                else -> 26.0f
            }
        })

        list.add(EduData().apply {
            bottomDialog.contentText = "원하는 앱을 사용하기<br>위해서는 이 앱에 들어가서<br>설치를 하면 됩니다."
        })
    }
}
