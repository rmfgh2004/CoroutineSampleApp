package com.example.coroutinesampleapp.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutinesampleapp.R
import com.example.coroutinesampleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by lazy {
        val factory = MainViewModelFactory()
        ViewModelProvider.create(this, factory)[MainViewModel::class.java]
    }
    private lateinit var adapter: PostAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initListView()
        observe()

        viewModel.loadPosts()
    }

    private fun initListView() {
        adapter = PostAdapter()
        binding.recyclerViewPost.adapter = adapter
        binding.recyclerViewPost.layoutManager = LinearLayoutManager(this)
    }

    private fun observe() {
        viewModel.posts.observe(this) {
            adapter.submitList(it)

            if (it.isEmpty()) {
                binding.progressLoading.isVisible = true
                binding.recyclerViewPost.isVisible = false
            } else {
                binding.progressLoading.isVisible = false
                binding.recyclerViewPost.isVisible = true
            }
        }
    }
}