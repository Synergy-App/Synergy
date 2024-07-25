package com.sungkyul.synergy.courses.default_app.phone

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AdaptiveUtils
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class DefaultPhoneCourse2(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())
    //전화3_전화 목록 확인
    // 교육 코스를 만든다.
    init {

        list.add(EduData().apply {
            dialog.contentText = "전화가 종료되었습니다!"
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont = R.font.pretendard_medium
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            dialog.top = AdaptiveUtils.ratio(300.0f/930.0f)
            dialog.bottom = AdaptiveUtils.ratio(430.0f/930.0f)
            dialog.start = AdaptiveUtils.ratio(24.0f/412.0f)
            dialog.end = AdaptiveUtils.ratio(24.0f/412.0f)
            dialog.background = R.drawable.edu_dialog_yellow_bg
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.contentGravity = Gravity.CENTER
            dialog.contentText = "최근기록 버튼은<br>전화 기록을<br>확인할 수 있습니다."
            dialog.top = AdaptiveUtils.ratio(500.0f/930.0f)
            dialog.bottom = AdaptiveUtils.ratio(100.0f/930.0f)

            cover.boxLeft = AdaptiveUtils.ratio(150.0f/412.0f)
            cover.boxTop = AdaptiveUtils.ratio(800.0f/930.0f)
            cover.boxRight = AdaptiveUtils.ratio((412.0f-150.0f)/412.0f)
            cover.boxBottom = AdaptiveUtils.ratio(930.0f/930.0f)
            dialog.background = R.drawable.edu_dialog_bg
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
        })

        list.add(EduData().apply {
            dialog.contentText = "전화 기록을<br>확인해 보세요."
            dialog.contentGravity = Gravity.CENTER
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            dialog.top = AdaptiveUtils.ratio(300.0f/930.0f)
            dialog.bottom = AdaptiveUtils.ratio(430.0f/930.0f)
            dialog.start = AdaptiveUtils.ratio(24.0f/412.0f)
            dialog.end = AdaptiveUtils.ratio(24.0f/412.0f)
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg

            dialog.visibility = true
            cover.visibility = true
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
            arrow.visibility = false
            action.id = "click_recent_history_button"
            hands.add(
                EduHand(
                    id = "tap",
                    x = AdaptiveUtils.ratio(180.0f/412.0f),
                    y = AdaptiveUtils.ratio(750.0f/930.0f),
                    rotation = 180.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        list.add(EduData().apply {
            dialog.contentText = "날짜 순으로<br>최근에 전화한 목록이<br>뜹니다."
            dialog.contentColor = R.color.black
            dialog.top = AdaptiveUtils.ratio(200.0f/930.0f)
            dialog.bottom = AdaptiveUtils.ratio(500.0f/930.0f)
            dialog.start = AdaptiveUtils.ratio(24.0f/412.0f)
            dialog.end = AdaptiveUtils.ratio(24.0f/412.0f)
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
            cover.boxLeft = AdaptiveUtils.ratio(10.0f/412.0f)
            cover.boxTop = AdaptiveUtils.ratio(400.0f/930.0f)
            cover.boxRight = AdaptiveUtils.ratio((412.0f-10.0f)/412.0f)
            cover.boxBottom = AdaptiveUtils.ratio((930.0f-100.0f)/930.0f)
            dialog.background = R.drawable.edu_dialog_bg
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            cover.boxBorderColor = R.color.black
        })

        list.add(EduData().apply {
            dialog.top = AdaptiveUtils.ratio(500.0f/930.0f)
            dialog.bottom = AdaptiveUtils.ratio(100.0f/930.0f)
            cover.boxLeft = AdaptiveUtils.ratio((412.0f-120.0f)/412.0f)
            cover.boxTop = AdaptiveUtils.ratio(770.0f/930.0f)
            cover.boxRight = AdaptiveUtils.ratio((412.0f-20.0f)/412.0f)
            cover.boxBottom = AdaptiveUtils.ratio(930.0f/930.0f)
            dialog.contentText = "연락처는<br>나의 휴대폰에 저장된<br>사람들의 전화목록입니다."
            cover.boxBorderColor = R.color.lime
        })

        list.add(EduData().apply {
            dialog.contentText = "연락처를<br>확인해 보세요."
            dialog.contentGravity = Gravity.CENTER
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            dialog.top = AdaptiveUtils.ratio(300.0f/930.0f)
            dialog.bottom = AdaptiveUtils.ratio(430.0f/930.0f)
            dialog.start = AdaptiveUtils.ratio(24.0f/412.0f)
            dialog.end = AdaptiveUtils.ratio(24.0f/412.0f)
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg

            dialog.visibility = true
            cover.visibility = true
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            cover.boxVisibility = false
            arrow.visibility = false
            action.id = "click_contact_button"
            hands.add(
                EduHand(
                    id = "tap",
                    x = AdaptiveUtils.ratio(330.0f/412.0f),
                    y = AdaptiveUtils.ratio(760.0f/930.0f),
                    rotation = 180.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        list.add(EduData().apply {
            dialog.contentText = "연락처에서는 상대방의<br>전화번호를 저장할 수 있고<br>상대방에게 전화를<br>걸 수 있습니다."
            dialog.top = AdaptiveUtils.ratio(350.0f/930.0f)
            dialog.bottom = AdaptiveUtils.ratio(270.0f/930.0f)

            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "저장되어 있는<br>상대방의 연락처 목록이<br>나열됩니다."

            dialog.top = AdaptiveUtils.ratio(240.0f/930.0f)
            dialog.bottom = AdaptiveUtils.ratio(460.0f/930.0f)

            cover.boxLeft = AdaptiveUtils.ratio(10.0f/412.0f)
            cover.boxTop = AdaptiveUtils.ratio(420.0f/930.0f)
            cover.boxRight = AdaptiveUtils.ratio((412.0f-10.0f)/412.0f)
            cover.boxBottom = AdaptiveUtils.ratio((930.0f-100.0f)/930.0f)

            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            cover.boxBorderColor = R.color.black
        })

        list.add(EduData().apply {
            dialog.contentText = "'시너지'연락처를<br>눌러볼까요?"
            dialog.contentGravity = Gravity.CENTER
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            dialog.top = AdaptiveUtils.ratio(300.0f/930.0f)
            dialog.bottom = AdaptiveUtils.ratio(430.0f/930.0f)
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg

            cover.boxVisibility = false
            cover.boxBorderVisibility = false
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false

            action.id = "click_captain_contact_item"
            hands.add(
                EduHand(
                    id = "tap",
                    x = AdaptiveUtils.ratio(100.0f/412.0f),
                    y = AdaptiveUtils.ratio(550.0f/930.0f),
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        list.add(EduData().apply {
            dialog.contentText = "다음과 같이 뜨게 됩니다."
            dialog.top = AdaptiveUtils.ratio(350.0f/930.0f)
            dialog.bottom = AdaptiveUtils.ratio(450.0f/930.0f) /*커질수록 다이얼로그 크기가 작아짐*/

            cover.boxLeft = AdaptiveUtils.ratio(10.0f/412.0f)
            cover.boxTop = AdaptiveUtils.ratio(430.0f/930.0f)
            cover.boxRight = AdaptiveUtils.ratio((412.0f-10.0f)/412.0f)
            cover.boxBottom = AdaptiveUtils.ratio((930.0f-180.0f)/930.0f)

            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            cover.visibility = true
            dialog.visibility = true
            dialog.background = R.drawable.edu_dialog_bg
            dialog.contentColor = R.color.black
            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.contentText = "상대방에게 전화를<br>거는 버튼입니다."
            dialog.top = AdaptiveUtils.ratio(360.0f/930.0f)
            dialog.bottom = AdaptiveUtils.ratio(320.0f/930.0f)

            cover.boxLeft = AdaptiveUtils.ratio(30.0f/412.0f)
            cover.boxRight = AdaptiveUtils.ratio((412.0f-280.0f)/412.0f)
            cover.boxTop = AdaptiveUtils.ratio(570.0f/930.0f)
            cover.boxBottom = AdaptiveUtils.ratio(690.0f/930.0f)
            cover.boxBorderColor = R.color.lime
        })

        list.add(EduData().apply {
            dialog.contentText = "상대방에게 메세지를<br>보내는 버튼입니다."

            cover.boxLeft = AdaptiveUtils.ratio(120.0f/412.0f)
            cover.boxRight = AdaptiveUtils.ratio((412.0f-190.0f)/412.0f)


        })

        list.add(EduData().apply {
            dialog.contentText = "상대방에게 영상통화를<br>거는 버튼입니다."

            cover.boxLeft = AdaptiveUtils.ratio(200.0f/412.0f)
            cover.boxRight = AdaptiveUtils.ratio((412.0f-120.0f)/412.0f)

        })

        list.add(EduData().apply {
            dialog.contentText = "연락처를 추가하는<br>버튼입니다."
            dialog.top = AdaptiveUtils.ratio(170.0f/930.0f)
            dialog.bottom = AdaptiveUtils.ratio(510.0f/930.0f)

            cover.boxLeft = AdaptiveUtils.ratio(230.0f/412.0f)
            cover.boxTop = AdaptiveUtils.ratio(400.0f/930.0f)
            cover.boxRight = AdaptiveUtils.ratio((412.0f- 100.0f)/412.0f)
            cover.boxBottom = AdaptiveUtils.ratio(480.0f/930.0f)

        })

        list.add(EduData().apply {
            dialog.contentText = "연락처를<br>추가해 보세요."
            dialog.contentGravity = Gravity.CENTER
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            dialog.top = AdaptiveUtils.ratio(300.0f/930.0f)
            dialog.bottom = AdaptiveUtils.ratio(430.0f/930.0f)
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg

            cover.boxVisibility = false
            cover.boxBorderVisibility = false
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false

            hands.add(
                EduHand(
                    id = "tap",
                    x = AdaptiveUtils.ratio(250.0f/412.0f),
                    y = AdaptiveUtils.ratio(440.0f/930.0f),
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
