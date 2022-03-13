package com.example.compose_movie.ui.component.details.movie

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.compose_movie.data.model.web.movie.Result
import com.example.compose_movie.data.util.Resource
import com.example.compose_movie.ui.component.navgraph.Screen
import com.example.compose_movie.ui.theme.ShimmerColorShades
import com.example.compose_movie.ui.viewmodel.MovieViewModel
import java.net.URLEncoder

@Composable
fun MovieSection(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    movieViewModel: MovieViewModel = hiltViewModel(),
) {

    val currentState by remember {
        movieViewModel.currentState
    }

    if (currentState is Resource.Success) {
        PopularMovieRow(
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
                movieViewModel.loadPopularMovie()
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
fun PopularMovieRow(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    movieViewModel: MovieViewModel = hiltViewModel()
) {

    val movies by remember {
        movieViewModel.movie
    }

    val endReached by remember {
        movieViewModel.endReached
    }

    val currentState by remember {
        movieViewModel.currentState
    }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(movies.size) {
            if (it >= movies.size - 1 && currentState !is Resource.Loading && !endReached) {
                LaunchedEffect(key1 = true) {
                    movieViewModel.loadPopularMovie()
                }
            }
            MovieCard(movie = movies[it]) { res ->
                val path = Screen.MovieDetail.navigateDetailMovieWithArgs(
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

@Composable
fun MovieCard(
    movie: Result,
    modifier: Modifier = Modifier,
    onClicked: (Result) -> Unit
) {
    Card(
        elevation = 8.dp,
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(18.dp))
            .clickable {
                onClicked(movie)
            }
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.posterPath)
                .crossfade(true)
                .build(),
            contentDescription = movie.originalTitle,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(18.dp)),
            loading = {
                ShimmerAnimation()
            }
        )
    }
}

@Composable
fun Retry(
    error: String,
    onRetry: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = error,
            fontSize = 24.sp,
            color = MaterialTheme.colors.error,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }
}

@Composable
fun ShimmerItem(
    brush: Brush
) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .width(150.dp)
            .background(brush = brush)
            .clip(RoundedCornerShape(24.dp))
    )
}

@Composable
fun ShimmerAnimation() {
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1000, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        )
    )
    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )
    ShimmerItem(brush = brush)
}