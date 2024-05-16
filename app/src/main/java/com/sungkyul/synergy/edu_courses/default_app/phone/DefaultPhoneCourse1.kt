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
    //전화1_전화 키패드 화면
    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.contentText = "전화 화면 입니다."
            dialog.contentFont = R.font.pretendard_medium
            dialog.contentSize = 26.0f
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 26.0f
            dialog.bottom = 700.0f
            dialog.start = 24.0f
            dialog.end = 24.0f
            cover.boxVisibility = true
            cover.visibility = false
            cover.isClickable = true
            arrow.endTo = EduScreen.DIALOG
            dialog.visibility = true
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
        })

        list.add(EduData().apply {
            dialog.contentText = "이 부분은 키패드라고 불리며,<br>전화하고 싶은 상대의 전화번호를<br>입력하는 버튼입니다. "
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 50.0f
            dialog.bottom = 550.0f /*커질수록 다이얼로그 크기가 작아짐*/
            dialog.start = 24.0f
            dialog.end = 24.0f

            cover.boxLeft = 10.0f
            cover.boxRight = width-10.0f
            cover.boxTop = 350.0f
            cover.boxBottom = 700.0f /*크기가 커질 수록 박스가 커짐 */
            cover.boxVisibility = true
            cover.boxStrokeVisibility = true

            dialog.visibility = true
            cover.visibility = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
        })

        list.add(EduData().apply {
            dialog.top = 450.0f
            dialog.bottom = 200.0f

            cover.boxLeft = 150.0f
            cover.boxRight = width-150.0f
            cover.boxTop = 680.0f
            cover.boxBottom = 800.0f
            dialog.contentText = "전화를 거는 버튼으로<br>전화번호를 입력하고<br>버튼을 누르면<br>상대방과 전화를<br>할 수 있어요."
        })

        list.add(EduData().apply {
            dialog.contentText = "010-2468-3579로<br>전화를 걸어보세요."
            dialog.top = 300.0f
            dialog.bottom = 400.0f
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg
            cover.boxVisibility = false
            cover.boxStrokeVisibility = false

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
                    y = 630.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        val phoneNumber = "1024683579"
        val keyPosition = hashMapOf(
            '1' to Pair(80.0f, 390.0f),
            '2' to Pair(190.0f, 390.0f),
            '3' to Pair(300.0f, 390.0f),
            '4' to Pair(80.0f, 480.0f),
            '5' to Pair(190.0f, 480.0f),
            '6' to Pair(300.0f, 480.0f),
            '7' to Pair(80.0f, 560.0f),
            '8' to Pair(190.0f, 560.0f),
            '9' to Pair(300.0f, 560.0f),
            '*' to Pair(80.0f, 630.0f),
            '0' to Pair(190.0f, 630.0f),
            '#' to Pair(300.0f, 630.0f)
        )
        for(i in phoneNumber) {
            list.add(EduData().apply {
                action.id = "click_key_button"
                cover.isClickable = false
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
            cover.isClickable = false
            hands.add(
                EduHand(
                    id = "tap",
                    x = 180.0f,
                    y = 750.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
