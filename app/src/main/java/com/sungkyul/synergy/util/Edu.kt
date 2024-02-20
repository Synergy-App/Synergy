package com.sungkyul.synergy.util

import android.animation.ObjectAnimator
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.sungkyul.synergy.R

// 현재 교육 단계의 정보 클래스
data class EduCurrentInfo(
    var title: String = "시너지",
    var titleGravity: Int = TextView.TEXT_ALIGNMENT_CENTER,
    var content: String = "생각보다 귀여움",
    var contentGravity: Int = TextView.TEXT_ALIGNMENT_CENTER,
    var dialogDuration: Long = 0,
    var dialogTop: Int = 100,
    var dialogBottom: Int = 100,
    var dialogStart: Int = 40,
    var dialogEnd: Int = 40,
    var coverDuration: Long = 0,
    var coverTop: Int = 20,
    var coverBottom: Int = 20,
    var coverStart: Int = 20,
    var coverEnd: Int = 20,
    var actionId: String = "synergy",
    var actionMsg: String? = null
)

interface EduData

// 교육에서 설명을 담당하는 클래스
data class EduDescription(
    val title: String? = null,
    val titleGravity: Int? = null,
    val content: String? = null,
    val contentGravity: Int? = null,
    val dialogDuration: Long? = null,
    val dialogTop: Int? = null,
    val dialogBottom: Int? = null,
    val dialogStart: Int? = null,
    val dialogEnd: Int? = null,
    val coverDuration: Long? = null,
    val coverTop: Int? = null,
    val coverBottom: Int? = null,
    val coverStart: Int? = null,
    val coverEnd: Int? = null
): EduData

/*data class EduImgDescription(
    val title: String,
    val content: String,
    val duration: Long,
    val src: String,
    val dialogTop: Int,
    val dialogBottom: Int,
    val dialogStart: Int,
    val dialogEnd: Int
): EduData*/

// 교육에서 연습을 담당하는 클래스
data class EduPractice(
    val actionId: String,
    val actionMsg: String? = null,
    val fingers: ArrayList<Int> = ArrayList()
): EduData

