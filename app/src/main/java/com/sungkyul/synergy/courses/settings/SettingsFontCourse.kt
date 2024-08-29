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

data class SettingsFontCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "글자 크기를 조절할 수 있는<br>화면입니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont = R.font.pretendard_medium
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
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
            dialog.contentText = "글자 크기를 조절해<br>원하는 크기로<br>변경할 수 있습니다."
            dialog.top = 0.5f
            dialog.bottom = 0.2f
            dialog.visibility = true
            cover.visibility = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg

            cover.boxLeft = 0.0f
            cover.boxRight = 1.0f
            cover.boxTop = 0.8f
            cover.boxBottom = 1.0f /*크기가 커질 수록 박스가 커짐 */
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
        })

        list.add(EduData().apply {
            dialog.contentText = "글자 크기를 최대로<br>키워볼까요?"
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 0.4f
            dialog.bottom = 0.4f
            dialog.visibility = true
            cover.visibility = true
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id = "change_text_size_bar"
            hands.add(
                EduHand(
                    id = "drag",
                    x = 0.15f,
                    y = 0.85f,
                    width = 50.0f,
                    height = 75.0f,
                    gesture = HandGestures.Companion::horizontalDragGesture
                )
            )
        })

        list.add(EduData().apply {
            action.id = "clear_ment"
            dialog.contentText = "글자 크기가<br>최대로 조절되었습니다!"
            dialog.top = 0.45f
            dialog.bottom = 0.35f
            dialog.visibility = true
            cover.visibility = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_yellow_bg

            cover.boxLeft = 0.0f
            cover.boxRight = 1.0f
            cover.boxTop = 0.15f
            cover.boxBottom = 0.3f
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            cover.isClickable = true

        })

        list.add(EduData().apply {
            dialog.contentText = "글자 크기가 너무 작아<br>사용하는데 어려웠다면"
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 0.4f
            dialog.bottom = 0.4f
            dialog.visibility = true
            cover.visibility = true
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
            cover.isClickable = true

            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "환경설정을 통해<br>글자 크기를<br>조절할 수 있습니다."
            dialog.top = 0.35f
            dialog.bottom = 0.35f
        })
    }
}
