package com.example.coroutinesampleapp.api

import com.example.coroutinesampleapp.model.Post
import retrofit2.http.GET

interface PostApiService {

    @GET("posts")
    suspend fun getPosts() : List<Post>
}