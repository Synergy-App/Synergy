package com.sungkyul.synergy.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class GeometryUtils {
    companion object {
        // a와 b 사이의 선형 보간을 구한다.
        // 0 ≤ t ≤ 1
        fun linear(a: Float, b: Float, t: Float): Float {
            return a+(b-a)*t
        }
    }
}
