package com.sungkyul.synergy.courses.default_app.phone

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

data class DefaultPhoneCallCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())
    //전화2_전화 연결
    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "전화 연결에 성공하였습니다!"
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont = R.font.pretendard_medium
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            dialog.top = 300.0f/930.0f
            dialog.bottom = 430.0f/930.0f
            dialog.start = 24.0f/412.0f
            dialog.end = 24.0f/412.0f
            dialog.background = R.drawable.edu_dialog_yellow_bg
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.top = Models.tunePos(300.0f/930.0f, 250.0f/930.0f, 300.0f/930.0f)
            dialog.bottom = Models.tunePos(380.0f/930.0f, 430.0f/930.0f, 380.0f/930.0f) /*커질수록 다이얼로그 크기가 작아짐*/
            dialog.start = 24.0f/412.0f
            dialog.end = 24.0f/412.0f

            cover.boxBorderColor = R.color.black
            cover.boxLeft = 10.0f/412.0f
            cover.boxRight = AdaptiveUtils.ratio((412.0f-10.0f)/412.0f)
            cover.boxTop = 500.0f/930.0f
            cover.boxBottom = 700.0f/930.0f /*크기가 커질 수록 박스가 커짐 */
            cover.boxVisibility = true
            cover.boxBorderVisibility = true

            dialog.visibility = true
            cover.visibility = true
            dialog.contentText = "아래 6개의 버튼을 통해<br> 다양한 기능을<br>사용할 수 있습니다."
            dialog.background = R.drawable.edu_dialog_bg
        })

        list.add(EduData().apply {
            dialog.contentGravity = Gravity.LEFT
            dialog.titleText = "녹음"
            dialog.titleFont = R.font.pretendard_semibold
            dialog.titleSize = AdaptiveUtils.dialogTitleMedium()
            dialog.top = Models.tunePos(280.0f/930.0f, 230.0f/930.0f, 280.0f/930.0f)
            dialog.bottom = Models.tunePos(380.0f/930.0f, 430.0f/930.0f, 380.0f/930.0f) /*커질수록 다이얼로그 크기가 작아짐*/
            dialog.start = 24.0f/412.0f
            dialog.end = 24.0f/412.0f

            cover.boxBorderColor = R.color.lime
            cover.boxLeft = 60.0f/412.0f
            cover.boxRight = AdaptiveUtils.ratio((412.0f-280.0f)/412.0f)
            cover.boxTop = 510.0f/930.0f
            cover.boxBottom = 590.0f/930.0f
            dialog.contentText = "현재 전화를 기록해<br>저장할 수 있습니다."
        })

        list.add(EduData().apply {
            dialog.contentGravity = Gravity.LEFT
            dialog.titleText = "영상통화"
            cover.boxLeft = 170.0f/412.0f
            cover.boxRight = AdaptiveUtils.ratio((412.0f-170.0f)/412.0f)

            dialog.contentText = "서로의 얼굴을 볼 수 있는<br>영상통화로 전환됩니다."
        })

        list.add(EduData().apply {
            dialog.contentGravity = Gravity.LEFT
            dialog.titleText = "블루투스"
            cover.boxLeft = 280.0f/412.0f
            cover.boxRight = AdaptiveUtils.ratio((412.0f-60.0f)/412.0f)

            dialog.contentText = "블루투스장치와 연결해<br>사용할 수 있습니다."
        })

        list.add(EduData().apply {
            dialog.contentGravity = Gravity.LEFT
            dialog.titleText = "스피커"
            dialog.titleFont = R.font.pretendard_semibold
            dialog.titleSize = AdaptiveUtils.dialogTitleMedium()
            dialog.start = 24.0f/412.0f
            dialog.end = 24.0f/412.0f

            cover.boxLeft = 60.0f/412.0f
            cover.boxRight = AdaptiveUtils.ratio((412.0f-280.0f)/412.0f)
            cover.boxTop = 610.0f/930.0f
            cover.boxBottom = 700.0f/930.0f
            dialog.contentText = "소리를 크게 들을 수 있습니다"
        })

        list.add(EduData().apply {
            dialog.contentGravity = Gravity.LEFT
            dialog.titleText = "내 소리 차단"

            cover.boxLeft = 170.0f/412.0f
            cover.boxRight = AdaptiveUtils.ratio((412.0f-170.0f)/412.0f)
            dialog.contentText = "소리를 크게 들을 수 있습니다"
        })

        list.add(EduData().apply {
            dialog.contentGravity = Gravity.LEFT
            dialog.titleText = "키패드"

            cover.boxLeft = 280.0f/412.0f
            cover.boxRight = AdaptiveUtils.ratio((412.0f-60.0f)/412.0f)
            dialog.contentText = "이전에 나왔던 키패드를<br>전화중에 열수 있습니다."
        })

        list.add(EduData().apply {
            dialog.contentText = "상황에 맞게 버튼을 눌러<br>기능을 사용하면 됩니다."
            dialog.titleText = ""
            dialog.contentGravity = Gravity.CENTER
            dialog.top = Models.tunePos(300.0f/930.0f, 250.0f/930.0f, 300.0f/930.0f)
            dialog.bottom = Models.tunePos(380.0f/930.0f, 430.0f/930.0f, 380.0f/930.0f)
            dialog.start = 24.0f/412.0f
            dialog.end = 24.0f/412.0f
            cover.boxBorderColor = R.color.black
            cover.boxLeft = 10.0f/412.0f
            cover.boxRight = AdaptiveUtils.ratio((412.0f-10.0f)/412.0f)
            cover.boxTop = 500.0f/930.0f
            cover.boxBottom = 700.0f/930.0f /*크기가 커질 수록 박스가 커짐 */
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            dialog.visibility = true
            cover.visibility = true
            dialog.background = R.drawable.edu_dialog_bg
        })

        list.add(EduData().apply {
            dialog.top = 450.0f/930.0f
            dialog.bottom = 200.0f/930.0f

            cover.boxLeft = 150.0f/412.0f
            cover.boxRight = AdaptiveUtils.ratio((412.0f-150.0f)/412.0f)
            cover.boxTop = 720.0f/930.0f
            cover.boxBottom = 840.0f/930.0f
            cover.boxBorderColor = R.color.lime
            dialog.contentText = "이 버튼을 누르면<br>전화가 종료됩니다."
        })

        list.add(EduData().apply {
            dialog.contentText = "전화를 끊어보세요."
            dialog.top = 300.0f/930.0f
            dialog.bottom = 400.0f/930.0f
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg
            cover.boxVisibility = false
            cover.boxBorderVisibility = false

        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id = "click_hang_up_button"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 190.0f/412.0f,
                    y = Models.tunePos(780.0f/930.0f, 800.0f/930.0f, 780.0f/930.0f),
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        list.add(EduData())
        }
    }

