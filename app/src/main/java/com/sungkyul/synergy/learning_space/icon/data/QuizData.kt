package com.sungkyul.synergy.learning_space.icon.data

data class QuizData(
    val question: String = "",
    val options: List<String> = emptyList(),
    val correctAnswer: String = ""
)
