package com.sungkyul.synergy.edu_courses.default_app.phone

import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class DefaultPhoneCourse1(val eduScreen: EduScreen, val activity: AppCompatActivity) : EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    init {
        // 전화 화면 설명
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

        // 키패드 설명
        list.add(EduData().apply {
            dialog.contentText = "이 부분은 키패드라고 불리며,<br>전화하고 싶은 상대의 전화번호를<br>입력하는 버튼입니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 50.0f
            dialog.bottom = 550.0f
            dialog.start = 24.0f
            dialog.end = 24.0f
            cover.boxLeft = 10.0f
            cover.boxRight = width - 10.0f
            cover.boxTop = 350.0f
            cover.boxBottom = 700.0f
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            dialog.visibility = true
            cover.visibility = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
        })

        // 전화 버튼 설명
        list.add(EduData().apply {
            dialog.top = 450.0f
            dialog.bottom = 200.0f
            cover.boxLeft = 150.0f
            cover.boxRight = width - 150.0f
            cover.boxTop = 680.0f
            cover.boxBottom = 800.0f
            dialog.contentText = "전화를 거는 버튼으로<br>전화번호를 입력하고<br>버튼을 누르면<br>상대방과 전화를<br>할 수 있어요."
        })

        // 전화 걸기 안내
        list.add(EduData().apply {
            dialog.contentText = "010-2468-3579로<br>전화를 걸어보세요."
            dialog.top = 300.0f
            dialog.bottom = 400.0f
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
        })

        // 손가락 애니메이션 설정
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
                    target = activity.findViewById(R.id.key0),
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        // 전화번호 입력 과정
        val phoneNumber = "1024683579"
        val keyViews = hashMapOf<Char, View>(
            '1' to activity.findViewById(R.id.key1),
            '2' to activity.findViewById(R.id.key2),
            '3' to activity.findViewById(R.id.key3),
            '4' to activity.findViewById(R.id.key4),
            '5' to activity.findViewById(R.id.key5),
            '6' to activity.findViewById(R.id.key6),
            '7' to activity.findViewById(R.id.key7),
            '8' to activity.findViewById(R.id.key8),
            '9' to activity.findViewById(R.id.key9),
            '*' to activity.findViewById(R.id.key_star),
            '0' to activity.findViewById(R.id.key0),
            '#' to activity.findViewById(R.id.key_sharp)
        )
        for (i in phoneNumber) {
            list.add(EduData().apply {
                action.id = "click_key_button"
                cover.isClickable = false
                action.message = i.toString()
                hands.add(
                    EduHand(
                        id = "tap",
                        target = keyViews[i],
                        gesture = HandGestures.Companion::tapGesture
                    )
                )
            })
        }

        // 전화 걸기 버튼
        list.add(EduData().apply {
            action.id = "click_call_button"
            cover.isClickable = false
            hands.add(
                EduHand(
                    id = "tap",
                    target = activity.findViewById(R.id.call_button),
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
