package com.example.compose_movie.ui.component.details.tvshow

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun SearchedTvShow(
    navController: NavHostController,
    query : String,
    tvShowViewModel: TvShowViewModel = hiltViewModel()
) {


    val currentState by remember {
        tvShowViewModel.currentStateSearched
    }

    if (currentState is Resource.Success) {
        SearchedTvShowColumn(
            navController = navController,
        )
    } else if (currentState.message == "Internet is not available") {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(24.dp)),
            contentAlignment = Alignment.Center
        )
        {
            Retry(currentState.message.toString()) {
                tvShowViewModel.loadSearchedTvShow(query)
            }
        }
    }

}

@Composable
fun SearchedTvShowColumn(
    navController: NavHostController,
    tvShowViewModel: TvShowViewModel = hiltViewModel()
) {

    val searched by remember {
        tvShowViewModel.searched
    }

    val currentState by remember {
        tvShowViewModel.currentStateSearched
    }

    val endReached by remember {
        tvShowViewModel.endReachedSearched
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(end = 8.dp)
    ) {
        items(searched.size) {
            if (it >= searched.size - 1 && currentState !is Resource.Loading && !endReached) {
                LaunchedEffect(key1 = true) {
                    tvShowViewModel.loadSearchedCachedTvShow()
                }
            }

            TvShowSearchedRow(tvShow = searched[it]) { res ->
                val path = Screen.TvShowSearched.navigateDetailTvShowWithArgs(
                    url = URLEncoder.encode(res.posterPath, "utf-8"),
                    title = res.name.toString(),
                    rate = res.voteAverage.toString(),
                    date = res.firstAirDate.toString(),
                    overview = res.overview.toString(),
                    id = res.id.toString().toInt(),
                )
                navController.navigate(path)
            }
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}

@Composable
fun TvShowSearchedRow(
    tvShow: Result,
    modifier: Modifier = Modifier,
    onClicked: (Result) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
            .clickable {
                onClicked(tvShow)
            },
        horizontalArrangement = Arrangement.Start
    ) {
        Card(
            modifier = modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(18.dp))
        )
        {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(tvShow.posterPath)
                    .crossfade(true)
                    .build(),
                contentDescription = tvShow.name,
                modifier = Modifier.weight(0.7f),
                loading = {
                    ShimmerAnimation()
                }
            )
        }
        Spacer(modifier = Modifier
            .width(16.dp))
        Column(
            modifier = Modifier
                .weight(0.4f)
                .padding(start = 8.dp, end = 10.dp, bottom = 8.dp, top = 12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = tvShow.name.toString(),
                fontSize = 18.sp,
                color = Color.Black,
                maxLines = 2,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = tvShow.firstAirDate.toString(),
                fontSize = 16.sp,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = tvShow.overview.toString(),
                fontSize = 16.sp,
                color = Color.Black,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}