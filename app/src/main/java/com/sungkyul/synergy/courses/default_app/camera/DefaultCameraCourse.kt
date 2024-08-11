package com.sungkyul.synergy.courses.default_app.camera

import android.os.Build
import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.GALAXY_NOTE9
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class DefaultCameraCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "카메라 화면입니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 0.1f
            dialog.bottom = 0.7f
            dialog.start = 0.1f
            dialog.end = 0.1f
            dialog.visibility = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont= R.font.pretendard_semibold
            dialog.contentSize = when(Build.MODEL) {
                GALAXY_NOTE9 -> 22.0f
                else -> 26.0f
            }
            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.contentText = "위 메뉴는 카메라의<br>플래시, 타이머 등을<br>설정할 수 있어요."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 200.0f
            dialog.bottom = 400.0f
            dialog.start = 50.0f
            dialog.end = 50.0f
            cover.boxLeft = 20.0f
            cover.boxTop = 5.0f
            cover.boxRight = width-20.0f
            cover.boxBottom = 45.0f
            arrow.endTo = EduScreen.BOX
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
            cover.boxBorderVisibility = true
            cover.boxVisibility = true
            arrow.visibility = true
        })

        list.add(EduData().apply {
            dialog.contentText = "아래 버튼은 사진,<br>동영상 등 자신이<br>원하는 촬영을<br>선택할 수 있어요."
            dialog.top = 300.0f
            dialog.bottom = 300.0f
            cover.boxLeft = 30.0f
            cover.boxTop = 620.0f
            cover.boxRight = width-30.0f
            cover.boxBottom = 650.0f
        })

        list.add(EduData().apply {
            dialog.contentText = "촬영할 수 있는<br>버튼이에요.<br>버튼을 클릭하면<br>사진 저장이 돼요."
            dialog.top = 400.0f
            dialog.bottom = 200.0f
            cover.boxLeft = 160.0f
            cover.boxTop = height-105.0f
            cover.boxRight = width-160.0f
            cover.boxBottom = height-15.0f
        })

        list.add(EduData().apply {
            dialog.contentText = "찍은 사진을<br>확인할 수 있는<br>앨범이에요."
            cover.boxLeft = 55.0f
            cover.boxTop = height-105.0f
            cover.boxRight = 135.0f
            cover.boxBottom = height-15.0f
        })

        list.add(EduData().apply {
            dialog.contentText = "사진을 찍으면<br>이 부분에 나타나요."
        })

        list.add(EduData().apply {
            dialog.contentText = "화면을 전환하는<br>부분이에요."
            cover.boxLeft = width-135.0f
            cover.boxTop = height-105.0f
            cover.boxRight = width-55.0f
            cover.boxBottom = height-15.0f
        })

        list.add(EduData().apply {
            dialog.contentText = "버튼을 클릭하면<br>나를 찍을 수도 있고,<br>상대방을 찍을 수도<br>있어요."
            dialog.top = 350.0f
        })

        list.add(EduData().apply {
            dialog.contentText = "사진을 촬영해볼까요?"
            dialog.top = 300.0f
            dialog.bottom = 300.0f
            dialog.start = 50.0f
            dialog.end = 50.0f
            cover.boxBorderVisibility = false
            cover.boxVisibility = false
            arrow.endTo = EduScreen.DIALOG
        })

        list.add(EduData().apply {
            dialog.contentText = "손가락을 따라<br>클릭해주세요."
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            arrow.visibility = false
            action.id = "click_shooting_button"
            hands.add(
                EduHand(
                    id = "tap",
                    //x = 220.0f,
                    //y = height-100.0f,
                    x = 0.0f, y = 0.0f,
                    rotation = -90.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        list.add(EduData().apply {
            dialog.contentText = "방금 찍은 사진은<br>여기에서 확인할 수<br>있어요."
            dialog.top = 400.0f
            dialog.bottom = 200.0f
            cover.boxLeft = 55.0f
            cover.boxTop = height-105.0f
            cover.boxRight = 135.0f
            cover.boxBottom = height-15.0f
            arrow.endTo = EduScreen.BOX
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            arrow.visibility = true
        })

        list.add(EduData().apply {
            dialog.contentText = "클릭해주세요."
            dialog.top = 450.0f
        })

        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
            arrow.visibility = false
            action.id = "click_gallery_view_button"
            hands.add(
                EduHand(
                    id = "tap",
                    //x = 120.0f,
                    //y = height-100.0f,
                    x = 0.0f, y = 0.0f,
                    rotation = -90.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
