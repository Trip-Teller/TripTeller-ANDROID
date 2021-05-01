package com.our.tripteller.network

import com.google.gson.JsonObject
import com.our.tripteller.data.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface RequestInterface{

    @GET("/search/photos")
    fun requestBackgroundImg(@QueryMap options:Map<String, String>, @QueryMap options02:Map<String, Int>): Call<ResponseUnsplashData>

    @Multipart
    @POST("/user/v0")
    fun requestSignup( @Body body:RequestSignUpData,
        @Part profileImage: MultipartBody.Part
    ): Call<ResponseSignUpData>

    @POST("/auth/v0")
    fun requestSignin(@Body body: JsonObject): Call<ResponseSignInData>

}