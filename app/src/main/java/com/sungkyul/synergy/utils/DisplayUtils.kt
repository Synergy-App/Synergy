package com.sungkyul.synergy.utils

import android.content.Context
import android.graphics.Point
import android.graphics.Rect
import android.util.TypedValue
import androidx.window.layout.WindowMetricsCalculator

class DisplayUtils {
    companion object {
        fun getBounds(context: Context): Rect {
            val windowMetrics = WindowMetricsCalculator.getOrCreate()
                .computeCurrentWindowMetrics(context)

            return windowMetrics.bounds
        }

        fun getSize(context: Context): Point {
            val bounds = getBounds(context)

            return Point(bounds.width(), bounds.height())
        }

        fun dpToPx(context: Context, dp: Float): Float {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                context.resources.displayMetrics
            )
        }

        fun pxToDp(context: Context, px: Float): Float {
            return px / context.resources.displayMetrics.density
        }

        fun convertXFromRatioToPx(context: Context, ratio: Float): Float {
            return ratio * getSize(context).x
        }

        fun convertYFromRatioToPx(context: Context, ratio: Float): Float {
            return ratio * getSize(context).y
        }
    }
}
