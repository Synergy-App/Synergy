package com.sungkyul.synergy.courses.default_app.message

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

class DefaultMessageCourse3 (val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    //메세지
    // 교육 코스를 만든다.
    init {

        list.add(EduData().apply {
            dialog.contentText = "만약 메세지를 보낼 상대방이<br>문자 내역 목록에 보인다면"
            dialog.contentFont = R.font.pretendard_medium
            dialog.contentSize = 26.0f
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 300.0f/930.0f
            dialog.bottom = 400.0f/930.0f
            dialog.start = 24.0f/412.0f
            dialog.end = 24.0f/412.0f
            cover.boxVisibility = false
            cover.visibility = true
            cover.isClickable = true
            dialog.visibility = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "이 버튼을 눌러<br>작성할 필요 없이"
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 400.0f/930.0f
            dialog.bottom = 200.0f/930.0f
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            cover.boxLeft = 0.75f
            cover.boxTop = 0.75f
            cover.boxRight = 0.95f
            cover.boxBottom = 0.85f
            cover.boxBorderColor = R.color.lime
        })

        list.add(EduData().apply {
            dialog.contentText = "내역을 클릭하여<br>쉽게 이동할 수 있습니다."
            dialog.top = 250.0f/930.0f
            dialog.bottom = 450.0f/930.0f
            cover.boxTop = 120.0f/930.0f
            cover.boxLeft = 0.0f
            cover.boxBottom = (930.0f-690.0f)/930.0f
            cover.boxRight = 1.0f
        })

        list.add(EduData().apply {
            dialog.contentText = "시너지와의 메세지 내역을<br>클릭해보세요"
            dialog.top = 300.0f/930.0f
            dialog.bottom = 400.0f/930.0f
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
        })

        list.add(EduData().apply {
            cover.visibility = false
            dialog.visibility = false
            hands.add(EduHand(
                id = "tap",
                x = 0.5f,
                y = 170.0f/930.0f,
                gesture = HandGestures.Companion::tapGesture
            ))
        })

    }
}