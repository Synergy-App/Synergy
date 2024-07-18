package com.sungkyul.synergy.courses.default_app.camera

import android.os.Build
import android.util.Log
import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.utils.GALAXY_NOTE9
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduScreen

data class DefaultCameraFirstCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        Log.i("model?", Build.MODEL)
        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "카메라 교육을<br>시작하겠습니다."
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
            bottomDialog.titleText = "카메라"
            bottomDialog.titleFont= R.font.pretendard_bold
            bottomDialog.titleSize = when(Build.MODEL) {
                GALAXY_NOTE9 -> 26.0f
                else -> 30.0f
            }
            bottomDialog.contentText = "자신이 찍고 싶은 사진을<br>촬영할 수 있는 어플입니다."
            bottomDialog.contentColor = R.color.black
            bottomDialog.background = R.drawable.edu_bottom_dialog_bg
            bottomDialog.contentFont= R.font.pretendard_semibold
            bottomDialog.contentSize = when(Build.MODEL) {
                GALAXY_NOTE9 -> 22.0f
                else -> 26.0f
            }
        })

        list.add(EduData().apply {
            bottomDialog.contentText = "휴대폰 전면에 존재하는<br>렌즈로 본인의 모습을(셀카)<br>담을 수 있고,"
        })

        list.add(EduData().apply {
            bottomDialog.contentText = "후면에 존재하는 렌즈로<br>원하는 모습을 담을<br>수 있습니다."
        })
    }
}
