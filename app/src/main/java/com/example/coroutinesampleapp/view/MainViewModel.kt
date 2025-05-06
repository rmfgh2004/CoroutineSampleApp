package com.example.coroutinesampleapp.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinesampleapp.model.Post
import com.example.coroutinesampleapp.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val postRepository = PostRepository()

    private val _posts = MutableLiveData<List<Post>>(emptyList())
    val posts : LiveData<List<Post>> = _posts

    fun loadPosts() {
        viewModelScope.launch {
            _posts.value = postRepository.getPosts()
        }
    }

}