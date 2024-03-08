package com.sungkyul.synergy.backend
import retrofit2.Call
import retrofit2.http.GET

interface questionAPI {
    @GET("quiz/question_api.php")
    fun getQuestion(): Call<List<QuizQuestion>>
}