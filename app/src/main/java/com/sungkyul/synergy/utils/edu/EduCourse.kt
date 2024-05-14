package com.sungkyul.synergy.utils.edu

import android.animation.AnimatorSet
import android.content.Context
import android.widget.ImageView
import com.sungkyul.synergy.R

data class EduDialog(
    var titleText: String? = null,
    var titleFont: Int? = null,
    var titleSize: Float? = null,
    var titleGravity: Int? = null,
    var titleColor: String? = null,
    var contentText: String? = null,
    var contentFont: Int? = null,
    var contentSize: Float? = null,
    var contentGravity: Int? = null,
    var contentColor: String? = null,
    var duration: Long? = null,
    var top: Float? = null,
    var bottom: Float? = null,
    var start: Float? = null,
    var end: Float? = null,
    var background: Int? = null,
    var visibility: Boolean? = null
)
data class EduImageDialog(
    var titleText: String? = null,
    var titleColor: String? = null,
    var source: Int? = null,
    var duration: Long? = null,
    var top: Float? = null,
    var bottom: Float? = null,
    var start: Float? = null,
    var end: Float? = null,
    var background: Int? = null,
    var visibility: Boolean? = null
)
data class EduVerticalDialog(
    var titleText: String? = null,
    var titleGravity: Int? = null,
    var titleColor: String? = null,
    var contentText: String? = null,
    var contentGravity: Int? = null,
    var contentColor: String? = null,
    var contentBolds: List<Pair<Int, Int>>? = null,
    var duration: Long? = null,
    var height: Int? = null,
    var background: Int? = null,
    var visibility: Boolean? = null
)
data class EduCover(
    var duration: Long? = null,
    var boxLeft: Float? = null,
    var boxTop: Float? = null,
    var boxRight: Float? = null,
    var boxBottom: Float? = null,
    var boxVisibility: Boolean? = null,
    var boxStrokeVisibility: Boolean? = null,
    var backgroundColor: String? = null,
    var visibility: Boolean? = null,
    var isClickable: Boolean? = null
)
data class EduArrow(
    var duration: Long? = null,
    var endTo: Int? = null,
    var visibility: Boolean? = null
)
data class EduAction(
    var id: String? = null,
    var message: String? = null
)
data class EduHand(
    var id: String,
    var source: Int = R.drawable.hand,
    var x: Float = 0.0f,
    var y: Float = 0.0f,
    var width: Float = 50.0f,
    var height: Float = 75.0f,
    var rotation: Float = 0.0f,
    var gesture: ((Context, ImageView) -> AnimatorSet)
)
data class EduData(
    val dialog: EduDialog = EduDialog(),
    val imageDialog: EduImageDialog = EduImageDialog(),
    val topDialog: EduVerticalDialog = EduVerticalDialog(),
    val bottomDialog: EduVerticalDialog = EduVerticalDialog(),
    val cover: EduCover = EduCover(),
    val arrow: EduArrow = EduArrow(),
    val action: EduAction = EduAction(),
    val hands: ArrayList<EduHand> = ArrayList()
)

interface EduCourse {
    val list: ArrayList<EduData>
    val width: Float
    val height: Float
}
