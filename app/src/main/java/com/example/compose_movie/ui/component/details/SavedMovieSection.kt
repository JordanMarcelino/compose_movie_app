package com.example.compose_movie.ui.component.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.compose_movie.data.model.domain.Movie
import com.example.compose_movie.ui.component.navgraph.Screen
import com.example.compose_movie.ui.viewmodel.MovieViewModel
import java.net.URLEncoder

@Composable
fun FavoriteMovie(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    movieViewModel: MovieViewModel = hiltViewModel()
) {
    val movies = movieViewModel.getSavedMovie().collectAsState(initial = listOf())

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(movies.value.size) {
            MovieSavedCard(movie = movies.value[it]) { res ->
                val path = Screen.MovieDetail.navigateDetailMovieWithArgs(
                    url = URLEncoder.encode(res.url, "utf-8"),
                    title = res.title.toString(),
                    rate = res.rate.toString(),
                    date = res.date.toString(),
                    overview = res.overview.toString(),
                    id = res.id.toString().toInt(),
                    adult = res.adult
                )
                navController.navigate(path)
            }
            Spacer(modifier = Modifier.width(32.dp))
        }
    }
}

@Composable
fun MovieSavedCard(
    movie: Movie,
    modifier: Modifier = Modifier,
    onClicked: (Movie) -> Unit
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
                .data(movie.url)
                .crossfade(true)
                .build(),
            contentDescription = movie.title,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(18.dp)),
            loading = {
                ShimmerAnimation()
            }
        )
    }
}