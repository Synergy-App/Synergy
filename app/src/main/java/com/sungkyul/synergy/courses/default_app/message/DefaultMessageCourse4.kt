package com.sungkyul.synergy.courses.default_app.message

import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AdaptiveUtils
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduScreen

class DefaultMessageCourse4 (val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    //메세지
    // 교육 코스를 만든다.
    init {

        list.add(EduData().apply {
            dialog.contentText = "이런 방법으로 편리하게<br>메세지를 보낼 수 있습니다."
            dialog.contentFont = R.font.pretendard_medium
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            dialog.contentGravity = Gravity.CENTER
            dialog.top = AdaptiveUtils.ratio(300.0f/930.0f)
            dialog.bottom = AdaptiveUtils.ratio(400.0f/930.0f)
            dialog.start = AdaptiveUtils.ratio(24.0f/412.0f)
            dialog.end = AdaptiveUtils.ratio(24.0f/412.0f)
            cover.boxVisibility = false
            cover.visibility = true
            cover.isClickable = true
            dialog.visibility = true
        })
    }
}