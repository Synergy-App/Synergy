package com.sungkyul.synergy.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class BitmapUtils {
    companion object {
        // 크기를 1/inSampleSize로 샘플링한 비트맵을 반환한다.
        fun createSampledBitmap(resId: Int, inSampleSize: Int, context: Context): Bitmap {
            val options = BitmapFactory.Options().apply {
                this.inSampleSize = inSampleSize
            }

            return BitmapFactory.decodeResource(context.resources, resId, options)
        }
    }
}
