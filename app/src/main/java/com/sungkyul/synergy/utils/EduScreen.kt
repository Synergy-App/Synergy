package com.sungkyul.synergy.utils

import android.animation.AnimatorSet
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R

data class EduDialog(
    var titleText: String? = null,
    var titleGravity: Int? = null,
    var contentText: String? = null,
    var contentGravity: Int? = null,
    var contentBolds: List<Pair<Int, Int>>? = null,
    var duration: Long? = null,
    var top: Float? = null,
    var bottom: Float? = null,
    var start: Float? = null,
    var end: Float? = null,
    var visibility: Boolean? = null
)
data class EduImageDialog(
    var titleText: String? = null,
    var image: Int? = null,
    var duration: Long? = null,
    var top: Float? = null,
    var bottom: Float? = null,
    var start: Float? = null,
    var end: Float? = null,
    var visibility: Boolean? = null
)
data class EduVerticalDialog(
    var titleText: String? = null,
    var titleGravity: Int? = null,
    var contentText: String? = null,
    var contentGravity: Int? = null,
    var contentBolds: List<Pair<Int, Int>>? = null,
    var duration: Long? = null,
    var height: Int? = null,
    var visibility: Boolean? = null
)
data class EduCover(
    var duration: Long? = null,
    var left: Float? = null,
    var top: Float? = null,
    var right: Float? = null,
    var bottom: Float? = null,
    var hasStroke: Boolean? = null,
    var visibility: Boolean? = null
)
data class EduArrow(
    var duration: Long? = null,
    var endTo: String? = null,
    var visibility: Boolean? = null
)
data class EduAction(
    var id: String? = null,
    var message: String? = null
)
data class EduFinger(
    var id: String,
    var source: Int = R.drawable.todo_circle,
    var x: Float = 0.0f,
    var y: Float = 0.0f,
    var width: Float,
    var height: Float,
    var rotation: Float = 0.0f,
    var pickAnimatorSet: ((ImageView) -> AnimatorSet)
)
data class EduData(
    val dialog: EduDialog = EduDialog(),
    val imageDialog: EduImageDialog = EduImageDialog(),
    val topDialog: EduVerticalDialog = EduVerticalDialog(),
    val bottomDialog: EduVerticalDialog = EduVerticalDialog(),
    val cover: EduCover = EduCover(),
    val arrow: EduArrow = EduArrow(),
    val action: EduAction = EduAction(),
    val fingers: ArrayList<EduFinger> = ArrayList()
)

/*
    ## 소개
    교육 화면이다.

    ## 주의점
    단위는 dp이다.

    ## 사용법
    1. 교육을 진행할 레이아웃에 아래 코드를 작성한다.
    ```
    <com.sungkyul.synergy.utils.EduScreen
        android:id="@+id/edu_screen"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
    ```

    2. EduCourses에 원하는 교육 코스 함수를 만들고, 교육을 진행할 액티비티에 아래 코드를 작성한다.
    ```
    binding.eduScreen.post {
        binding.eduScreen.course = EduCourses.nameCourse(
            binding.eduScreen.context,
            binding.eduScreen.width.toFloat(),
            binding.eduScreen.height.toFloat()
        )
        binding.eduScreen.start(this)
    }
    ```

    ### 프래그먼트에서 onAction 사용하기
    1. EduScreen을 사용할 프래그먼트 클래스를 다음과 같이 작성한다.
    ```
    class NameFragment(private val eduListener: EduListener) : Fragment() {
        ...
    }
    ```

    2. 프래그먼트 클래스 안의 원하는 이벤트 리스너에 onAction을 호출한다.
    ```
    eduListener.onAction("id", "message")
    ```

    ## 멤버
    - course
    - setOnFinishedCourseListener(l): 교육 코스가 끝났을 때 이벤트가 발생한다.
    - onAction(id, message)
    - start(activity)
*/
class EduScreen(context: Context, attrs: AttributeSet?): FrameLayout(context, attrs), EduListener {
    private val eduScreenFragment = EduScreenFragment()
    private var onFinishedCourseListener: (() -> Unit)? = null
    private val currentEduData = EduData(
        EduDialog(
            "",
            Gravity.START,
            "",
            Gravity.START,
            listOf(),
            0,
            0.0f,
            0.0f,
            0.0f,
            0.0f,
            false
        ),
        EduImageDialog(
            "",
            R.drawable.todo_rect,
            0,
            0.0f,
            0.0f,
            0.0f,
            0.0f,
            false
        ),
        EduVerticalDialog(
            "",
            Gravity.START,
            "",
            Gravity.START,
            listOf(),
            0,
            0,
            false
        ),
        EduVerticalDialog(
            "",
            Gravity.START,
            "",
            Gravity.START,
            listOf(),
            0,
            0,
            false
        ),
        EduCover(
            0,
            0.0f,
            0.0f,
            0.0f,
            0.0f,
            false,
            false
        ),
        EduArrow(
            0,
            "dialog",
            false
        ),
        EduAction(),
        arrayListOf()
    )
    private var num = -1
    var course = ArrayList<EduData>()

