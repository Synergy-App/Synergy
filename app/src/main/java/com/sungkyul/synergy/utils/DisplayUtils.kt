package com.sungkyul.synergy.utils

import android.content.Context
import android.util.TypedValue

class DisplayUtils {
    companion object {
        // dp에서 px로 변환한다.
        fun dpToPx(context: Context, dp: Float): Float {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                context.resources.displayMetrics
            )
        }

        // px에서 dp로 변환한다.
        fun pxToDp(context: Context, px: Float): Float {
            return px / context.resources.displayMetrics.density
        }
    }
}
