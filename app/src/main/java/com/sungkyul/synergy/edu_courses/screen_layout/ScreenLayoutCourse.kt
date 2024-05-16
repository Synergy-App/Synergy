package com.sungkyul.synergy.edu_courses.screen_layout

import android.view.Gravity
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduScreen

data class ScreenLayoutCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "이 부분은<br><b>홈 화면</b>입니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 300.0f
            dialog.bottom = 300.0f
            dialog.start = 50.0f
            dialog.end = 50.0f
            dialog.visibility = true

            cover.boxLeft = 20.0f
            cover.boxTop = 50.0f
            cover.boxRight = width-20.0f
            cover.boxBottom = height-50.0f
            cover.visibility = true
            cover.isClickable = true
            cover.boxVisibility = true
            cover.boxStrokeVisibility = true
        })

        list.add(EduData().apply {
            dialog.titleText = "홈 화면"
            dialog.contentText = "주로 <b>배경화면</b>이라고<br>부릅니다."
            dialog.contentGravity = Gravity.START
            dialog.top = 275.0f
            dialog.bottom = 275.0f
        })

        list.add(EduData().apply {
            dialog.contentText = "자주 사용하는 앱이<br>화면에 배치되어<br>있습니다."
            dialog.top = 250.0f
            dialog.bottom = 250.0f
        })

        list.add(EduData().apply {
            dialog.titleText = ""
            dialog.contentText = "이 부분은<br><b>상단바(상태 표시줄)</b><br>입니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 100.0f
            dialog.bottom = 490.0f

            cover.boxLeft = 0.0f
            cover.boxTop = 5.0f
            cover.boxRight = width
            cover.boxBottom = 55.0f

            arrow.endTo = EduScreen.BOX
            arrow.visibility = true
        })

        list.add(EduData().apply {
            dialog.contentText = "주로 스마트폰의<br>상태를 보여줍니다."
        })

        list.add(EduData().apply {
            dialog.contentText = "좌측 상단에는<br><b>현재 시간</b>이<br>나타납니다."

            cover.boxRight = 100.0f
        })

        list.add(EduData().apply {
            dialog.contentText = "우측 상단은<br><b>스마트폰의 현재<br>상태</b>를 나타냅니다."

            cover.boxLeft = width-150.0f
            cover.boxRight = width
        })

        list.add(EduData().apply {
            dialog.contentText = "이 부분은<br><b>하단바(내비게이션바)</b><br>입니다."
            dialog.top = 500.0f
            dialog.bottom = 100.0f

            cover.boxLeft = 0.0f
            cover.boxTop = height-55.0f
            cover.boxRight = width
            cover.boxBottom = height-5.0f

        })

        list.add(EduData().apply {
            dialog.contentText = "이 버튼은<br><b>최근 실행 앱</b>을<br>볼 수 있습니다."

            cover.boxLeft = 50.0f
            cover.boxRight = 110.0f
        })

        list.add(EduData().apply {
            dialog.contentText = "이 버튼은<br><b>홈 화면</b>으로<br>돌아갈 수 있습니다."

            cover.boxLeft = 175.0f
            cover.boxRight = 235.0f
        })

        list.add(EduData().apply {
            dialog.contentText = "이 버튼은<br><b>뒤로(이전 화면)</b><br>돌아갈 수 있습니다."

            cover.boxLeft = width-110.0f
            cover.boxRight = width-50.0f
        })

        list.add(EduData().apply {
            dialog.contentText = "<b>상단바(상태 표시줄)</b>를<br>한 번 내려볼까요?"
            dialog.top = 300.0f
            dialog.bottom = 300.0f

            cover.boxVisibility = false
            cover.boxStrokeVisibility = false

            arrow.endTo = EduScreen.DIALOG
        })

        // TODO(상단바를 내릴 수 있는 기능 추가하기!)
    }
}
