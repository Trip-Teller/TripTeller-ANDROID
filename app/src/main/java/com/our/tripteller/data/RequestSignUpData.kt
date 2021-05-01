package com.our.tripteller.data

data class RequestSignUpData(
    val age: Int,
    val birthDate: String?,
    val password: String?,
    val gender: String?,
    val email: String?,
    val nickname: String?
    )