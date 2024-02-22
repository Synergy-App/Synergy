package com.sungkyul.synergy.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import kotlin.math.sqrt

/*
    ## 소개
    터치 다운/업을 할 때 다이나믹한 인터렉션이 나오는 버튼이다.

    ## 주의점
    - `view.post {}` 내에서 `clipTo` 함수를 사용하자. 안 그러면 계산되지 않은 width, height를 사용하게 된다.
*/
class DynamicButton(context: Context, attrs: AttributeSet?): View(context, attrs) {
    private var circleX = 0.0f
    private var circleY = 0.0f
    private var circleRadius = 0.0f
    private val clipPath = Path()
    private val circlePaint = Paint()

    init {
        circlePaint.color = Color.BLACK
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        clipToRect()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawColor(Color.TRANSPARENT)
        canvas.clipPath(clipPath)
        canvas.drawCircle(circleX, circleY, circleRadius, circlePaint)
    }

    // 캔버스를 사각형으로 클리핑한다.
    fun clipToRect() {
        clipPath.reset()
        clipPath.addRect(
            0.0f,
            0.0f,
            width.toFloat(),
            height.toFloat(),
            Path.Direction.CCW
        )
        invalidate()
    }

    // 캔버스를 둥근 사각형으로 클리핑한다.
    fun clipToRoundRect(cornerRadius: Float) {
        clipPath.reset()
        clipPath.addRoundRect(
            0.0f,
            0.0f,
            width.toFloat(),
            height.toFloat(),
            AnimUtils.dpToPx(context, cornerRadius),
            AnimUtils.dpToPx(context, cornerRadius),
            Path.Direction.CCW
        )
        invalidate()
    }

    // 캔버스를 원으로 클리핑한다.
    fun clipToCircle() {
        clipPath.reset()
        clipPath.addCircle(
            width/2.0f,
            height/2.0f,
            width/2.0f,
            Path.Direction.CCW
        )
        invalidate()
    }

    // 원의 색을 설정한다.
    fun setColor(color: Int) {
        circlePaint.color = color
    }

    // 터치 다운 애니메이션을 시작한다.
    fun startTouchDownAnimation(x: Float, y: Float, startRadius: Float) {
        circleX = x
        circleY = y
        circleRadius = startRadius
        AnimUtils.startValueAnimatorOfFloat({
            circlePaint.alpha = (36*it).toInt()

            val diagonal = sqrt((width*width+height*height).toDouble()).toFloat()
            circleRadius = startRadius+(diagonal-startRadius)*it

            invalidate()
        }, 0.0f, 1.0f, 500, DecelerateInterpolator())
    }

    // 터치 업 애니메이션을 시작한다.
    fun startTouchUpAnimation() {
        AnimUtils.startValueAnimatorOfFloat({
            circlePaint.alpha = (36*it).toInt()
            invalidate()
        }, 1.0f, 0.0f, 500, DecelerateInterpolator())
    }
}
