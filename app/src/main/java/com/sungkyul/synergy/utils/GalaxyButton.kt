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
    터치 다운일 때 다음 인터랙션이 재생된다.
        원의 위치: (x, y)
        원의 투명도: 0% → 25%
        원의 반지름: startRadius → 버튼의 중심점에서 꼭짓점까지의 거리
    터치 업일 때 다음 인터랙션이 재생된다.
        원의 투명도: 25% → 0%

    주의점:
        `view.post {}` 내에서 `clipTo<shape>` 함수를 사용해야 한다. 안 그러면 함수 내에서 width/height를 사용하지 못한다.
*/
class GalaxyButton(context: Context, attrs: AttributeSet?): View(context, attrs) {
    private var circleX = 0.0f
    private var circleY = 0.0f
    private var circleRadius = 0.0f
    private val clipPath = Path()
    private val circlePaint = Paint()
    private var toggle = false

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

    fun clipToRoundRect(cornerRadius: Float) {
        clipPath.reset()
        clipPath.addRoundRect(
            0.0f,
            0.0f,
            width.toFloat(),
            height.toFloat(),
            DisplayUtils.dpToPx(context, cornerRadius),
            DisplayUtils.dpToPx(context, cornerRadius),
            Path.Direction.CCW
        )
        invalidate()
    }

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

    fun setColor(color: Int) {
        circlePaint.color = color
    }

    fun startTouchDownAnimation(x: Float, y: Float, startRadius: Float) {
        circleX = x
        circleY = y
        circleRadius = startRadius

        AnimUtils.startValueAnimatorOfFloat({
            circlePaint.alpha = (51*it).toInt()

            val diagonal = sqrt((width*width+height*height).toDouble()).toFloat()
            circleRadius = startRadius+(diagonal-startRadius)*it

            invalidate()
        }, 0.0f, 1.0f, 500, DecelerateInterpolator())

        toggle = true
    }

    fun startTouchUpAnimation() {
        AnimUtils.startValueAnimatorOfFloat({
            circlePaint.alpha = (51*it).toInt()
            invalidate()
        }, 1.0f, 0.0f, 500, DecelerateInterpolator())

        toggle = false
    }

    fun getToggle() = toggle
}
