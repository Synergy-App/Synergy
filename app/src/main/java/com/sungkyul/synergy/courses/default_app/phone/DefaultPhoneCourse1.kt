package com.sungkyul.synergy.courses.default_app.phone

import android.graphics.PointF
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AdaptiveUtils
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
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 0.1f
            dialog.bottom = 0.7f
            dialog.start = 0.1f
            dialog.end = 0.1f
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
            dialog.contentText = "이 부분은 키패드라고 불리며,<br>전화하고 싶은 상대의<br>전화번호를 입력하는<br>버튼입니다."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 0.1f
            dialog.bottom = 0.6f
            dialog.start = 0.05f
            dialog.end = 0.05f
            cover.boxBorderColor = R.color.black
            cover.boxLeft = AdaptiveUtils.ratio((10.0f)/412.0f)
            cover.boxRight = AdaptiveUtils.ratio((412.0f - 10.0f)/412.0f)
            cover.boxTop = AdaptiveUtils.ratio((330.0f)/773.0f)
            cover.boxBottom = AdaptiveUtils.ratio((590.0f)/773.0f)
            cover.boxVisibility = true
            cover.boxBorderVisibility = true
            dialog.visibility = true
            cover.visibility = true
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
        })

        // 전화 버튼 설명
        list.add(EduData().apply {
            dialog.top = 0.3f
            dialog.bottom = 0.4f

            cover.boxLeft = AdaptiveUtils.ratio((150.0f)/412.0f)
            cover.boxRight = AdaptiveUtils.ratio((412.0f - 150.0f)/412.0f)
            cover.boxTop = 700.0f/930.0f
            cover.boxBottom = 820.0f/930.0f
            cover.boxBorderColor = R.color.eduColor1
            dialog.contentText = "전화를 거는 버튼으로<br>전화번호를 입력하고<br>버튼을 누르면<br>상대방과 전화를<br>할 수 있어요."
        })

        // 전화 걸기 안내
        list.add(EduData().apply {
            dialog.contentText = "010-2468-3579로<br>전화를 걸어보세요."
            dialog.top = 0.4f
            dialog.bottom = 0.4f
            dialog.start = 0.1f
            dialog.end = 0.1f
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg
            cover.boxVisibility = false
            cover.boxBorderVisibility = false
            cover.boxBorderColor = R.color.eduColor1
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
                    x = 0.5f,
                    y = 0.725f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })

        // 전화번호 입력 과정
        val phoneNumber = "1024683579"
        val keyViews = hashMapOf<Char, PointF>(
            '1' to PointF(0.235f, 0.495f),
            '2' to PointF(0.45f, 0.495f),
            '3' to PointF(0.7f, 0.495f),
            '4' to PointF(0.235f, 0.565f),
            '5' to PointF(0.45f, 0.565f),
            '6' to PointF(0.7f, 0.565f),
            '7' to PointF(0.235f, 0.645f),
            '8' to PointF(0.45f, 0.645f),
            '9' to PointF(0.7f, 0.645f),
            '*' to PointF(0.235f, 0.725f),
            '0' to PointF(0.45f, 0.725f),
            '#' to PointF(0.7f, 0.725f)
        )
        for (i in phoneNumber) {
            val p = keyViews[i]!!
            list.add(EduData().apply {
                action.id = "click_key_button"
                cover.isClickable = false
                action.message = i.toString()
                hands.add(
                    EduHand(
                        id = "tap",
                        x = p.x,
                        y = p.y,
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
                    //target = activity.findViewById(R.id.call_button),
                    x = 0.45f,
                    y = 0.85f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