    init {
        eduScreenFragment.setEduListener(this)
    }

    override fun onPosted() {
        next()
    }

    override fun onAction(id: String, message: String?): Boolean {
        val eduData = course[num]
        Log.i(id, message ?: "null")
        if(id == eduData.action.id && (eduData.action.message == null || message == eduData.action.message)) {
            next()
            return true
        }
        return false
    }

    fun setOnFinishedCourseListener(l: (() -> Unit)?) {
        onFinishedCourseListener = l
    }

    fun start(activity: AppCompatActivity) {
        bringToFront()

        setOnClickListener {
            next()
        }
        isClickable = false

        // fragment_edu_screen.xml 화면으로 전환한다.
        activity.supportFragmentManager.beginTransaction()
            .replace(id, eduScreenFragment)
            .commit()
    }

    fun next() {
        num++

        if(num >= course.size) {
            onFinishedCourseListener?.invoke()
            return
        }

        /*
            현재 EduData를 course[num]에 해당하는 EduData로 갱신하는 작업이다.
        */
        val currentDialog = currentEduData.dialog
        val dialog = course[num].dialog
        currentDialog.titleText = dialog.titleText ?: currentDialog.titleText
        currentDialog.titleGravity = dialog.titleGravity ?: currentDialog.titleGravity
        currentDialog.contentText = dialog.contentText ?: currentDialog.contentText
        currentDialog.contentGravity = dialog.contentGravity ?: currentDialog.contentGravity
        currentDialog.contentBolds = dialog.contentBolds ?: currentDialog.contentBolds
        currentDialog.duration = dialog.duration ?: currentDialog.duration
        currentDialog.top = dialog.top ?: currentDialog.top
        currentDialog.bottom = dialog.bottom ?: currentDialog.bottom
        currentDialog.start = dialog.start ?: currentDialog.start
        currentDialog.end = dialog.end ?: currentDialog.end

        val currentCover = currentEduData.cover
        val cover = course[num].cover
        currentCover.duration = cover.duration ?: currentCover.duration
        currentCover.left = cover.left ?: currentCover.left
        currentCover.top = cover.top ?: currentCover.top
        currentCover.right = cover.right ?: currentCover.right
        currentCover.bottom = cover.bottom ?: currentCover.bottom

        val currentArrow = currentEduData.arrow
        val arrow = course[num].arrow
        currentArrow.duration = arrow.duration ?: currentArrow.duration
        currentArrow.endTo = arrow.endTo ?: currentArrow.endTo

        val currentAction = currentEduData.action
        val action = course[num].action
        currentAction.id = action.id
        currentAction.message = action.message

        val currentFingers = currentEduData.fingers
        val fingers = course[num].fingers
        currentFingers.clear()
        currentFingers.addAll(fingers)

        /*
            현재 EduData(+ course[num]의 EduData)를 참고하여 교육 화면을 변경한다.
        */
        // 다이얼로그의 제목과 내용을 변경한다.
        eduScreenFragment.setDialogTitle(currentDialog.titleText!!, currentDialog.titleGravity!!)
        eduScreenFragment.setDialogContent(currentDialog.contentText!!, currentDialog.contentGravity!!, currentDialog.contentBolds!!)
        // 다이얼로그를 이동시킨다.
        eduScreenFragment.translateDialog(
            currentDialog.duration!!,
            currentDialog.top!!,
            currentDialog.bottom!!,
            currentDialog.start!!,
            currentDialog.end!!
        )
        // 제목 텍스트가 없으면 숨기고, 있으면 보여준다.
        if(currentDialog.titleText == "") {
            eduScreenFragment.hideDialogTitle()
        } else {
            eduScreenFragment.showDialogTitle()
        }
        // 다이얼로그를 보여줄까 숨길까
        if(currentDialog.visibility == false && dialog.visibility == true) {
            eduScreenFragment.showDialog()
        }
        if(currentDialog.visibility == true && dialog.visibility == false) {
            eduScreenFragment.hideDialog()
        }

        // 박스를 이동시킨다.
        eduScreenFragment.translateBox(
            currentCover.duration!!,
            currentCover.left!!,
            currentCover.top!!,
            currentCover.right!!,
            currentCover.bottom!!
        )
        // 박스 선을 보여줄까 숨길까
        if(currentCover.hasStroke == false && cover.hasStroke == true) {
            eduScreenFragment.showBoxStroke()
        }
        if(currentCover.hasStroke == true && cover.hasStroke == false) {
            eduScreenFragment.hideBoxStroke()
        }
        // 커버를 보여줄까 숨길까
        // 커버가 있으면 교육 화면을 클릭할 수 있게 된다.
        if(currentCover.visibility == false && cover.visibility == true) {
            isClickable = true
            eduScreenFragment.showCover()
        }
        if(currentCover.visibility == true && cover.visibility == false) {
            isClickable = false
            eduScreenFragment.hideCover()
        }

        // 화살표를 이동시킨다.
        eduScreenFragment.translateArrowStart(currentArrow.duration!!)
        if(currentArrow.endTo == "dialog") {
            eduScreenFragment.translateArrowEndToDialog(currentArrow.duration!!)
        }
        if(currentArrow.endTo == "box") {
            eduScreenFragment.translateArrowEndToBox(currentArrow.duration!!)
        }
        // 화살표를 보여줄까 숨길까
        if(currentArrow.visibility == false && arrow.visibility == true) {
            eduScreenFragment.showArrow()
        }
        if(currentArrow.visibility == true && arrow.visibility == false) {
            eduScreenFragment.hideArrow()
        }

        /*
            현재 EduData에 있는 손들을 제거하고, course[num]에 있는 손들을 추가한다.
            손 제스처를 화면에 보여준다.
        */
        eduScreenFragment.clearHands()
        for(i in currentFingers) {
            eduScreenFragment.addHand(
                i.id,
                i.source,
                i.x,
                i.y,
                i.width,
                i.height,
                i.rotation,
                i.pickAnimatorSet
            )
        }

        /*
            교육 화면 변경 이후에 현재 EduData를 갱신해야 하는 작업은 마지막에 한다.
        */
        currentDialog.visibility = dialog.visibility ?: currentDialog.visibility
        currentCover.hasStroke = cover.hasStroke ?: currentCover.hasStroke
        currentCover.visibility = cover.visibility ?: currentCover.visibility
        currentArrow.visibility = arrow.visibility ?: currentArrow.visibility

        Log.i("eduScreen", currentEduData.dialog.contentText.toString())
    }
}
