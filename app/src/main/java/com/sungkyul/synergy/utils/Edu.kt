package com.sungkyul.synergy.utils

import android.util.Log
import android.view.Gravity
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.sungkyul.synergy.R

/*data class EduDialog(
    var titleText: String? = null,
    var titleGravity: Int? = null,
    var contentText: String? = null,
    var contentGravity: Int? = null,
    var contentBolds: List<Pair<Int, Int>>? = null,
    var duration: Int? = null,
    var top: Int? = null,
    var bottom: Int? = null,
    var start: Int? = null,
    var end: Int? = null,
    var visibility: Boolean? = null
)
data class EduImageDialog(
    var titleText: String? = null,
    var image: Int? = null,
    var duration: Int? = null,
    var top: Int? = null,
    var bottom: Int? = null,
    var start: Int? = null,
    var end: Int? = null,
    var visibility: Boolean? = null
)
data class EduVerticalDialog(
    var titleText: String? = null,
    var titleGravity: Int? = null,
    var contentText: String? = null,
    var contentGravity: Int? = null,
    var contentBolds: List<Pair<Int, Int>>? = null,
    var duration: Int? = null,
    var visibility: Boolean? = null
)
data class EduCover(
    var duration: Int? = null,
    var top: Int? = null,
    var bottom: Int? = null,
    var start: Int? = null,
    var end: Int? = null,
    var hasStroke: Boolean? = null,
    var visibility: Boolean? = null
)
data class EduArrow(
    var duration: Int? = null,
    var visibility: Boolean? = null
)
data class EduAction(
    var id: String? = null,
    var message: String? = null
)
data class EduFinger(
    var image: Int? = null,
    var x: Int? = null,
    var y: Int? = null,
    var anim: Int? = null
)
data class EduData(
    val dialog: EduDialog = EduDialog(),
    val imageDialog: EduImageDialog = EduImageDialog(),
    val topDialog: EduVerticalDialog = EduVerticalDialog(),
    val bottomDialog: EduVerticalDialog = EduVerticalDialog(),
    val covers: EduCover = EduCover(),
    val arrow: EduArrow = EduArrow(),
    val action: EduAction = EduAction(),
    val fingers: ArrayList<EduFinger> = ArrayList()
)*/

/*
## 소개
교육 과정을 편하게 제작할 수 있도록 만든 클래스이다.

## 사용법
0. 교육을 진행할 액티비티는 `NameActivity.kt`, 레이아웃은 `activity_name.xml`이라 가정한다.

1. `activity_name.xml` 안에 교육 화면을 보여줄 FrameLayout을 추가한다. 이 레이아웃의 id는 `edu_screen`이다.
```
<FrameLayout
    android:id="@+id/edu_screen"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

2. `NameActivity` 클래스의 `onCreate()` 함수 안에 Edu 클래스를 선언한다. `size`는 교육 슬라이드의 수이다.
```
val edu = Edu(this, size)
```

3. 각 슬라이드에 들어갈 교육을 만든다.
```
edu.course = arrayListOf(
    EduData(
        dialogTitleText = "안녕",
        dialogTitleGravity = Gravity.CENTER,
        fingers = listOf(
            EduFinger(R.drawable.finger, 0, 0, R.anim.click)
        )
    ),
    EduData(

    ),
)

edu[0].dialog.titleText = "hello"
edu[0].dialog.top = 10
...
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
    size: Int
): EduListener {
    private var num = -1
    private val course = Array(size) {EduData()}
    private val eduScreenFragment = EduScreenFragment()
    private val eduScreen = activity.findViewById<FrameLayout>(R.id.edu_screen)

    init {
        eduScreen.bringToFront()
        replaceFragment(activity.supportFragmentManager, R.id.edu_screen, eduScreenFragment)
        num = -1
        
        eduScreen.setOnClickListener {
            update()
        }
    }

    // eduScreenFragment.onCreateView가 호출되면, 이 함수가 호출된다.
    override fun onCreateViewFinished() {
        update()
    }

    // 액션이 발생하면, 이 함수가 호출된다.
    override fun onAction(id: String, message: String?) {
        val eduData = course[num]
        Log.i(id, message ?: "null")
        if(id == eduData.action.id && (eduData.action.message == null || message == eduData.action.message)) {
            update()
        }
    }

    private fun update() {
        num++

        // TEST
        if(num == course.size) {
            num = 0
        }

        val data = course[num]

        /*if(data.dialog.titleText != null) {
            //eduScreenFragment.setDialogTitleText(data.dialog.titleText!!)
        }
        if(data.dialog.titleGravity != null) {
            eduScreenFragment.setDialogTitleGravity(data.dialog.titleGravity!!)
        }
        if(data.dialog.contentText != null) {
            eduScreenFragment.setDialogContentText(data.dialog.titleText!!)
        }
        if(data.dialog.contentGravity != null) {
            eduScreenFragment.setDialogContentGravity(data.dialog.contentGravity!!)
        }
        if(data.dialog.contentBolds != null) {
            eduScreenFragment.setDialogContentBolds(data.dialog.contentBolds!!)
        }*/

        /*
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
        }*/
    }

    private fun replaceFragment(fragmentManager: FragmentManager, screenID: Int, fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(screenID, fragment)
            .commit()
    }
}
