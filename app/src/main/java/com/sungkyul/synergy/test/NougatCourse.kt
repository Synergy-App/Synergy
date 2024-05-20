package com.sungkyul.synergy.test

import android.view.Gravity
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
            //cover.backgroundColor = R.color.coral
            cover.boxVisibility = true
            //cover.boxBorderVisibility = true
            bottomDialog.visibility = true

            cover.isClickable = true

            dialog.titleText = "제목"
            dialog.contentText = "내용"
            dialog.top = 300.0f
            dialog.bottom = 300.0f
            dialog.start = 100.0f
            dialog.end = 100.0f

            cover.boxLeft = 100.0f
            cover.boxTop = 100.0f
            cover.boxRight = 200.0f
            cover.boxBottom = 200.0f
            cover.boxBorderWidth = 25.0f
        })

        list.add(EduData().apply {
            cover.boxVisibility = false
            cover.boxBorderVisibility = true


            cover.boxLeft = 100.0f
            cover.boxTop = 100.0f
            cover.boxRight = 200.0f
            cover.boxBottom = 200.0f


            bottomDialog.contentText = "하단 다이얼로그 등장!"
            bottomDialog.contentGravity = Gravity.CENTER
            bottomDialog.separatorColor = R.color.coral
            bottomDialog.height = 100.0f
        })

        list.add(EduData().apply {
            cover.boxVisibility = true
            cover.boxBorderColor = R.color.coral

            bottomDialog.titleText = "여기는 제목"
            bottomDialog.contentText = "여기는 내용"
            bottomDialog.height = 200.0f
        })

        list.add(EduData().apply {
            bottomDialog.titleText = ""
            bottomDialog.contentText = "<b>다시 사라져용</b>"
            bottomDialog.height = 0.0f
        })
    }
}
