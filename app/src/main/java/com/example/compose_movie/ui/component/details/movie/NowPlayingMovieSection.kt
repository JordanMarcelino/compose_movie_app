package com.example.compose_movie.ui.component.details.movie

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.compose_movie.data.util.Resource
import com.example.compose_movie.ui.component.navgraph.Screen
import com.example.compose_movie.ui.viewmodel.MovieViewModel
import java.net.URLEncoder

@Composable
fun NowPlayingMovie(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    movieViewModel: MovieViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true){
        movieViewModel.loadLatestMovie()
    }

    val currentState by remember {
        movieViewModel.currentStateNowPlaying
    }

    if (currentState is Resource.Success) {
        NowPlayingMovieRow(
            navController = navController,
            modifier = modifier
        )
    } else if (currentState is Resource.Error) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(24.dp)),
            contentAlignment = Alignment.Center
        )
        {
            Retry(currentState.message.toString()) {
                movieViewModel.loadLatestMovie()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(24.dp)),
        contentAlignment = Alignment.Center
    ) {
        if (currentState is Resource.Loading) {
            ShimmerAnimation()
        }

    }
}

@Composable
fun NowPlayingMovieRow(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    movieViewModel: MovieViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true){
        movieViewModel.loadLatestMovie()
    }

    val movies by remember {
        movieViewModel.nowPlaying
    }

    val endReached by remember {
        movieViewModel.endReachedNowPlaying
    }

    val currentState by remember {
        movieViewModel.currentStateNowPlaying
    }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(movies.size) {
            if (it >= movies.size - 1 && currentState !is Resource.Loading && !endReached) {
                LaunchedEffect(key1 = true) {
                    movieViewModel.loadLatestMovie()
                }
            }
            MovieCard(movie = movies[it]) { res ->
                val path = Screen.MovieNowPlayingDetail.navigateDetailMovieWithArgs(
                    url = URLEncoder.encode(res.posterPath, "utf-8"),
                    title = res.title.toString(),
                    rate = res.voteAverage.toString(),
                    date = res.releaseDate.toString(),
                    overview = res.overview.toString(),
                    id = res.id.toString().toInt(),
                    adult = res.adult!!
                )
                navController.navigate(path)
            }
            Spacer(modifier = Modifier.width(32.dp))
        }
    }
}