package com.sungkyul.synergy.courses.accountedu

import android.os.Build
import android.util.Log
import android.view.Gravity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.utils.GALAXY_NOTE9
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.edu.EduCourse
import com.sungkyul.synergy.utils.edu.EduData
import com.sungkyul.synergy.utils.edu.EduScreen

data class GoogleFirstCourse(val eduScreen: EduScreen): EduCourse {
    override val list = ArrayList<EduData>()
    override val width = DisplayUtils.pxToDp(eduScreen.context, eduScreen.width.toFloat())
    override val height = DisplayUtils.pxToDp(eduScreen.context, eduScreen.height.toFloat())

    // 교육 코스를 만든다.
    init {
        Log.i("model?", Build.MODEL)
        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "계정생성 교육을<br>시작하겠습니다."
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont= R.font.pretendard_semibold
            dialog.contentSize = when(Build.MODEL) {
                GALAXY_NOTE9 -> 22.0f
                else -> 26.0f
            }
            dialog.top = 0.4f
            dialog.bottom = 0.35f
            dialog.start = 0.05f
            dialog.end = 0.05f

            bottomDialog.visibility = true

            cover.isClickable = true
        })

        list.add(EduData().apply {
            dialog.contentText = "로그인은<br> 사용하려는 앱에 <br>내가 접속하기 위한 <br>첫 단계입니다."
            dialog.top = 0.4f
            dialog.bottom = 0.3f
        })

        list.add(EduData().apply {
            dialog.contentText = "이 때 내가 누군지<br>확인하기 위해 아이디와<br>비밀번호가 필요합니다."
        })

        list.add(EduData().apply {
            dialog.contentText ="아이디는 내가<br>앱에서 사용할<br>고유한 이름이라고<br>생각할 수 있고,"
        })

        list.add(EduData().apply {
            dialog.contentText = "비밀번호는<br>" +
                    "그 아이디와 짝을 이루는<br>" +
                    "암호입니다. "
        })

        list.add(EduData().apply {
            dialog.contentText ="이러한 나의 정보들을<br>" +
                    "저장하는 곳이<br>" +
                    "'계정'입니다."
        })

        list.add(EduData().apply {
            dialog.visibility = false

            bottomDialog.sebookImageVisibility = true
            bottomDialog.height = 0.3f
            bottomDialog.titleFont= R.font.pretendard_bold
            bottomDialog.titleSize = when(Build.MODEL) {
                GALAXY_NOTE9 -> 26.0f
                else -> 30.0f
            }
            bottomDialog.contentText = "예를 들어<br>" +
                    "제 이름을 영어로 표기한<br>" +
                    "senergy와 좋아하는 숫자를<br>" +
                    "조합하여 아이디를<br>" +
                    "senergy100로<br>" +
                    "만들겠습니다."
            bottomDialog.contentColor = R.color.black
            bottomDialog.background = R.drawable.edu_bottom_dialog_bg
            bottomDialog.contentFont= R.font.pretendard_semibold
            bottomDialog.contentSize = when(Build.MODEL) {
                GALAXY_NOTE9 -> 22.0f
                else -> 26.0f
            }
        })

        list.add(EduData().apply {
            bottomDialog.contentText = "이때 아이디는 주로<br>" +
                    "영어만 사용하거나<br>" +
                    "영어 + 숫자를 조합하여<br>" +
                    "만들 수 있습니다."
        })

        list.add(EduData().apply {
            bottomDialog.contentText = "비밀번호는 제가 좋아하는<br>" +
                    "과일을 영어로 한<br>" +
                    "banana와 좋아하는 날인<br>" +
                    "크리스마스를 조합하여<br>" +
                    "banana1225로 <br>" +
                    "만들겠습니다."
        })

        list.add(EduData().apply {
            bottomDialog.contentText = "그렇다면 이제 제 아이디는<br>" +
                    "senergy100, 비밀번호는<br>" +
                    "banana1225가 됩니다."
        })

        list.add(EduData().apply {
            bottomDialog.contentText = "로그인할 때마다<br>" +
                    "아이디와 비밀번호를<br>" +
                    "입력해야 하기 때문에, <br>" +
                    "이 정보를 기억하거나<br>" +
                    "안전한 곳에 적어두는 것이<br>" +
                    "중요합니다."
        })

        list.add(EduData().apply {
            bottomDialog.contentText = "이렇게 아이디와 비밀번호를<br>" +
                    "만들어 나의 계정을<br>" +
                    "만들었다면  로그인을 통해<br>" +
                    "앱을 사용할 수 있게 됩니다."
        })

        list.add(EduData().apply {
            dialog.visibility = true
            dialog.contentText = "복잡한 내용같지만<br>" +
                    "휴대폰을 사용하기<br>" +
                    "위해서는 꼭!<br>" +
                    "습득해야하는 내용이니<br>" +
                    "포기하지 말고 교육에<br>" +
                    "집중해주세요!"
            dialog.contentColor = R.color.black
            dialog.background = R.drawable.edu_dialog_bg
            dialog.contentGravity = Gravity.CENTER
            dialog.contentFont= R.font.pretendard_semibold
            dialog.contentSize = when(Build.MODEL) {
                GALAXY_NOTE9 -> 22.0f
                else -> 26.0f
            }
            dialog.top = 0.4f
            dialog.bottom = 0.3f
            dialog.start = 0.05f
            dialog.end = 0.05f

            bottomDialog.height = 0f
            bottomDialog.sebookImageVisibility = false

            cover.isClickable = true
        })
    }
}
