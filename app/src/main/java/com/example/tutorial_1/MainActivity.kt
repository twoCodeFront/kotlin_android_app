package com.example.tutorial_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tutorial_1.ui.theme.Tutorial_1Theme
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider

class MainActivity : ComponentActivity() {

    private val postModel = PostViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tutorial_1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PostScreen(postViewModel = postModel,
                        modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview
@Composable
fun LoadingView() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}


@Composable
fun ErrorView(message: String){
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
       Text(text = message, color = Color.Red)
    }
}

@Composable
fun PostList(posts: List<PostItem>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(8.dp)
    ) {
        items(posts) {post ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(6.dp),
                elevation = CardDefaults.cardElevation(6.dp),
                colors = CardDefaults.cardColors(
                    contentColor = Color.White
                )
            ){
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = post.title,
                        color= Color.Red
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
                }
            }
        }
    }
}

@Composable
fun PostScreen(postViewModel: PostViewModel, modifier: Modifier = Modifier) {
    val state = postViewModel.state.value

    when(state) {
        is UiState.Loading -> LoadingView()
        is UiState.Success -> PostList(posts = state.posts)
        is UiState.Error -> ErrorView(message = "${state.message}")
    }
}