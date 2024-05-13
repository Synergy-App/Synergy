package com.sungkyul.synergy.utils.edu

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.widget.FrameLayout
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R

/*
    교육 화면 레이아웃이다.
*/
class EduScreen(context: Context, attrs: AttributeSet?): FrameLayout(context, attrs), EduListener {
    private val eduScreenFragment = EduScreenFragment()
    private var onFinishedCourseListener: (() -> Unit)? = null
    private val currentEduData = EduData(
        EduDialog(
            titleText = "",
            titleGravity = Gravity.START,
            titleColor = "#000000",
            contentText = "",
            contentGravity = Gravity.START,
            contentColor = "#000000",
            duration = 0,
            top = 0.0f,
            bottom = 0.0f,
            start = 0.0f,
            end = 0.0f,
            background = R.drawable.edu_dialog_bg,
            visibility = false
        ),
        EduImageDialog(
            titleText = "",
            titleColor = "#000000",
            source = R.drawable.todo_rect,
            duration = 0,
            top = 0.0f,
            bottom = 0.0f,
            start = 0.0f,
            end = 0.0f,
            background = R.drawable.edu_dialog_bg,
            visibility = false
        ),
        EduVerticalDialog(
            titleText = "",
            titleGravity = Gravity.START,
            titleColor = "#000000",
            contentText = "",
            contentGravity = Gravity.START,
            contentColor = "#000000",
            duration = 0,
            height = 0,
            background = R.drawable.edu_dialog_bg,
            visibility = false
        ),
        EduVerticalDialog(
            titleText = "",
            titleGravity = Gravity.START,
            titleColor = "#000000",
            contentText = "",
            contentGravity = Gravity.START,
            contentColor = "#000000",
            duration = 0,
            height = 0,
            background = R.drawable.edu_dialog_bg,
            visibility = false
        ),
        EduCover(
            duration = 0,
            boxLeft = 0.0f,
            boxTop = 0.0f,
            boxRight = 0.0f,
            boxBottom = 0.0f,
            boxVisibility = false,
            boxStrokeVisibility = false,
            visibility = false
        ),
        EduArrow(
            duration = 0,
            endTo = DIALOG,
            visibility = false
        ),
        EduAction(),
        arrayListOf()
    )
    private var num = -1
    var course: EduCourse? = null

    init {
        eduScreenFragment.setEduListener(this)
    }

    override fun onPosted() {
        next()
    }

    override fun onAction(id: String, message: String?): Boolean {
        // 교육 코스가 시작하지 않았거나 끝났으면, 모든 액션을 다시 사용할 수 있게 된다.
        if(num < 0 || num >= course!!.list.size) return true

        val eduData = course!!.list[num]
        Log.i(id, message ?: "null")
        if(id == eduData.action.id && (eduData.action.message == null || message == eduData.action.message)) {
            next()
            return true
        }
        return false
    }

