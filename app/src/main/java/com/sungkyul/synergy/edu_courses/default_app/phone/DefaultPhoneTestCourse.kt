package com.sungkyul.synergy.edu_courses.default_app.phone

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class DefaultPhoneTestCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.titleText = ""
            dialog.contentText = "안녕하세요!"
            dialog.contentGravity = Gravity.CENTER
            dialog.duration = 0
            dialog.top = 300.0f
            dialog.bottom = 300.0f
            dialog.start = 50.0f
            dialog.end = 50.0f
            dialog.visibility = true

            cover.visibility = true
        })

        list.add(EduData().apply {
            dialog.contentText = "교육 진행을<br>테스트 해 볼 거에요!"
        })

        list.add(EduData().apply {
            dialog.titleText = "교육"
            dialog.titleGravity = Gravity.CENTER
            dialog.contentText = "<b>제목</b>과 <b>내용</b> 변경 테스트에요!"
            dialog.contentGravity = Gravity.END
        })

        list.add(EduData().apply {
            dialog.contentText = "사이즈 변경 테스트에요!"
            dialog.contentGravity = Gravity.START
            dialog.duration = 500
            dialog.top = 320.0f
            dialog.bottom = 320.0f
            dialog.start = 60.0f
            dialog.end = 60.0f
        })

        list.add(EduData().apply {
            dialog.top = 300.0f
            dialog.bottom = 300.0f
            dialog.start = 0.0f
            dialog.end = width/2
        })

        list.add(EduData().apply {
            dialog.top = 0.0f
            dialog.bottom = height/2
            dialog.start = 50.0f
            dialog.end = 50.0f
        })

        list.add(EduData().apply {
            dialog.top = 300.0f
            dialog.bottom = 300.0f
            dialog.start = 50.0f
            dialog.end = 50.0f
        })

        list.add(EduData().apply {
            dialog.contentText = "박스와 화살표 테스트에요!"

            cover.boxVisibility = true
            cover.boxLeft = 50.0f
            cover.boxTop = 50.0f
            cover.boxRight = width-50.0f
            cover.boxBottom = 100.0f

            arrow.duration = 500
            arrow.endTo = EduScreen.BOX
            arrow.visibility = true
        })

        list.add(EduData().apply {
            cover.duration = 500
            cover.boxRight = 100.0f
        })

        list.add(EduData().apply {
            cover.boxLeft = 75.0f
            cover.boxRight = 125.0f
        })

        list.add(EduData().apply {
            cover.boxLeft = 100.0f
            cover.boxRight = 150.0f
            cover.boxStrokeVisibility = true
        })

        list.add(EduData().apply {
            dialog.top = 275.0f
            dialog.bottom = 325.0f

            cover.duration = 0
            cover.boxTop = 500.0f
            cover.boxBottom = 550.0f

            arrow.duration = 1000
        })

        list.add(EduData().apply {
            cover.boxLeft = 150.0f
            cover.boxRight = 200.0f

            arrow.duration = 500
        })

        list.add(EduData().apply {
            cover.boxVisibility = false
            cover.boxStrokeVisibility = false

            arrow.endTo = EduScreen.DIALOG
        })

        list.add(EduData().apply {
            dialog.contentText = "다음에 나타날 손가락이<br>가리키는 곳을 누르세요!"
        })

        list.add(EduData().apply {
            dialog.visibility = false

            cover.visibility = false

            arrow.visibility = false

            hands.add(
                EduHand(
                    "touch",
                    R.drawable.hand,
                    110.0f,
                    290.0f,
                    50.0f,
                    75.0f,
                    -90.0f,
                    HandGestures.Companion::tapGesture
                )
            )

            action.id = "click_key_button"
            action.message = "1"
        })

        list.add(EduData().apply {
            hands.add(
                EduHand(
                    "touch",
                    R.drawable.hand,
                    200.0f,
                    340.0f,
                    50.0f,
                    75.0f,
                    0.0f,
                    HandGestures.Companion::tapGesture
                )
            )

            action.id = "click_key_button"
            action.message = "2"
        })

        list.add(EduData().apply {
            dialog.contentText = "테스트 완료입니다!"
            dialog.visibility = true

            cover.visibility = true
        })
    }
}
