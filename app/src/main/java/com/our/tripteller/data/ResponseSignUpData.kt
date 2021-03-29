package com.our.tripteller.data

data class ResponseSignUpData (
    val id: Int,
    val age: Int,
    val birthDate: String,
    val password: String,
    val gender: String,
    val email: String,
    val nickname: String,
    val createdAt: String,
    val updatedAt: String
)