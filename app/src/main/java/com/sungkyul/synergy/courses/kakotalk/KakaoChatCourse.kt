package com.sungkyul.synergy.courses.kakotalk

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AdaptiveUtils
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class KakaoChatCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "이 화면에서<br>상대와 다양한 의사소통을<br>할 수 있습니다."
            dialog.background = R.drawable.edu_dialog_black_bg
            dialog.contentColor = R.color.white
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont = R.font.pretendard_semibold
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            dialog.top = AdaptiveUtils.ratio(0.1f)
            dialog.bottom = AdaptiveUtils.ratio(0.7f)
            dialog.start = AdaptiveUtils.ratio(0.1f)
            dialog.end = AdaptiveUtils.ratio(0.1f)
            dialog.visibility = true
            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.contentText = "상대에게<br>인사 메시지를<br>보내보세요."
            dialog.background = R.drawable.edu_dialog_green_bg
            dialog.top = AdaptiveUtils.ratio(0.4f)
            dialog.bottom = AdaptiveUtils.ratio(0.4f)
            dialog.start = AdaptiveUtils.ratio(0.1f)
            dialog.end = AdaptiveUtils.ratio(0.1f)
            cover.visibility = true
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id = "click_message_edit_text"
            hands.add(
                EduHand(
                    id = "tap",
                    source = R.drawable.hand,
                    x = AdaptiveUtils.ratio(0.5f),
                    y = AdaptiveUtils.ratio(0.8f),
                    rotation = 180.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        list.add(EduData().apply {
            action.id = "click_sharp_button"
        })

        list.add(EduData().apply {
            dialog.contentText = "내가 보낸 메시지는<br>우측에 뜨게 되고"
            dialog.background = R.drawable.edu_dialog_bg
            dialog.contentColor = R.color.black
            dialog.visibility=true
            cover.visibility = true
            cover.boxBorderVisibility=true
            cover.boxVisibility=true
            cover.boxLeft=AdaptiveUtils.ratio(0.5f)
            cover.boxRight=AdaptiveUtils.ratio(1.0f)
            cover.boxTop=AdaptiveUtils.ratio(0.1f)
            cover.boxBottom=AdaptiveUtils.ratio(0.2f)
            cover.isClickable=true
        })

        list.add(EduData().apply {
            dialog.contentText = "상대가 보낸 메시지는<br>좌측에 뜨게 됩니다."
            dialog.background = R.drawable.edu_dialog_bg
            dialog.contentColor = R.color.black
            dialog.visibility=true
            cover.visibility = true
            cover.boxBorderVisibility=true
            cover.boxVisibility=true
            cover.boxLeft=AdaptiveUtils.ratio(0.0f)
            cover.boxRight=AdaptiveUtils.ratio(0.5f)
            cover.boxTop=AdaptiveUtils.ratio(0.1f)
            cover.boxBottom=AdaptiveUtils.ratio(0.2f)
        })
    }
}
