package com.sungkyul.synergy.types

data class ExamCheckResult(
    val correct: Boolean,
    val correctAnswer: Int,
    val answerOnSelect: Int,
    val explanation: String?
)
