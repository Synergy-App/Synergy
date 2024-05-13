package com.sungkyul.synergy.backend.auth

import com.sungkyul.synergy.backend.ApiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {
    @POST("user/signup")
    fun signup(@Body requestBody: SignUpBody): Call<ApiResponse<Nothing>>

    @POST("user/check-id")
    fun checkId(@Body requestBody: CheckIdBody): Call<ApiResponse<CheckResult>>

    @POST("user/check-nickname")
    fun checkNickname(@Body requestBody: CheckNicknameBody): Call<ApiResponse<CheckResult>>

    @POST("user/signin")
    fun signin(@Body requestBody: SignInBody): Call<ApiResponse<SignInResult>>
}