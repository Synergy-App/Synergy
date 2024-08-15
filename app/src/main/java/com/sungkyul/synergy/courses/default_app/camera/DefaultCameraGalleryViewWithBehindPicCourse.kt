package com.sungkyul.synergy.courses.default_app.camera

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AdaptiveUtils
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduScreen

data class DefaultCameraGalleryViewWithBehindPicCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "사진 확인에 성공하였습니다!"
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 0.4f
            dialog.bottom = 0.4f
            dialog.start = 0.1f
            dialog.end = 0.1f
            dialog.visibility = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_yellow_bg
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont= R.font.pretendard_semibold
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            cover.isClickable = true
            cover.visibility=true
        })

    }
}
