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
            dialog.titleText = "제목인 것"
            dialog.contentText = "내용인 것"
            dialog.titleFont = R.font.mini_handwriting
            dialog.contentFont = R.font.mini_handwriting
            dialog.visibility = true
            dialog.top = 300.0f
            dialog.bottom = 300.0f
            dialog.start = 100.0f
            dialog.end = 100.0f
            dialog.background = R.drawable.edu_dialog_bg
        })
    }
}
