package com.sungkyul.synergy.courses.app_installation

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AdaptiveUtils
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.Models
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class InstallLoadingCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            action.id = "complete_loading"

            cover.visibility = true

            bottomDialog.visibility = true
            bottomDialog.sebookImageVisibility = true
            bottomDialog.height = 0.2f
            bottomDialog.contentText = "로딩이 끝날 때까지<br>기다려주세요."
            bottomDialog.contentFont = R.font.pretendard_semibold
            bottomDialog.contentSize = AdaptiveUtils.dialogContentMedium()
        })

        list.add(EduData().apply {
            dialog.contentText = "앱을 실행하려면<br>열기 버튼을 누르면 됩니다."
            dialog.contentFont = R.font.pretendard_medium
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 350.0f/930.0f
            dialog.bottom = 350.0f/930.0f
            dialog.start = 24.0f/412.0f
            dialog.end = 24.0f/412.0f
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg

            bottomDialog.sebookImageVisibility = false
            bottomDialog.height = 0.0f

            cover.boxLeft = 0.8f
            cover.boxRight = 1.0f
            cover.boxTop = Models.tunePos(0.08f, 0.11f, 0.08f)
            cover.boxBottom = Models.tunePos(0.15f, 0.20f, 0.15f)
            cover.boxBorderVisibility=true
            cover.boxVisibility=true
        })
    }
}
