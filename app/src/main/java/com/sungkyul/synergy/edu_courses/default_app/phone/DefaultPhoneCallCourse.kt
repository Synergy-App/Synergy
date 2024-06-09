package com.sungkyul.synergy.edu_courses.default_app.phone

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
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
            dialog.contentSize = 26.0f
            dialog.top = 300.0f
            dialog.bottom = 430.0f
            dialog.start = 24.0f
            dialog.end = 24.0f
            dialog.background = R.drawable.edu_dialog_yellow_bg
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.top = 300.0f
            dialog.bottom = 380.0f /*커질수록 다이얼로그 크기가 작아짐*/
            dialog.start = 24.0f
            dialog.end = 24.0f

            cover.boxLeft = 10.0f
            cover.boxRight = width-10.0f
            cover.boxTop = 500.0f
            cover.boxBottom = 700.0f /*크기가 커질 수록 박스가 커짐 */
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
            dialog.titleSize = 28.0f
            dialog.top = 260.0f
            dialog.bottom = 380.0f /*커질수록 다이얼로그 크기가 작아짐*/
            dialog.start = 24.0f
            dialog.end = 24.0f

            cover.boxLeft = 50.0f
            cover.boxRight = width-250.0f
            cover.boxTop = 500.0f
            cover.boxBottom = 620.0f
            dialog.contentText = "현재 전화를 기록해<br>저장할 수 있습니다."
        })

        list.add(EduData().apply {
            dialog.contentGravity = Gravity.LEFT
            dialog.titleText = "영상통화"
            cover.boxLeft = 150.0f
            cover.boxRight = width-150.0f

            dialog.contentText = "서로의 얼굴을 볼 수 있는<br>영상통화로 전환됩니다."
        })

        list.add(EduData().apply {
            dialog.contentGravity = Gravity.LEFT
            dialog.titleText = "블루투스"
            cover.boxLeft = 260.0f
            cover.boxRight = width-40.0f

            dialog.contentText = "블루투스장치와 연결해<br>사용할 수 있습니다."
        })

        list.add(EduData().apply {
            dialog.contentGravity = Gravity.LEFT
            dialog.titleText = "스피커"
            dialog.titleFont = R.font.pretendard_semibold
            dialog.titleSize = 28.0f
            dialog.top = 360.0f
            dialog.bottom = 280.0f /*커질수록 다이얼로그 크기가 작아짐*/
            dialog.start = 24.0f
            dialog.end = 24.0f

            cover.boxLeft = 50.0f
            cover.boxRight = width-250.0f
            cover.boxTop = 600.0f
            cover.boxBottom = 720.0f
            dialog.contentText = "소리를 크게 들을 수 있습니다"
        })

        list.add(EduData().apply {
            dialog.contentGravity = Gravity.LEFT
            dialog.titleText = "내 소리 차단"

            cover.boxLeft = 150.0f
            cover.boxRight = width-150.0f
            dialog.contentText = "소리를 크게 들을 수 있습니다"
        })

        list.add(EduData().apply {
            dialog.contentGravity = Gravity.LEFT
            dialog.titleText = "키패드"

            cover.boxLeft =260.0f
            cover.boxRight = width-40.0f
            dialog.contentText = "이전에 나왔던 키패드를 전화 중에 열 수 있습니다."
        })

        list.add(EduData().apply {
            dialog.contentText = "상황에 맞게 버튼을 눌러<br>기능을 사용하면 됩니다."
            dialog.titleText = ""
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 300.0f
            dialog.bottom = 380.0f /*커질수록 다이얼로그 크기가 작아짐*/
            dialog.start = 24.0f
            dialog.end = 24.0f
            cover.boxLeft = 10.0f
            cover.boxRight = width-10.0f
            cover.boxTop = 500.0f
            cover.boxBottom = 700.0f /*크기가 커질 수록 박스가 커짐 */
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            dialog.visibility = true
            cover.visibility = true
            dialog.background = R.drawable.edu_dialog_bg
        })

        list.add(EduData().apply {
            dialog.top = 450.0f
            dialog.bottom = 200.0f

            cover.boxLeft = 150.0f
            cover.boxRight = width-150.0f
            cover.boxTop = 680.0f
            cover.boxBottom = 800.0f
            dialog.contentText = "이 버튼을 누르면<br>전화가 종료됩니다."
        })

        list.add(EduData().apply {
            dialog.contentText = "전화를 끊어보세요."
            dialog.top = 300.0f
            dialog.bottom = 400.0f
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
                    //x = 190.0f,
                    //y = 760.0f,
                    x = 0.0f, y = 0.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        list.add(EduData())
        }
    }

