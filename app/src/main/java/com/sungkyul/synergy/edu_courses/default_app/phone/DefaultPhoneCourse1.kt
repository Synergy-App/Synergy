package com.sungkyul.synergy.edu_courses.default_app.phone

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class DefaultPhoneCourse1(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "전화 화면 입니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 26.0f
            dialog.bottom = 700.0f
            dialog.start = 24.0f
            dialog.end = 24.0f
            cover.boxVisibility = true
            cover.visibility = false
            arrow.endTo = EduScreen.DIALOG
            dialog.visibility = true
            dialog.contentColor = "#FFFFFF"
            dialog.background = R.drawable.edu_dialog_black_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "해당 부분은 다른<br>사람의 전화번호를<br>입력하는 버튼이에요."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 100.0f
            dialog.bottom = 500.0f
            dialog.start = 50.0f
            dialog.end = 50.0f
            cover.boxLeft = 50.0f
            cover.boxTop = 300.0f
            cover.boxRight = width-50.0f
            cover.boxBottom = 600.0f
            cover.boxVisibility = true
            cover.boxStrokeVisibility = true
            arrow.endTo = EduScreen.DIALOG
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.duration = 750
            cover.duration = 750
            arrow.duration = 750
            dialog.top = 300.0f
            dialog.bottom = 300.0f
            cover.boxLeft = 150.0f
            cover.boxTop = 620.0f
            cover.boxRight = width-150.0f
            cover.boxBottom = 720.0f
            arrow.endTo = EduScreen.BOX
            arrow.visibility = true
            dialog.contentText = "전화를 거는 버튼입니다.<br>이 버튼을 누르면<br>상대방과 통화를<br>할 수 있어요."
        })

        list.add(EduData().apply {
            dialog.contentText = "통화를 걸어볼까요?"
            cover.boxVisibility = false
            cover.boxStrokeVisibility = false
            arrow.endTo = EduScreen.DIALOG
        })

        list.add(EduData().apply {
            dialog.contentText = "010-2468-3579로<br>전화를 걸어보세요."
            dialog.contentColor = "#FFFFFF"
            dialog.background = R.drawable.edu_dialog_green_bg
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            arrow.visibility = false
            action.id = "click_key_button"
            action.message = "0"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 190.0f,
                    y = 580.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        val phoneNumber = "1024683579"
        val keyPosition = hashMapOf(
            '1' to Pair(80.0f, 340.0f),
            '2' to Pair(190.0f, 340.0f),
            '3' to Pair(300.0f, 340.0f),
            '4' to Pair(80.0f, 420.0f),
            '5' to Pair(190.0f, 420.0f),
            '6' to Pair(300.0f, 420.0f),
            '7' to Pair(80.0f, 500.0f),
            '8' to Pair(190.0f, 500.0f),
            '9' to Pair(300.0f, 500.0f),
            '*' to Pair(80.0f, 580.0f),
            '0' to Pair(190.0f, 580.0f),
            '#' to Pair(300.0f, 580.0f)
        )
        for(i in phoneNumber) {
            list.add(EduData().apply {
                action.id = "click_key_button"
                action.message = i.toString()
                hands.add(
                    EduHand(
                        id = "tap",
                        x = keyPosition[i]!!.first,
                        y = keyPosition[i]!!.second,
                        gesture = HandGestures.Companion::tapGesture
                    )
                )
            })
        }

        list.add(EduData().apply {
            action.id = "click_call_button"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 200.0f,
                    y = 670.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
