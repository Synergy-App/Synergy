package com.sungkyul.synergy.test

import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduScreen

data class NougatCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        list.add(EduData().apply {
            dialog.visibility = true
            cover.visibility = true
            cover.boxVisibility = true

            cover.isClickable = true

            dialog.titleText = "제목인 것"
            dialog.contentText = "내용인 것"
            dialog.titleSize = 30.0f
            dialog.visibility = true
            dialog.top = 300.0f
            dialog.bottom = 300.0f
            dialog.start = 100.0f
            dialog.end = 100.0f
            dialog.background = R.drawable.edu_dialog_bg

            cover.boxLeft = 50.0f
            cover.boxTop = 50.0f
            cover.boxRight = 100.0f
            cover.boxBottom = 100.0f
        })

        list.add(EduData().apply {
            cover.boxVisibility = false
            cover.boxBorderVisibility = true
            cover.boxPadding = 0.0f

            cover.boxLeft = 100.0f
            cover.boxTop = 100.0f
            cover.boxRight = 200.0f
            cover.boxBottom = 200.0f
        })

        list.add(EduData().apply {
            cover.boxVisibility = true
            cover.boxBorderColor = R.color.coral
        })

        list.add(EduData().apply {
        })
    }
}
