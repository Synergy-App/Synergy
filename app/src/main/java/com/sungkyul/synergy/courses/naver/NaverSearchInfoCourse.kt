package com.sungkyul.synergy.courses.naver

import android.os.Build
import android.util.Log
import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AdaptiveUtils
import com.sungkyul.synergy.utils.GALAXY_NOTE9
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.HandGestures
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduHand
import com.sungkyul.synergy.utils.edu.EduScreen

data class NaverSearchInfoCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        Log.i("model?", Build.MODEL)
        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "\"된장찌개 만드는 법\"에<br>관한 여러 정보가<br>뜨게 됩니다."
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_black_bg
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont= R.font.pretendard_semibold
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            dialog.top = 0.7f
            dialog.bottom = 0.1f
            dialog.start = 0.05f
            dialog.end = 0.05f

            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "원하는 정보를 검색을<br>통해 찾아볼 수 있습니다."
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
            dialog.top = 0.4f
            dialog.bottom = 0.4f

            cover.visibility = true
        })
        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "다시 네이버 홈으로<br>돌아가보겠습니다."
            dialog.contentColor = R.color.white
            dialog.background = R.drawable.edu_dialog_green_bg
        })

        list.add(EduData().apply {
            cover.visibility = false
            cover.isClickable = false
            dialog.visibility = false
            action.id = "click_naver_button"
            hands.add(
                EduHand(
                    id = "tap",
                    x = 0.1f,
                    y = 0.0f,
                    rotation = 270.0f,
                    gesture = HandGestures.Companion::tapGesture
                )
            )
        })
    }
}
