package com.sungkyul.synergy.edu_courses.settings

import android.view.Gravity
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class SettingsFontCourse(val eduScreen: EduScreen): EduCourse {
    override val eduDataList = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        eduDataList.add(EduData().apply {
            dialog.contentText = "손가락을 따라<br>움직여주세요."
            dialog.contentGravity = Gravity.CENTER
            dialog.top = 300.0f
            dialog.bottom = 300.0f
            dialog.start = 50.0f
            dialog.end = 50.0f
            dialog.visibility = true
            cover.visibility = true
        })

        eduDataList.add(EduData().apply {
            dialog.visibility = false
            cover.visibility = false
            action.id = "change_text_size_bar"
            action.message = "7"
            hands.add(
                EduHand(
                    id = "drag",
                    x = 40.0f,
                    y = 660.0f,
                    rotation = 180.0f,
                    gesture = HandGestures.Companion::textSizeDragGesture
                )
            )
        })

        eduDataList.add(EduData().apply {
            dialog.visibility = true
            cover.visibility = true
            dialog.contentText = "이것으로<br>환경 설정 교육을<br>마치겠습니다.<br>수고하셨습니다!"
        })
    }
}
