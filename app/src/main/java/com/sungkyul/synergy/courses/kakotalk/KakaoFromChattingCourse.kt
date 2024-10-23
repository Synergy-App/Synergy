package com.sungkyul.synergy.courses.kakotalk

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

data class KakaoFromChattingCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "친구랑 대화한 목록을<br>확인할 수 있는<br>대화 목록 버튼입니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 0.5f
            dialog.bottom = 0.2f
            dialog.start = 0.1f
            dialog.end = 0.1f
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
            cover.boxVisibility=true
            cover.boxBorderVisibility=true
            cover.boxTop = Models.tunePos(0.85f, 0.8f, 0.85f)
            cover.boxBottom = 1.0f
            cover.boxLeft = 0.25f
            cover.boxRight = 0.4f
            dialog.contentSize=AdaptiveUtils.dialogContentMedium()
            dialog.contentFont = R.font.pretendard_semibold
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            cover.boxVisibility=false
            cover.boxBorderVisibility=false
            bottomDialog.visibility=false
            action.id="click_chatting_button"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 0.275f,
                    y = Models.tunePos(0.8f, 0.7f, 0.8f),
                    rotation = 180f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        list.add(EduData().apply {
            dialog.contentText = "친구와 대화한<br>대화창이 나열됩니다."
            dialog.top = 0.6f
            dialog.bottom = Models.tunePos(0.2f, 0.1f, 0.2f)
            dialog.start = 0.1f
            dialog.end = 0.1f
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
            cover.boxVisibility=true
            cover.boxBorderVisibility=true
            cover.boxBorderColor=R.color.black
            cover.boxTop = 0.075f
            cover.boxBottom = 0.5f
            cover.boxLeft = 0.0f
            cover.boxRight = 01.0f
            dialog.contentSize=AdaptiveUtils.dialogContentMedium()
            dialog.contentFont = R.font.pretendard_semibold
        })

        list.add(EduData().apply {
            dialog.contentText = "최근에 대화한 순으로<br>위에서부터 뜨게 되고"
        })

        list.add(EduData().apply {
            dialog.top = Models.tunePos(0.3f, 0.3f, 0.3f)
            dialog.bottom = Models.tunePos(0.5f, 0.3f, 0.5f)
            dialog.contentText = "새로 온 메시지는<br>숫자와 함께 가장<br>상단에 뜨게 됩니다."
            cover.boxBottom = Models.tunePos(0.15f, 0.2f, 0.15f)
            cover.boxBorderColor = R.color.lime
        })
    }
}
