package com.our.tripteller.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RequestToServer {

    var unsplash = Retrofit.Builder()
        .baseUrl("https://api.unsplash.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BODY)

    var client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    var retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://trip-teller-dev-tnb2rokc2q-du.a.run.app")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var serviceUnsplash: RequestInterface = unsplash.create(RequestInterface::class.java)
    var service: RequestInterface = retrofit.create(RequestInterface::class.java)
}

