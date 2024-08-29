package com.sungkyul.synergy.courses.default_app.message

import android.os.Build
import android.util.Log
import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.AdaptiveUtils
import com.sungkyul.synergy.utils.GALAXY_NOTE9
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduScreen

data class DefaultMessageFirstCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        Log.i("model?", Build.MODEL)
        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "메시지 교육을<br>시작하겠습니다."
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont= R.font.pretendard_semibold
            dialog.contentSize = AdaptiveUtils.dialogContentMedium()
            dialog.top = 0.4f
            dialog.bottom = 0.35f
            dialog.start = 0.05f
            dialog.end = 0.05f

            bottomDialog.visibility = true

            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.visibility = false

            bottomDialog.sebookImageVisibility = true
            bottomDialog.height = 0.3f
            bottomDialog.titleText = "메시지"
            bottomDialog.titleFont= R.font.pretendard_bold
            bottomDialog.titleSize = AdaptiveUtils.dialogTitleMedium()
            bottomDialog.contentText = "문자는 글이나 사진을<br>상대방에게 보낼 수 있습니다."
            bottomDialog.contentColor = R.color.black
            bottomDialog.background = R.drawable.edu_bottom_dialog_bg
            bottomDialog.contentFont= R.font.pretendard_semibold
            bottomDialog.contentSize = AdaptiveUtils.dialogContentMedium()
        })

        list.add(EduData().apply {
            bottomDialog.contentText = "글로 상대방에게 하고<br>싶은 말이 있을 때나<br>통화를 할 수 없는 상황에서<br>의사소통을 할 때 유용하게<br>사용됩니다."
        })
    }
}
