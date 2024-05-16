package com.sungkyul.synergy.edu_courses.default_app.phone

import android.view.Gravity
import com.sungkyul.synergy.R
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
            dialog.contentSize = 28.0f
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
            dialog.contentGravity = Gravity.CENTER
            dialog.contentText = "최근기록 버튼은<br>전화 기록을<br>확인할 수 있습니다."
            dialog.top = 500.0f
            dialog.bottom = 100.0f

            cover.boxLeft = 150.0f
            cover.boxTop = 770.0f
            cover.boxRight = width-150.0f
            cover.boxBottom = height
            dialog.background = R.drawable.edu_dialog_bg
            cover.boxVisibility = true
            cover.boxStrokeVisibility = true
        })

        list.add(EduData().apply {
            dialog.contentText = "전화 기록을<br>확인해 보세요."
            dialog.contentGravity = Gravity.CENTER
            dialog.contentSize = 28.0f
            dialog.top = 300.0f
            dialog.bottom = 430.0f
            dialog.start = 24.0f
            dialog.end = 24.0f
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg

            dialog.visibility = true
            cover.visibility = true
            cover.boxVisibility = false
            cover.boxStrokeVisibility = false
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            cover.boxVisibility = false
            cover.boxStrokeVisibility = false
            arrow.visibility = false
            action.id = "click_recent_history_button"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 180.0f,
                    y = 750.0f,
                    rotation = 180.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        list.add(EduData().apply {
            dialog.contentText = "날짜 순으로<br>최근에 전화한 목록이<br>뜹니다."
            dialog.contentColor = R.color.black
            dialog.top = 200.0f
            dialog.bottom = 500.0f
            dialog.start = 24.0f
            dialog.end = 24.0f
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
            cover.boxLeft = 10.0f
            cover.boxTop = 430.0f
            cover.boxRight = width-10.0f
            cover.boxBottom = height-50.0f
            dialog.background = R.drawable.edu_dialog_bg
            cover.boxVisibility = true
            cover.boxStrokeVisibility = true
        })

        list.add(EduData().apply {
            dialog.top = 500.0f
            dialog.bottom = 100.0f
            cover.boxLeft = width-120.0f
            cover.boxTop = 770.0f
            cover.boxRight = width-20.0f
            cover.boxBottom = height
            dialog.contentText = "연락처는<br>나의 휴대폰에 저장된<br>사람들의 전화목록입니다."
        })

        list.add(EduData().apply {
            dialog.contentText = "연락처를<br>확인해 보세요."
            dialog.contentGravity = Gravity.CENTER
            dialog.contentSize = 28.0f
            dialog.top = 300.0f
            dialog.bottom = 430.0f
            dialog.start = 24.0f
            dialog.end = 24.0f
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg

            dialog.visibility = true
            cover.visibility = true
            cover.boxVisibility = false
            cover.boxStrokeVisibility = false
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            cover.boxVisibility = false
            cover.boxStrokeVisibility = false
            arrow.visibility = false
            action.id = "click_contact_button"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 380.0f,
                    y = 770.0f,
                    rotation = 180.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            cover.boxVisibility = false
            cover.boxStrokeVisibility = false
            arrow.visibility = false
            action.id = "click_captain_contact_item"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 0.0f,
                    y = 0.0f,
                    rotation = 180.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
