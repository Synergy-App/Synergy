package com.sungkyul.synergy.utils

import android.content.Context
import android.view.View
import android.widget.TextView

class GalaxyNote9 {
    companion object {
        fun setTitleSize(text: TextView) {
            text.textSize = 28.0f
        }

        fun setSubtitleSize(text: TextView) {
            text.textSize = 20.0f
        }

        fun setHeaderHeight(context: Context, header: View) {
            header.layoutParams.apply {
                header.layoutParams.height = DisplayUtils.dpToPx(context, 175.0f).toInt()
            }
        }
    }
}