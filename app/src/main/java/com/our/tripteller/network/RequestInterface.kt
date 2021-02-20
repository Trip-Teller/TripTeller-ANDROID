package com.our.tripteller.network

import com.our.tripteller.data.ResponseUnsplashData
import retrofit2.Call
import retrofit2.http.*

interface RequestInterface{

    @GET("/search/photos")
    fun requestBackgroundImg(@QueryMap options:Map<String, String>, @QueryMap options02:Map<String, Int>): Call<ResponseUnsplashData>

}