package com.example.coroutinesampleapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostApiClient {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    val api: PostApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostApiService::class.java)
    }

}