package com.sungkyul.synergy.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

data class MapPiece(
    val resId: Int,
    val rect: Rect
)

/*
    이 뷰는 그림 조각들을 퍼즐처럼 맞춰서 하나의 맵으로 만들어준다.
*/
class MapView(context: Context, attrs: AttributeSet?): View(context, attrs) {
    var mapPieces: List<MapPiece> = listOf()

    private var canvasX = 0
    private var canvasY = 0

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawColor(Color.WHITE)

        for(i in mapPieces) {
            drawMapPiece(canvas, i)
        }
    }

    private fun drawMapPiece(canvas: Canvas, mapPiece: MapPiece) {
        val drawable = ContextCompat.getDrawable(context, mapPiece.resId)
        drawable?.bounds = mapPiece.rect
        drawable?.draw(canvas)
    }
}
