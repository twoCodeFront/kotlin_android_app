package com.example.tutorial_1

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

sealed class UiState {
    object Loading: UiState()
    data class Success(val posts: List<Post>): UiState()
    data class Error(val message: String): UiState()
}

class PostViewModel: ViewModel() {
    private val repository = PostRepository()

    private val internalState: MutableState<UiState> = mutableStateOf(UiState.Loading)
    val state: State<UiState> = internalState


}