    fun getAction(): EduAction {
        return currentEduData.action
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

        if(num >= course!!.list.size) {
            onFinishedCourseListener?.invoke()
            return
        }

        /*
        현재 EduData를 course!!.list[num]에 해당하는 EduData로 갱신하는 작업이다.
        */
        val currentDialog = currentEduData.dialog
        val dialog = course!!.list[num].dialog
        currentDialog.titleText = dialog.titleText ?: currentDialog.titleText
        currentDialog.titleGravity = dialog.titleGravity ?: currentDialog.titleGravity
        currentDialog.titleColor = dialog.titleColor ?: currentDialog.titleColor
        currentDialog.contentText = dialog.contentText ?: currentDialog.contentText
        currentDialog.contentGravity = dialog.contentGravity ?: currentDialog.contentGravity
        currentDialog.contentColor = dialog.contentColor ?: currentDialog.contentColor
        currentDialog.duration = dialog.duration ?: currentDialog.duration
        currentDialog.top = dialog.top ?: currentDialog.top
        currentDialog.bottom = dialog.bottom ?: currentDialog.bottom
        currentDialog.start = dialog.start ?: currentDialog.start
        currentDialog.end = dialog.end ?: currentDialog.end
        currentDialog.background = dialog.background ?: currentDialog.background

        val currentCover = currentEduData.cover
        val cover = course!!.list[num].cover
        currentCover.duration = cover.duration ?: currentCover.duration
        currentCover.boxLeft = cover.boxLeft ?: currentCover.boxLeft
        currentCover.boxTop = cover.boxTop ?: currentCover.boxTop
        currentCover.boxRight = cover.boxRight ?: currentCover.boxRight
        currentCover.boxBottom = cover.boxBottom ?: currentCover.boxBottom

        val currentArrow = currentEduData.arrow
        val arrow = course!!.list[num].arrow
        currentArrow.duration = arrow.duration ?: currentArrow.duration
        currentArrow.endTo = arrow.endTo ?: currentArrow.endTo

        val currentAction = currentEduData.action
        val action = course!!.list[num].action
        currentAction.id = action.id
        currentAction.message = action.message

        val currentHands = currentEduData.hands
        val hands = course!!.list[num].hands
        currentHands.clear()
        currentHands.addAll(hands)

        /*
        현재 EduData(+ course!!.list[num]의 EduData)를 참고하여 교육 화면을 변경한다.
        */
        // 다이얼로그의 제목과 내용을 변경한다.
        eduScreenFragment.setDialogTitle(currentDialog.titleText!!, currentDialog.titleGravity!!, currentDialog.titleColor!!)
        eduScreenFragment.setDialogContent(currentDialog.contentText!!, currentDialog.contentGravity!!, currentDialog.contentColor!!)
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
        // 다이얼로그의 배경색을 변경한다.
        eduScreenFragment.setDialogBackground(currentDialog.background!!)
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
            currentCover.boxLeft!!,
            currentCover.boxTop!!,
            currentCover.boxRight!!,
            currentCover.boxBottom!!
        )
        // 박스를 보여줄까 숨길까
        if(currentCover.boxVisibility == false && cover.boxVisibility == true) {
            eduScreenFragment.showBox()
        }
        if(currentCover.boxVisibility == true && cover.boxVisibility == false) {
            eduScreenFragment.hideBox()
        }
        // 박스 선을 보여줄까 숨길까
        if(currentCover.boxStrokeVisibility == false && cover.boxStrokeVisibility == true) {
            eduScreenFragment.showBoxStroke()
        }
        if(currentCover.boxStrokeVisibility == true && cover.boxStrokeVisibility == false) {
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
        if(currentArrow.endTo == DIALOG) {
            eduScreenFragment.translateArrowEndToDialog(currentArrow.duration!!)
        }
        if(currentArrow.endTo == BOX) {
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
        현재 EduData에 있는 손들을 제거하고, course!!.list[num]에 있는 손들을 추가한다.
        손 제스처를 화면에 보여준다.
        */
        eduScreenFragment.clearHands()
        for(i in currentHands) {
            eduScreenFragment.addHand(
                id = i.id,
                source = i.source,
                xDp = i.x,
                yDp = i.y,
                widthDp = i.width,
                heightDp = i.height,
                rotation = i.rotation,
                gesture = i.gesture
            )
        }

        /*
        교육 화면 변경 이후에 현재 EduData를 갱신해야 하는 작업은 마지막에 한다.
        */
        currentDialog.visibility = dialog.visibility ?: currentDialog.visibility
        currentCover.boxVisibility = cover.boxVisibility ?: currentCover.boxVisibility
        currentCover.boxStrokeVisibility = cover.boxStrokeVisibility ?: currentCover.boxStrokeVisibility
        currentCover.visibility = cover.visibility ?: currentCover.visibility
        currentArrow.visibility = arrow.visibility ?: currentArrow.visibility
    }

    fun prev() {
        if(num > 0) {
            num -= 2
            next()
        }
    }

    companion object {
        const val DIALOG = 0
        const val BOX = 1

        fun toTop(from: AppCompatActivity, to: Class<out AppCompatActivity>) {
            val intent = Intent(from, to)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            from.startActivity(intent)
        }

        fun navigateBackToTop(from: AppCompatActivity, to: Class<out AppCompatActivity>) {
            from.onBackPressedDispatcher.addCallback(from, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    toTop(from, to)
                }
            })
        }
    }
}
