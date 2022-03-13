package com.example.compose_movie.ui.component.details.tvshow

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
import com.example.compose_movie.data.model.domain.TvShow
import com.example.compose_movie.ui.component.details.movie.ShimmerAnimation
import com.example.compose_movie.ui.component.navgraph.Screen
import com.example.compose_movie.ui.viewmodel.MovieViewModel
import com.example.compose_movie.ui.viewmodel.TvShowViewModel
import java.net.URLEncoder

@Composable
fun FavoriteTvShow(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    SavedTvShowRow(navController = navController, modifier = modifier)
}

@Composable
fun TvShowSavedCard(
    tvShow: TvShow,
    modifier: Modifier = Modifier,
    onClicked: (TvShow) -> Unit
) {
    Card(
        elevation = 8.dp,
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(18.dp))
            .clickable {
                onClicked(tvShow)
            }
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(tvShow.url)
                .crossfade(true)
                .build(),
            contentDescription = tvShow.title,
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
fun SavedTvShowRow(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    tvShowViewModel: TvShowViewModel = hiltViewModel()
) {

    val tvShows = tvShowViewModel.getSavedTvShow().collectAsState(initial = listOf())

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(tvShows.value.size) {
            TvShowSavedCard(tvShow = tvShows.value[it]) { res ->
                val path = Screen.TvShowSavedDetail.navigateDetailTvShowWithArgs(
                    url = URLEncoder.encode(res.url, "utf-8"),
                    title = res.title,
                    rate = res.rate,
                    date = res.date,
                    overview = res.overview,
                    id = res.id.toString().toInt()
                )
                navController.navigate(path)
            }
            Spacer(modifier = Modifier.width(32.dp))
        }
    }
}