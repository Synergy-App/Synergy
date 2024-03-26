package com.sungkyul.synergy.backend

data class QuizQuestion(
    val question: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val correct_option: String,
    )

/**퀴즈 정보(난이도랑 문제 갯수)*/
data class QuizInfo(
    val learningInfo: String,
    val option1: String,
    val option2: String,
)

