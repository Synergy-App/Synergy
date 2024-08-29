package com.sungkyul.synergy.courses.default_app.camera

import android.os.Build
import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AdaptiveUtils
import com.sungkyul.synergy.utils.GALAXY_NOTE9
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class DefaultCameraGalleryViewCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "방금 찍은 사진입니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 0.2f
            dialog.bottom = 0.6f
            dialog.start = 0.1f
            dialog.end = 0.1f
            dialog.visibility = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont= R.font.pretendard_semibold
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.contentText = "앨범으로 돌아가는 버튼입니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.background = R.drawable.edu_dialog_bg
            dialog.contentColor = R.color.black
            cover.boxLeft = 0.0f
            cover.boxTop = 0.0f
            cover.boxRight = 0.2f
            cover.boxBottom = 0.1f
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
            cover.boxBorderVisibility = true
            cover.boxVisibility = true
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id = "click_back_button"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 0.1f,
                    y = 0.05f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
