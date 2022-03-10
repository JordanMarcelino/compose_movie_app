package com.example.compose_movie.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_movie.ui.viewmodel.MovieViewModel

@Composable
fun HomeScreen(
    movieViewModel: MovieViewModel = hiltViewModel()
) {
    Box() {
        Text(text = "Home")
    }
}