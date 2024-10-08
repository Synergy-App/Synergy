package com.sungkyul.synergy.courses.settings

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AdaptiveUtils
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class SettingsCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
            list.add(EduData().apply {
                dialog.contentText = "환경설정의<br>메인 화면입니다."
                dialog.contentFont = R.font.pretendard_medium
                dialog.contentSize = AdaptiveUtils.dialogContentMedium()
                dialog.contentGravity = Gravity.CENTER
                dialog.top = 0.1f
                dialog.bottom = 0.7f
                dialog.start = 0.1f
                dialog.end = 0.1f
                dialog.visibility = true
                cover.visibility = false
                cover.isClickable = true
                dialog.contentColor = R.color.white
                dialog.background = R.drawable.edu_dialog_black_bg

            })

        list.add(EduData().apply {
            dialog.contentText = "휴대폰의 상세 설정 목록들이<br>나열되어 있는데요."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 0.35f
            dialog.bottom = 0.35f
            dialog.visibility = true
            cover.visibility = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "글자 크기를 변경하기 위해서는<br>디스플레이를 이용하면 됩니다."
            dialog.top = 0.35f
            dialog.bottom = 0.35f
        })

        list.add(EduData().apply {
            dialog.contentText = "디스플레이를<br>클릭해 주세요."
            dialog.contentGravity = Gravity.CENTER
            dialog.visibility = true
            cover.visibility = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id = "scroll_to_bottom"
            hands.add(
                EduHand(
                    id = "drag",
                    x = 0.5f,
                    y = 0.5f,
                    gesture = HandGestures.Companion::verticalScrollGesture
                )
            )
        })

        list.add(EduData().apply {

            cover.boxLeft = 0.0f
            cover.boxRight = 1.0f
            cover.boxTop = 0.375f
            cover.boxBottom = 0.475f
            cover.boxVisibility = true
            cover.boxBorderVisibility = true

            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            action.id = "tap_display_item"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 0.5f,
                    y = 0.4f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
