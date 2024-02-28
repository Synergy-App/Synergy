package com.sungkyul.synergy.utils

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R

data class EduDialog(
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
)

/*
    ## 소개
    교육 화면이다.
*/
class EduScreen(context: Context, attrs: AttributeSet?): FrameLayout(context, attrs) {
    private val eduScreenFragment = EduScreenFragment()
    val course = ArrayList<EduData>()
    private var num = -1

    fun onAction(): Boolean {
        return false
    }

    fun start(activity: AppCompatActivity) {
        bringToFront()
        setOnClickListener {
            next()
        }

        // fragment_edu_screen.xml 화면으로 전환한다.
        activity.supportFragmentManager.beginTransaction()
            .replace(id, eduScreenFragment)
            .commit()

        post {
            eduScreenFragment.showDialog()
            eduScreenFragment.showCover()
            eduScreenFragment.showArrow()
            eduScreenFragment.showBoxStroke()
            eduScreenFragment.setDialogTitle("안녕", Gravity.START)
            eduScreenFragment.setDialogContent("abcdefg", Gravity.CENTER, listOf(Pair(1, 2)))

            eduScreenFragment.translateDialog(1000, 200.0f, 200.0f, 0.0f, AnimUtils.pxToDp(context, width/2.0f))
            eduScreenFragment.translateBox(1000, 100.0f, 100.0f, 200.0f, 200.0f)

            eduScreenFragment.translateArrowStart(0)
            eduScreenFragment.translateArrowEndToDialog(0)
            eduScreenFragment.translateArrowStart(1000)
            eduScreenFragment.translateArrowEndToBox(1000)
        }
    }

    fun next() {
        num++

        if(num == 0) {
            eduScreenFragment.addHand("touch", R.drawable.hand, 0.0f, 0.0f, 50.0f, 75.0f, R.animator.show_hand)

            eduScreenFragment.translateDialog(1000, 250.0f, 250.0f, 100.0f, 100.0f)
            eduScreenFragment.translateBox(1000, 200.0f, 600.0f, 350.0f, 700.0f)

            eduScreenFragment.translateArrowStart(1000)
            eduScreenFragment.translateArrowEndToBox(1000)
        }
        if(num == 1) {
            eduScreenFragment.removeHand("touch")


            eduScreenFragment.translateBox(1000, 100.0f, 600.0f, 150.0f, 700.0f)
            eduScreenFragment.translateArrowEndToBox(1000)
        }
        if(num == 2) {

            eduScreenFragment.translateBox(1000, 100.0f, 600.0f, 200.0f, 700.0f)
            eduScreenFragment.translateArrowEndToBox(1000)
        }
        if(num == 3) {

            eduScreenFragment.hideDialog()
            eduScreenFragment.hideCover()
            eduScreenFragment.hideBoxStroke()
            eduScreenFragment.hideArrow()
        }
    }
}
