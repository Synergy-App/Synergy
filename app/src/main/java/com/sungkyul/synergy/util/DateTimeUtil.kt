package com.sungkyul.synergy.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class DateTimeUtil {
    companion object {
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일")
        val timeFormatter = DateTimeFormatter.ofPattern("h:mm")

        fun getKoreanDayOfWeek(dateTime: LocalDateTime): String {
            return when(dateTime.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())) {
                "Mon" -> "월요일"
                "Tue" -> "화요일"
                "Wed" -> "수요일"
                "Thu" -> "목요일"
                "Fri" -> "금요일"
                "Sat" -> "토요일"
                "Sun" -> "일요일"
                else -> ""
            }
        }

        fun getKoreanPeriod(dateTime: LocalDateTime): String {
            return if(dateTime.hour < 12) "오전" else "오후"
        }
    }
}
