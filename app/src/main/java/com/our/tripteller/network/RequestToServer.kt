package com.our.tripteller.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RequestToServer {

    var unsplash = Retrofit.Builder()
        .baseUrl("https://api.unsplash.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var serviceUnsplash: RequestInterface = unsplash.create(RequestInterface::class.java)
}

