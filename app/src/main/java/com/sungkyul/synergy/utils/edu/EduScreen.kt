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
    var course: EduCourse? = null
    private val eduScreenFragment = EduScreenFragment()
    private var onFinishedCourseListener: (() -> Unit)? = null
    private val currentEduData = initEduData()
    private var num = -1

    init {
        eduScreenFragment.setEduListener(this)
    }

    override fun onPosted() {
        next()
    }

    override fun onAction(id: String, message: String?): Boolean {
        if(!(0 <= num && num < course!!.list.size)) return true

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

        replaceToEduScreenFragment(activity)
    }

    fun next() {
        num++

        if(num >= course!!.list.size) {
            onFinishedCourseListener?.invoke()
            return
        }

        updateCurrentDialog()
        updateCurrentBottomDialog()
        updateCurrentCover()
        updateCurrentArrow()
        updateCurrentAction()
        updateCurrentHands()

        configureEduScreenFragmentDialog()
        configureEduScreenFragmentBottomDialog()
        configureEduScreenFragmentCover()
        configureEduScreenFragmentArrow()
        configureEduScreenFragmentHands()

        eduScreenFragment.draw()
    }

    fun prev() {
        if(num > 0) {
            num -= 2
            next()
        }
    }

    private fun initEduData(): EduData {
        return EduData(
            EduDialog(
                titleText = "",
                titleFont = R.font.pretendard_semibold,
                titleSize = 20.0f,
                titleGravity = Gravity.START,
                titleColor = R.color.black,
                contentText = "",
                contentFont = R.font.pretendard_regular,
                contentSize = 18.0f,
                contentGravity = Gravity.START,
                contentColor = R.color.black,
                separatorColor = R.color.light_grey,
                separatorWidth = 5,
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
                top = 0.0f,
                bottom = 0.0f,
                start = 0.0f,
                end = 0.0f,
                background = R.drawable.edu_dialog_bg,
                visibility = false
            ),
            EduVerticalDialog(
                titleText = "",
                titleFont = R.font.pretendard_semibold,
                titleSize = 20.0f,
                titleGravity = Gravity.START,
                titleColor = R.color.black,
                contentText = "",
                contentFont = R.font.pretendard_regular,
                contentSize = 18.0f,
                contentGravity = Gravity.START,
                contentColor = R.color.black,
                separatorColor = R.color.light_grey,
                separatorWidth = 5,
                height = 0.0f,
                background = R.drawable.edu_top_dialog_bg,
                visibility = false
            ),
            EduVerticalDialog(
                titleText = "",
                titleFont = R.font.pretendard_semibold,
                titleSize = 20.0f,
                titleGravity = Gravity.START,
                titleColor = R.color.black,
                contentText = "",
                contentFont = R.font.pretendard_regular,
                contentSize = 18.0f,
                contentGravity = Gravity.START,
                contentColor = R.color.black,
                separatorColor = R.color.light_grey,
                separatorWidth = 5,
                height = 0.0f,
                background = R.drawable.edu_bottom_dialog_bg,
                visibility = false
            ),
            EduCover(
                boxLeft = 0.0f,
                boxTop = 0.0f,
                boxRight = 0.0f,
                boxBottom = 0.0f,
                boxVisibility = false,
                boxBorderVisibility = false,
                backgroundColor = R.color.black,
                visibility = false,
                isClickable = false,
                boxPadding = 0.0f,
                boxBorderColor = R.color.lime,
                boxBorderWidth = 15.0f
            ),
            EduArrow(
                endTo = DIALOG,
                visibility = false
            ),
            EduAction(),
            arrayListOf()
        )
    }

    private fun updateCurrentDialog() {
        val currentDialog = currentEduData.dialog
        val dialog = course!!.list[num].dialog

        currentDialog.titleText = dialog.titleText ?: currentDialog.titleText
        currentDialog.titleFont = dialog.titleFont ?: currentDialog.titleFont
        currentDialog.titleSize = dialog.titleSize ?: currentDialog.titleSize
        currentDialog.titleGravity = dialog.titleGravity ?: currentDialog.titleGravity
        currentDialog.titleColor = dialog.titleColor ?: currentDialog.titleColor
        currentDialog.contentText = dialog.contentText ?: currentDialog.contentText
        currentDialog.contentFont = dialog.contentFont ?: currentDialog.contentFont
        currentDialog.contentSize = dialog.contentSize ?: currentDialog.contentSize
        currentDialog.contentGravity = dialog.contentGravity ?: currentDialog.contentGravity
        currentDialog.contentColor = dialog.contentColor ?: currentDialog.contentColor
        currentDialog.separatorColor = dialog.separatorColor ?: currentDialog.separatorColor
        currentDialog.separatorWidth = dialog.separatorWidth ?: currentDialog.separatorWidth
        currentDialog.top = dialog.top ?: currentDialog.top
        currentDialog.bottom = dialog.bottom ?: currentDialog.bottom
        currentDialog.start = dialog.start ?: currentDialog.start
        currentDialog.end = dialog.end ?: currentDialog.end
        currentDialog.background = dialog.background ?: currentDialog.background
    }

    private fun updateCurrentBottomDialog() {
        val currentDialog = currentEduData.bottomDialog
        val dialog = course!!.list[num].bottomDialog

        currentDialog.titleText = dialog.titleText ?: currentDialog.titleText
        currentDialog.titleFont = dialog.titleFont ?: currentDialog.titleFont
        currentDialog.titleSize = dialog.titleSize ?: currentDialog.titleSize
        currentDialog.titleGravity = dialog.titleGravity ?: currentDialog.titleGravity
        currentDialog.titleColor = dialog.titleColor ?: currentDialog.titleColor
        currentDialog.contentText = dialog.contentText ?: currentDialog.contentText
        currentDialog.contentFont = dialog.contentFont ?: currentDialog.contentFont
        currentDialog.contentSize = dialog.contentSize ?: currentDialog.contentSize
        currentDialog.contentGravity = dialog.contentGravity ?: currentDialog.contentGravity
        currentDialog.contentColor = dialog.contentColor ?: currentDialog.contentColor
        currentDialog.separatorColor = dialog.separatorColor ?: currentDialog.separatorColor
        currentDialog.separatorWidth = dialog.separatorWidth ?: currentDialog.separatorWidth
        currentDialog.height = dialog.height ?: currentDialog.height
        currentDialog.background = dialog.background ?: currentDialog.background
    }

    private fun updateCurrentCover() {
        val currentCover = currentEduData.cover
        val cover = course!!.list[num].cover

        currentCover.boxLeft = cover.boxLeft ?: currentCover.boxLeft
        currentCover.boxTop = cover.boxTop ?: currentCover.boxTop
        currentCover.boxRight = cover.boxRight ?: currentCover.boxRight
        currentCover.boxBottom = cover.boxBottom ?: currentCover.boxBottom
        currentCover.backgroundColor = cover.backgroundColor ?: currentCover.backgroundColor
        currentCover.isClickable = cover.isClickable ?: currentCover.isClickable
        currentCover.boxPadding = cover.boxPadding ?: currentCover.boxPadding
        currentCover.boxBorderColor = cover.boxBorderColor ?: currentCover.boxBorderColor
        currentCover.boxBorderWidth = cover.boxBorderWidth ?: currentCover.boxBorderWidth
    }

    private fun updateCurrentArrow() {
        val currentArrow = currentEduData.arrow
        val arrow = course!!.list[num].arrow

        currentArrow.endTo = arrow.endTo ?: currentArrow.endTo
    }

    private fun updateCurrentAction() {
        val currentAction = currentEduData.action
        val action = course!!.list[num].action

        currentAction.id = action.id
        currentAction.message = action.message
    }

    private fun updateCurrentHands() {
        val currentHands = currentEduData.hands
        val hands = course!!.list[num].hands

        currentHands.clear()
        currentHands.addAll(hands)
    }

    private fun configureEduScreenFragmentDialog() {
        val currentDialog = currentEduData.dialog
        val dialog = course!!.list[num].dialog

        eduScreenFragment.setDialogTitle(currentDialog.titleText!!, currentDialog.titleGravity!!, currentDialog.titleColor!!)
        eduScreenFragment.setDialogContent(currentDialog.contentText!!, currentDialog.contentGravity!!, currentDialog.contentColor!!)

        eduScreenFragment.setDialogTitleFont(currentDialog.titleFont!!)
        eduScreenFragment.setDialogContentFont(currentDialog.contentFont!!)

        eduScreenFragment.setDialogTitleSize(currentDialog.titleSize!!)
        eduScreenFragment.setDialogContentSize(currentDialog.contentSize!!)

        eduScreenFragment.translateDialog(
            currentDialog.top!!,
            currentDialog.bottom!!,
            currentDialog.start!!,
            currentDialog.end!!,
            if(hasChangedDialogVisibility(from = false, to = true)) 0 else EduScreenFragment.DIALOG_MOVEMENT_DURATION
        )

        if(currentDialog.titleText == "") {
            eduScreenFragment.hideDialogTitle()
        } else {
            eduScreenFragment.showDialogTitle()
        }

        eduScreenFragment.setDialogSeparatorColor(currentDialog.separatorColor!!)
        eduScreenFragment.setDialogSeparatorWidth(currentDialog.separatorWidth!!)

        eduScreenFragment.setDialogBackground(currentDialog.background!!)

        if(hasChangedDialogVisibility(from = false, to = true)) {
            eduScreenFragment.showDialog()
        }
        if(hasChangedDialogVisibility(from = true, to = false)) {
            eduScreenFragment.hideDialog()
        }

        currentDialog.visibility = dialog.visibility ?: currentDialog.visibility
    }

    private fun configureEduScreenFragmentBottomDialog() {
        val currentDialog = currentEduData.bottomDialog
        val dialog = course!!.list[num].bottomDialog

        eduScreenFragment.setBottomDialogTitle(currentDialog.titleText!!, currentDialog.titleGravity!!, currentDialog.titleColor!!)
        eduScreenFragment.setBottomDialogContent(currentDialog.contentText!!, currentDialog.contentGravity!!, currentDialog.contentColor!!)

        eduScreenFragment.setBottomDialogTitleFont(currentDialog.titleFont!!)
        eduScreenFragment.setBottomDialogContentFont(currentDialog.contentFont!!)

        eduScreenFragment.setBottomDialogTitleSize(currentDialog.titleSize!!)
        eduScreenFragment.setBottomDialogContentSize(currentDialog.contentSize!!)

        eduScreenFragment.translateBottomDialog(
            currentDialog.height!!,
            if(hasChangedBottomDialogVisibility(from = false, to = true)) 0 else EduScreenFragment.DIALOG_MOVEMENT_DURATION
        )

        if(currentDialog.titleText == "") {
            eduScreenFragment.hideBottomDialogTitle()
        } else {
            eduScreenFragment.showBottomDialogTitle()
        }

        eduScreenFragment.setBottomDialogSeparatorColor(currentDialog.separatorColor!!)
        eduScreenFragment.setBottomDialogSeparatorWidth(currentDialog.separatorWidth!!)

        eduScreenFragment.setBottomDialogBackground(currentDialog.background!!)

        if(hasChangedBottomDialogVisibility(from = false, to = true)) {
            eduScreenFragment.showBottomDialog()
        }
        if(hasChangedBottomDialogVisibility(from = true, to = false)) {
            eduScreenFragment.hideBottomDialog()
        }

        currentDialog.visibility = dialog.visibility ?: currentDialog.visibility
    }

    private fun hasChangedDialogVisibility(from: Boolean, to: Boolean): Boolean {
        val currentDialog = currentEduData.dialog
        val dialog = course!!.list[num].dialog

        return currentDialog.visibility == from && dialog.visibility == to
    }

    private fun hasChangedBottomDialogVisibility(from: Boolean, to: Boolean): Boolean {
        val currentDialog = currentEduData.bottomDialog
        val dialog = course!!.list[num].bottomDialog

        return currentDialog.visibility == from && dialog.visibility == to
    }

    private fun configureEduScreenFragmentCover() {
        val currentCover = currentEduData.cover
        val cover = course!!.list[num].cover

        eduScreenFragment.translateBox(
            currentCover.boxLeft!!,
            currentCover.boxTop!!,
            currentCover.boxRight!!,
            currentCover.boxBottom!!,
            if(hasChangedBoxVisibility(from = false, to = true)) 0 else EduScreenFragment.BOX_MOVEMENT_DURATION
        )

        // TODO(박스 테두리 애니메이션을 따로 만들어야 할까? 박스 등장/숨김을 하면 박스 테두리 애니메이션이 끊기는데)

        eduScreenFragment.setBoxPadding(currentCover.boxPadding!!)
        eduScreenFragment.setBoxBorderWidth(currentCover.boxBorderWidth!!)

        if(cover.boxBorderVisibility == true || (cover.boxBorderVisibility == null && currentCover.boxBorderVisibility == true)) {
            eduScreenFragment.setBoxBorderColor(currentCover.boxBorderColor!!)
        }
        if(cover.visibility == true || (cover.visibility == null && currentCover.visibility == true)) {
            eduScreenFragment.setCoverBackgroundColor(currentCover.backgroundColor!!)
        }

        if(hasChangedBoxVisibility(from = false, to = true)) {
            eduScreenFragment.showBox()
        }
        if(hasChangedBoxVisibility(from = true, to = false)) {
            eduScreenFragment.hideBox()
        }

        if(currentCover.boxBorderVisibility == false && cover.boxBorderVisibility == true) {
            eduScreenFragment.showBoxBorder()
        }
        if(currentCover.boxBorderVisibility == true && cover.boxBorderVisibility == false) {
            eduScreenFragment.hideBoxBorder()
        }

        if(currentCover.visibility == false && cover.visibility == true) {
            eduScreenFragment.showCover()
        }
        if(currentCover.visibility == true && cover.visibility == false) {
            eduScreenFragment.hideCover()
        }

        isClickable = currentCover.isClickable!!

        currentCover.boxVisibility = cover.boxVisibility ?: currentCover.boxVisibility
        currentCover.boxBorderVisibility = cover.boxBorderVisibility ?: currentCover.boxBorderVisibility
        currentCover.visibility = cover.visibility ?: currentCover.visibility
    }

    private fun hasChangedBoxVisibility(from: Boolean, to: Boolean): Boolean {
        val currentCover = currentEduData.cover
        val cover = course!!.list[num].cover

        return currentCover.boxVisibility == from && cover.boxVisibility == to
    }

    private fun configureEduScreenFragmentArrow() {
        val currentArrow = currentEduData.arrow
        val arrow = course!!.list[num].arrow

        eduScreenFragment.translateArrowStart()
        if(currentArrow.endTo == DIALOG) {
            eduScreenFragment.translateArrowEndToDialog()
        }
        if(currentArrow.endTo == BOX) {
            eduScreenFragment.translateArrowEndToBox()
        }

        if(currentArrow.visibility == false && arrow.visibility == true) {
            eduScreenFragment.showArrow()
        }
        if(currentArrow.visibility == true && arrow.visibility == false) {
            eduScreenFragment.hideArrow()
        }

        currentArrow.visibility = arrow.visibility ?: currentArrow.visibility
    }

    private fun configureEduScreenFragmentHands() {
        val currentHands = currentEduData.hands

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
    }

    private fun replaceToEduScreenFragment(activity: AppCompatActivity) {
        activity.supportFragmentManager.beginTransaction()
            .replace(id, eduScreenFragment)
            .commit()
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
