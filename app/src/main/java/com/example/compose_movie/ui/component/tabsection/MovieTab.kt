package com.example.compose_movie.ui.component.tabsection

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.compose_movie.ui.component.details.movie.FavoriteMovie
import com.example.compose_movie.ui.component.details.movie.MovieSection
import com.example.compose_movie.ui.component.details.movie.NowPlayingMovie

@Composable
fun ColumnScope.MovieTab(
    navController: NavHostController,
) {
    MovieSection(
        navController = navController,
    )
    Spacer(modifier = Modifier.height(32.dp))
    Text(
        text = "Latest Movie",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .align(Alignment.Start),
        color = Color.White
    )
    Spacer(modifier = Modifier.height(8.dp))
    NowPlayingMovie(navController = navController)
    Spacer(modifier = Modifier.height(32.dp))
    Text(
        text = "My Favorites",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .align(Alignment.Start),
        color = Color.White
    )
    Spacer(modifier = Modifier.height(8.dp))
    FavoriteMovie(navController = navController)
    Spacer(modifier = Modifier.height(100.dp))
}