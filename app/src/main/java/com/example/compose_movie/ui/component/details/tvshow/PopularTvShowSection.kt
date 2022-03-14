package com.example.compose_movie.ui.component.details.tvshow

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.compose_movie.data.model.web.tvshow.Result
import com.example.compose_movie.data.util.Resource
import com.example.compose_movie.ui.component.details.movie.Retry
import com.example.compose_movie.ui.component.details.movie.ShimmerAnimation
import com.example.compose_movie.ui.component.navgraph.Screen
import com.example.compose_movie.ui.viewmodel.TvShowViewModel
import java.net.URLEncoder

@Composable
fun PopularTvShowRow(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    tvShowViewModel: TvShowViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true){
        tvShowViewModel.loadPopularTvShow()
    }

    val tvShow by remember {
        tvShowViewModel.popularTvShow
    }

    val endReached by remember {
        tvShowViewModel.endReached
    }

    val currentState by remember {
        tvShowViewModel.currentState
    }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(tvShow.size) {
            if (it >= tvShow.size - 1 && currentState !is Resource.Loading && !endReached) {
                LaunchedEffect(key1 = true) {
                    tvShowViewModel.loadPopularTvShow()
                }
            }
            TvShowCard(tvShow = tvShow[it]) { res ->
                val path = Screen.TvShowDetail.navigateDetailTvShowWithArgs(
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

@Composable
fun TvShowSection(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    tvShowViewModel: TvShowViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = true){
        tvShowViewModel.loadPopularTvShow()
    }

    val currentState by remember {
        tvShowViewModel.currentState
    }

    if (currentState is Resource.Success) {
        PopularTvShowRow(
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
                tvShowViewModel.loadPopularTvShow()
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
fun TvShowCard(
    tvShow: Result,
    modifier: Modifier = Modifier,
    onClicked: (Result) -> Unit
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
                .data(tvShow.posterPath)
                .crossfade(true)
                .build(),
            contentDescription = tvShow.name,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(18.dp)),
            loading = {
                ShimmerAnimation()
            }
        )
    }
}
