package com.example.tutorial_1


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostItem(
    @SerialName("body")
    val body: String,
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("userId")
    val userId: Int
)