package com.sungkyul.synergy.types

import com.google.gson.annotations.SerializedName

data class ExamCheckResult(
    val correct: Boolean,
    val correctAnswer: Int,
    val answerOnSelect: Int,
    val explanation: String?
)
