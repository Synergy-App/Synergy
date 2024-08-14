package com.sungkyul.synergy.courses.naver

import android.os.Build
import android.util.Log
import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AdaptiveUtils
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class NaverFromNaverSearchInfoCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        Log.i("model?", Build.MODEL)
        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "이 부분은 날씨와 미세먼지에<br>대한 정보를 확인할 수 <br>있습니다."
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont = R.font.pretendard_semibold
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            dialog.top = AdaptiveUtils.ratio(0.3f)
            dialog.bottom = AdaptiveUtils.ratio(0.5f)
            dialog.start = AdaptiveUtils.ratio(0.05f)
            dialog.end = AdaptiveUtils.ratio(0.05f)

            cover.visibility=true
            cover.isClickable = true
            cover.boxVisibility=true
            cover.boxBorderVisibility=true
            cover.boxBorderColor=R.color.lime
            cover.boxTop=AdaptiveUtils.ratio(0.52f)
            cover.boxBottom=AdaptiveUtils.ratio(0.6f)
            cover.boxLeft=AdaptiveUtils.ratio(0.0f)
            cover.boxRight=AdaptiveUtils.ratio(1.0f)
        })

        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "현재 위치를 기반으로<br>날씨를 확인할 수 있고"

            cover.boxLeft=AdaptiveUtils.ratio(0.0f)
            cover.boxRight=AdaptiveUtils.ratio(0.5f)
        })

        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "미세먼지 정보를 확인할<br>수도 있습니다."

            cover.boxLeft=AdaptiveUtils.ratio(0.5f)
            cover.boxRight=AdaptiveUtils.ratio(1.0f)
        })
    }
}
