package com.sungkyul.synergy.edu_courses.kakotalk

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class KakaoProfileCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "친구의 프로필입니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 300.0f
            dialog.bottom = 300.0f
            dialog.start = 50.0f
            dialog.end = 50.0f
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.contentText = "'1:1 채팅'은 친구와<br>대화할 수 있는 기능입니다."
            dialog.top = 400.0f
            dialog.bottom = 200.0f
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            cover.boxLeft = 25.0f
            cover.boxTop = height-95.0f
            cover.boxRight = 110.0f
            cover.boxBottom = height-10.0f
            arrow.endTo = EduScreen.BOX
            arrow.visibility = true
        })

        list.add(EduData().apply {
            dialog.contentText = "'보이스톡'은 친구의<br>전화 번호가 없어도<br>전화를 할 수 있습니다."
            cover.boxLeft = 165.0f
            cover.boxRight = 250.0f
        })

        list.add(EduData().apply {
            dialog.contentText = "와이파이가 없는<br>곳에서는 데이터가<br>나갈 수 있으니<br>주의해주세요!"
        })

        list.add(EduData().apply {
            dialog.contentText = "페이스톡은 영상통화입니다."
            cover.boxLeft = width-110.0f
            cover.boxRight = width-25.0f
        })

        list.add(EduData().apply {
            dialog.contentText = "보이스톡과 같이<br>와이파이가 없으면<br>데이터가 나가니<br>주의해주세요."
        })

        list.add(EduData().apply {
            dialog.contentText = "그럼 1:1 대화를<br>시작해볼까요?"
            dialog.top = 300.0f
            dialog.bottom = 300.0f
            dialog.start = 50.0f
            dialog.end = 50.0f
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
            arrow.endTo = EduScreen.DIALOG
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            arrow.visibility = false
            action.id = "click_chat11_button"
            hands.add(
                EduHand(
                    id = "tap",
                    source = R.drawable.hand,
                    //x = 85.0f,
                    //y = 675.0f,
                    x = 0.0f, y = 0.0f,
                    width = 50.0f,
                    height = 75.0f,
                    rotation = -90.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