/*
# TODO: 설명 작성 중
# 교육 내용을 편하게 제작할 수 있도록 만든 클래스

## 사용법
교육을 진행할 액티비티가 `Name`이라 가정한다.

1. `activity_<name>.xml`의 루트 레이아웃 안에 교육 화면을 보여줄 FrameLayout을 추가한다.
```
<FrameLayout
    android:id="@+id/edu_screen"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

2. `<Name>Activity.kt` 클래스의 `onCreate()` 함수 안에 Edu 클래스를 선언하여 교육을 시작한다.
```
// 교육 과정을 만든다.
val course = ArrayList<EduData>()
course.add(...)
course.add(...)
...

// 교육 클래스를 선언한다.
val edu = Edu(this, course)
```

## 교육 과정 만들기
교육 과정은 다음과 같이 만들 수 있고, 오름차순으로 진행한다.
```
val course = ArrayList<EduData>()
```

### 설명 추가
```
course.add(EduDescription(
))
```

### 연습 추가
```
private val eduListener: EduListener = edu
eduListener.onAction("click_key_button", (view as Button).text.toString())
```
```
course.add(EduPractice(
))
```

Fragment에서 사용하는 법
```
class NameFragment(private val eduListener: EduListener) : Fragment() {
    override fun onCreateView(...): View {
        binding.contactAddition.setOnClickListener {
            eduListener.onAction("id", "msg")
        }
    }
}
```

*/
class Edu (
    private val activity: AppCompatActivity,
    private val course: ArrayList<EduData>
): EduListener {
    private var num = -1
    private val eduScreenFragment = EduScreenFragment(this)
    private val eduScreen = activity.findViewById<FrameLayout>(R.id.edu_screen)
    private val eduCurrentInfo = EduCurrentInfo()

    private val onEduScreenClickListener = { _: View -> update() }

    init {
        // 교육 화면을 맨 앞으로 오게 한다.
        eduScreen.bringToFront()

        replaceFragment(activity.supportFragmentManager, R.id.edu_screen, eduScreenFragment)

        num = -1

        // 프래그먼트 화면의 클릭 이벤트를 설정한다.
        eduScreen.setOnClickListener(onEduScreenClickListener)

        // TODO(클래스 초기 설계; 화살표는 어떻게 하지)
        val e = ArrayList<List<Pair<String, Map<String, Any>>>>()
        e.add(listOf(
            Pair("dialog", mapOf(
                "title_text" to "시너지",
                "title_gravity" to Gravity.CENTER,
                "content_text" to "여기는 교육 공간입니다.\n근사하고 멋있습니다.",
                "content_gravity" to Gravity.CENTER,
                "content_bolds" to listOf(Pair(1, 3), Pair(5, 8)),
                "duration" to 500,
                "top" to 0,
                "bottom" to 0,
                "start" to 0,
                "end" to 0,
                "visibility" to true
            )),
            Pair("image_dialog", mapOf(
                "title_text" to "시너지",
                "duration" to 500,
                "top" to 0,
                "bottom" to 0,
                "start" to 0,
                "end" to 0,
                "background" to 0,
                "visibility" to true
            )),
            Pair("top_dialog", mapOf(
                "title_text" to "시너지",
                "title_gravity" to Gravity.CENTER,
                "content_text" to "여기는 교육 공간입니다.\n근사하고 멋있습니다.",
                "content_gravity" to Gravity.CENTER,
                "content_bolds" to listOf(Pair(1, 3), Pair(5, 8)),
                "duration" to 500,
                "height" to 500,
                "visibility" to true
            )),
            Pair("bottom_dialog", mapOf(
                "title_text" to "시너지",
                "title_gravity" to Gravity.CENTER,
                "content_text" to "여기는 교육 공간입니다.\n근사하고 멋있습니다.",
                "content_gravity" to Gravity.CENTER,
                "content_bolds" to listOf(Pair(1, 3), Pair(5, 8)),
                "duration" to 500,
                "height" to 500,
                "visibility" to true
            )),
            Pair("covers", mapOf(
                "duration" to 500,
                "top" to 0,
                "bottom" to 0,
                "start" to 0,
                "end" to 0,
                "visibility" to true
            )),
            Pair("action", mapOf(
                "id" to "click",
                "message" to "hello"
            )),
            Pair("add_box", mapOf(
                "id" to "hello",
                "top" to 0,
                "bottom" to 0,
                "start" to 0,
                "end" to 0,
                "background" to R.drawable.display
            )),
            Pair("delete_box", mapOf(
                "id" to "hello"
            )),
            Pair("add_finger", mapOf(
                "id" to "hello",
                "x" to 10,
                "y" to 20,
                "anim" to androidx.appcompat.R.anim.abc_fade_out
            )),
            Pair("delete_finger", mapOf(
                "id" to "hello"
            ))
        ))
    }

    // eduScreenFragment.onCreateView가 호출될 때 반응하는 함수
    override fun onCreateViewFinished() {
        update()
    }

    override fun onAction(actionId: String, actionMsg: String?) {
        val eduData = course[num]
        if(eduData is EduPractice) {
            Log.i(actionId, actionMsg ?: "null")
            if (actionId == eduData.actionId && (eduData.actionMsg == null || actionMsg == eduData.actionMsg)) {
                update()
            }
        }
    }

    private fun update() {
        num++

        if(num == course.size) {
            num = 0
        }

        when(val data = course[num]) {
            is EduDescription -> {
                if(data.title != null) eduCurrentInfo.title = data.title
                if(data.titleGravity != null) eduCurrentInfo.titleGravity = data.titleGravity
                if(data.content != null) eduCurrentInfo.content = data.content
                if(data.contentGravity != null) eduCurrentInfo.contentGravity = data.contentGravity
                if(data.dialogDuration != null) eduCurrentInfo.dialogDuration = data.dialogDuration
                if(data.dialogTop != null) eduCurrentInfo.dialogTop = data.dialogTop
                if(data.dialogBottom != null) eduCurrentInfo.dialogBottom = data.dialogBottom
                if(data.dialogStart != null) eduCurrentInfo.dialogStart = data.dialogStart
                if(data.dialogEnd != null) eduCurrentInfo.dialogEnd = data.dialogEnd
                if(data.coverDuration != null) eduCurrentInfo.coverDuration = data.coverDuration
                if(data.coverTop != null) eduCurrentInfo.coverTop = data.coverTop
                if(data.coverBottom != null) eduCurrentInfo.coverBottom = data.coverBottom
                if(data.coverStart != null) eduCurrentInfo.coverStart = data.coverStart
                if(data.coverEnd != null) eduCurrentInfo.coverEnd = data.coverEnd

                // 시작 단계이거나 이전 단계가 EduDescription이 아닌 경우, 다이얼로그 등장 애니메이션이 나온다.
                if(num == 0 || course[num-1] !is EduDescription) {
                    eduScreenFragment.showDialog()
                    eduScreenFragment.showCovers()
                }

                // 제목이 빈칸이면, 제목 텍스트 뷰와 구분선을 안 보이게 한다.
                if(eduCurrentInfo.title == "") {
                    eduScreenFragment.hideTitle()
                } else {
                    eduScreenFragment.showTitle()
                }

                // 다이얼로그의 제목과 내용을 업데이트한다.
                eduScreenFragment.updateDialogTitleAndContent(
                    eduCurrentInfo.title,
                    eduCurrentInfo.titleGravity,
                    eduCurrentInfo.content,
                    eduCurrentInfo.contentGravity
                )
                // 다이얼로그의 위치를 업데이트한다.
                eduScreenFragment.translateDialog(
                    eduCurrentInfo.dialogDuration,
                    eduCurrentInfo.dialogTop,
                    eduCurrentInfo.dialogBottom,
                    eduCurrentInfo.dialogStart,
                    eduCurrentInfo.dialogEnd
                )
                // 커버들의 위치를 업데이트한다.
                eduScreenFragment.translateCovers(
                    eduCurrentInfo.coverDuration,
                    eduCurrentInfo.coverTop,
                    eduCurrentInfo.coverBottom,
                    eduCurrentInfo.coverStart,
                    eduCurrentInfo.coverEnd
                )

                eduScreen.isClickable = true
            }
            is EduPractice -> {
                eduCurrentInfo.actionId = data.actionId
                if(data.actionMsg != null) eduCurrentInfo.actionMsg = data.actionMsg

                // 시작 단계가 아니고 이전 단계가 EduPractice이 아닌 경우, 다이얼로그 숨김 애니메이션이 나온다.
                if(num > 0 && course[num-1] !is EduPractice) {
                    eduScreenFragment.hideDialog()
                    eduScreenFragment.hideCovers()
                }

                eduScreen.isClickable = false
            }
            else -> {

            }
        }
    }

    private fun replaceFragment(fragmentManager: FragmentManager, screenID: Int, fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(screenID, fragment)
            .commit()
    }
}
