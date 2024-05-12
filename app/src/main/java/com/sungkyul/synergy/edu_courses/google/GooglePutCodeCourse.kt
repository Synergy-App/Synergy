package com.sungkyul.synergy.edu_courses.google

import android.view.Gravity
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduScreen

data class GooglePutCodeCourse(val eduScreen: EduScreen): EduCourse {
    override val eduDataList = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        eduDataList.add(EduData().apply {
            dialog.contentText = "보안문자가 올 때까지<br>기다려볼까요?"
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
        })
    }
}
