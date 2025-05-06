package com.example.coroutinesampleapp.repository

import com.example.coroutinesampleapp.api.PostApiClient
import com.example.coroutinesampleapp.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository {

    private val api = PostApiClient.api

    suspend fun getPosts() : List<Post> {
        return withContext(Dispatchers.IO) {
            api.getPosts()
        }
    }

}