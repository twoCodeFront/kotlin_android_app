package com.example.tutorial_1

class PostRepository {
    suspend fun getPosts(): List<PostItem> {
        return RetrofitInstance.api.getPosts()
    }
}