package com.sungkyul.synergy.backend.auth

import android.os.Parcel
import android.os.Parcelable
import com.sungkyul.synergy.types.User

// SignInBody 클래스
data class SignInBody(
    val authId: String,
    val pw: String
) // Parcelable 필요 없음

// SignUpBody 클래스
data class SignUpBody(
    val authId: String,
    val pw: String,
    val pwRe: String,
    val nickname: String,
    val phone: String
) // Parcelable 필요 없음

// CheckNicknameBody 클래스
data class CheckNicknameBody(
    val nickname: String
) // Parcelable 필요 없음

// CheckIdBody 클래스
data class CheckIdBody(
    val authId: String
) // Parcelable 필요 없음

// CheckResult 클래스
data class CheckResult(
    val available: Boolean
) // Parcelable 필요 없음

// SignInResult 클래스
data class SignInResult(
    val accessToken: String,
    val user: User
) // Parcelable 필요 없음

// FindIdBody 클래스
data class FindIdBody(
    val phone: String
) // Parcelable 필요 없음

// FindIdResult 클래스
data class FindIdResult(
    val authId: String?
) // Parcelable 필요 없음

// VerifyUserBody 클래스
data class VerifyUserBody(
    val authId: String,
    val phone: String
) // Parcelable 필요 없음

// 기존의 VerifyUserResult 데이터 클래스에 Parcelable 구현 추가
/*
data class ResetKeyData(
    val resetKey: String
)
*/

data class VerifyUserResult(
    val success: Boolean,
    val err: ErrorResponse?,
    val resetKey: String
)
data class ErrorResponse(
    val msg: String,
    val code: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(msg)
        parcel.writeString(code)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ErrorResponse> {
        override fun createFromParcel(parcel: Parcel): ErrorResponse {
            return ErrorResponse(parcel)
        }

        override fun newArray(size: Int): Array<ErrorResponse?> {
            return arrayOfNulls(size)
        }
    }
}

// ChangePasswordBody 클래스
data class ChangePasswordBody(
    val password: String,
    val resetKey: String
) // Parcelable 필요 없음

// ChangePasswordResult 클래스
data class ChangePasswordResult(
    val success: Boolean,
    val err: ErrorResponse?,
    val data: Any?
) // Parcelable 필요 없음
