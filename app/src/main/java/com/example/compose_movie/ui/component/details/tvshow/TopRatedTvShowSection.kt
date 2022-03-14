package com.example.compose_movie.ui.component.details.tvshow

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.compose_movie.data.util.Resource
import com.example.compose_movie.ui.component.details.movie.Retry
import com.example.compose_movie.ui.component.details.movie.ShimmerAnimation
import com.example.compose_movie.ui.component.navgraph.Screen
import com.example.compose_movie.ui.viewmodel.TvShowViewModel
import java.net.URLEncoder

@Composable
fun TopRatedTvShowSection(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    tvShowViewModel: TvShowViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = true){
        tvShowViewModel.loadTopRatedTvShow()
    }

    val currentState by remember {
        tvShowViewModel.currentStateTopRated
    }

    if (currentState is Resource.Success) {
        TopRatedTvShowRow(
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
                tvShowViewModel.loadTopRatedTvShow()
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
fun TopRatedTvShowRow(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    tvShowViewModel: TvShowViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true){
        tvShowViewModel.loadTopRatedTvShow()
    }

    val tvShow by remember {
        tvShowViewModel.topRated
    }

    val endReached by remember {
        tvShowViewModel.endReachedTopRated
    }

    val currentState by remember {
        tvShowViewModel.currentStateTopRated
    }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(tvShow.size) {
            if (it >= tvShow.size - 1 && currentState !is Resource.Loading && !endReached) {
                LaunchedEffect(key1 = true) {
                    tvShowViewModel.loadTopRatedTvShow()
                }
            }
            TvShowCard(tvShow = tvShow[it]) { res ->
                val path = Screen.TvShowTopRatedDetail.navigateDetailTvShowWithArgs(
                    url = URLEncoder.encode(res.posterPath, "utf-8"),
                    title = res.name.toString(),
                    rate = res.voteAverage.toString(),
                    date = res.firstAirDate.toString(),
                    overview = res.overview.toString(),
                    id = res.id.toString().toInt(),
                )
                navController.navigate(path)
            }
            Spacer(modifier = Modifier.width(32.dp))
        }
    }
}