package com.sungkyul.synergy.edu_courses.kakotalk

import android.view.Gravity
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class KakaoCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "<span style=\"color:#E6C60D\"><b>카카오톡</b></span>의<br>메인 화면입니다."
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
            dialog.top = 200.0f
            dialog.bottom = 400.0f
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            cover.boxLeft = 225.0f
            cover.boxTop = 15.0f
            cover.boxRight = 275.0f
            cover.boxBottom = 65.0f
            arrow.endTo = EduScreen.BOX
            arrow.visibility = true
            dialog.contentText = "이 버튼은 빠르게<br>친구를 찾을 수 있는<br>버튼이에요."
        })

        list.add(EduData().apply {
            dialog.contentText = "친구를 빠르게 찾고<br>싶을 때 이 버튼을 눌러<br>찾고 싶은 친구의 이름을<br>입력하면 돼요."
        })

        list.add(EduData().apply {
            cover.boxLeft = 10.0f
            cover.boxTop = 75.0f
            cover.boxRight = width-10.0f
            cover.boxBottom = 135.0f
            dialog.contentText = "내 프로필입니다."
        })

        list.add(EduData().apply {
            dialog.titleText = "프로필이란?"
            dialog.contentText = "카카오톡을 사용할 때<br>다른 사람들에게<br>여러분을 알리는데<br>도움을 줄 수 있는<br>간단한 자기소개입니다."
            dialog.bottom = 300.0f
        })

        list.add(EduData().apply {
            dialog.titleText = ""
            dialog.contentText = "친구들의 목록을 볼 수 있어요."
            dialog.top = 25.0f
            dialog.bottom = 625.0f
            cover.boxTop = 175.0f
            cover.boxBottom = 700.0f
            arrow.endTo = EduScreen.DIALOG
        })

        list.add(EduData().apply {
            dialog.contentText = "친구를 찾을 수 있는<br>버튼입니다."
            dialog.top = 450.0f
            dialog.bottom = 150.0f
            dialog.start = 25.0f
            dialog.end = 75.0f
            cover.boxLeft = 25.0f
            cover.boxTop = height-70.0f
            cover.boxRight = 80.0f
            cover.boxBottom = height-0.0f
            arrow.endTo = EduScreen.BOX
        })

        list.add(EduData().apply {
            dialog.contentText = "방금까지 살펴본<br>카카오톡의 화면이<br>이 버튼을 누르면<br>보이는 화면입니다."
            dialog.top = 425.0f
        })

        list.add(EduData().apply {
            dialog.contentText = "카카오톡으로<br>친구와 연락을<br>주고 받아 볼까요?"
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
            hands.add(
                EduHand(
                    id = "tap",
                    x = 100.0f,
                    y = 200.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
