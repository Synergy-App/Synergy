package com.sungkyul.synergy.backend.auth

import com.sungkyul.synergy.types.User

data class SignInBody (
    /** 아이디 */
    val authId: String,
    /** 패스워드 */
    val pw: String,
)

data class SignUpBody (
    /** 아이디 */
    val authId: String,
    /** 패스워드 */
    val pw: String,
    /** 패스워드 재입력 */
    val pwRe: String,
    /** 닉네임 */
    val nickname: String,
    /** 휴대폰번호 */
    val phone: String
)

data class CheckNicknameBody (
    val nickname: String
)

data class CheckIdBody (
    val authId: String
)


/** POST /user/check-id, POST /user/check-nickname 응답데이터 */
data class CheckResult (
    /** 아이디/닉네임의 사용가능여부 */
    val available: Boolean
)

/** POST /user/signin 응답데이터 */
data class SignInResult (
    /** 유저의 JWT토큰 */
    val accessToken: String,
    /** 로그인한 유저 정보 */
    val user: User
)