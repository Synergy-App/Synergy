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

data class KakaoProfileCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "친구의 프로필 화면입니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont = R.font.pretendard_semibold
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            dialog.background = R.drawable.edu_dialog_black_bg
            dialog.contentColor=R.color.white
            dialog.top = 0.2f
            dialog.bottom = 0.6f
            dialog.start = 0.1f
            dialog.end = 0.1f
            dialog.visibility = true
            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.titleText="1:1 채팅"
            dialog.contentText = "친구와 대화할 수 있는<br>기능입니다."
            dialog.background=R.drawable.edu_dialog_bg
            dialog.contentColor=R.color.black
            dialog.contentGravity=Gravity.LEFT
            dialog.top = 0.6f
            dialog.bottom = 0.15f
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            cover.boxLeft = 0.0f
            cover.boxTop = 0.825f
            cover.boxRight = 0.3f
            cover.boxBottom = 1.0f
            cover.visibility = true
        })
        list.add(EduData().apply {
            dialog.titleText="보이스톡"
            dialog.contentText = "친구와 음성 통화를<br>할 수 있는 기능입니다."
            cover.boxLeft = 0.4f
            cover.boxRight = 0.6f
        })
        list.add(EduData().apply {
            dialog.titleText="페이스톡"
            dialog.contentText = "친구와 얼굴을 보면서 하는<br>영상 통화의 기능입니다."
            cover.boxLeft = 0.7f
            cover.boxRight = 1.0f
        })

        list.add(EduData().apply {
            dialog.titleText=""
            dialog.contentText = "친구와 1:1대화를<br>시작해보세요."
            dialog.background=R.drawable.edu_dialog_green_bg
            dialog.contentColor=R.color.white
            dialog.top=0.4f
            dialog.bottom=0.4f
            dialog.contentGravity=Gravity.CENTER
            cover.boxVisibility=false
            cover.boxBorderVisibility=false
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
                    x = 0.1f,
                    y = 0.8f,
                    rotation = -180.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })


        /*
        list.add(EduData().apply {
            dialog.contentText = "'보이스톡'은 친구의<br>전화 번호가 없어도<br>전화를 할 수 있습니다."
            cover.boxLeft = 165.0f/412.0f
            cover.boxRight = 250.0f/412.0f
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
            dialog.top = 300.0f/930.0f
            dialog.bottom = 300.0f/930.0f
            dialog.start = 50.0f/412.0f
            dialog.end = 50.0f/412.0f
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
                    x = 85.0f/412.0f,
                    y = 675.0f/930.0f,

                    width = 50.0f,
                    height = 75.0f,
                    rotation = -90.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })*/
    }
}
