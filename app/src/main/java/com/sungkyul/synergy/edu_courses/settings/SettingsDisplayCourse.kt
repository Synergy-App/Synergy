package com.sungkyul.synergy.edu_courses.settings

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class SettingsDisplayCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "아래 \"글자 크기와 스타일\"을 눌러보세요."
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont = R.font.pretendard_medium
            dialog.contentSize = 26.0f
            dialog.top = 40.0f
            dialog.bottom = 450.0f
            dialog.start = 24.0f
            dialog.end = 24.0f
            dialog.visibility = true
            cover.visibility = true
            cover.isClickable = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg
        })



        /*list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id = "change_light_bar"
            action.message = "100"
            hands.add(
                EduHand(
                    id = "drag",
                    x = 220.0f,
                    y = 510.0f,
                    gesture = HandGestures.Companion::displayLightDragGesture
                )
            )
        }) */


        list.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            cover.isClickable = false
            action.id = "scroll_to_bottom"
            hands.add(
                EduHand(
                    id = "drag",
                    source = R.drawable.hand,
                    //x = 200.0f,
                    //y = 650.0f,
                    x = 0.0f, y = 0.0f,
                    width = 50.0f,
                    height = 75.0f,
                    gesture = HandGestures.Companion::verticalScrollGesture
                )
            )
        })

        list.add(EduData().apply {

            cover.boxLeft = 10.0f
            cover.boxRight = width-10.0f
            cover.boxTop = 280.0f
            cover.boxBottom = 340.0f /*크기가 커질 수록 박스가 커짐 */
            cover.boxVisibility = true
            cover.boxBorderVisibility = true

            action.id = "tap_font_item"
            hands.add(
                EduHand(
                    id = "tap",
                    //x = 200.0f,
                    //y = 300.0f,
                    x = 0.0f, y = 0.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
