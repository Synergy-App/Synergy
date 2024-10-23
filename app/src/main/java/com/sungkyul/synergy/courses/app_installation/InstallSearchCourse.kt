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

data class InstallSearchCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "카카오톡이<br>상단이 나타납니다."
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

            cover.boxLeft = 0.0f
            cover.boxRight = 1.0f
            cover.boxTop = 0.08f
            cover.boxBottom = 0.18f
            cover.boxBorderVisibility=true
            cover.boxVisibility=true
        })

        list.add(EduData().apply {
            dialog.contentText = "내가 찾는 앱이 맞는지<br>확인하고"
        })

        list.add(EduData().apply {
            dialog.contentText = "설치할 앱이 맞다면<br>설치를 터치해주세요."
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
            action.id = "click_install_button"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 370.0f/412.0f,
                    y = Models.tunePos(100.0f/930.0f, 130.0f/930.0f, 100.0f/930.0f),
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
