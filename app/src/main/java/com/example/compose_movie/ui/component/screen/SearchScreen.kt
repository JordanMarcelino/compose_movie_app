package com.example.compose_movie.ui.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.compose_movie.data.model.web.movie.Result
import com.example.compose_movie.data.util.Resource
import com.example.compose_movie.ui.component.details.movie.Retry
import com.example.compose_movie.ui.component.details.movie.ShimmerAnimation
import com.example.compose_movie.ui.component.navgraph.Screen
import com.example.compose_movie.ui.viewmodel.MovieViewModel
import java.net.URLEncoder

@Composable
fun SearchScreen(
    navController: NavHostController,
    movieViewModel: MovieViewModel = hiltViewModel()
) {

    var query by remember {
        mutableStateOf("")
    }

    var index by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        SearchedBox(value = query) {
            movieViewModel.loadSearchedMovie(it)
            query = it
        }
        TabSection(navController = navController) {
            index = it
        }
        when (index) {
            0 -> {
                SearchedMovie(
                    navController = navController,
                    query = query
                )
                Spacer(modifier = Modifier.height(100.dp))
            }
            1 -> {

            }
        }

    }
}

@Composable
fun SearchedMovie(
    navController: NavHostController,
    query : String,
    modifier: Modifier = Modifier,
    movieViewModel: MovieViewModel = hiltViewModel()
) {


    val currentState by remember {
        movieViewModel.currentStateSearched
    }

    if (currentState is Resource.Success) {
        SearchedMovieColumn(
            navController = navController,
            modifier = modifier
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
                movieViewModel.loadSearchedMovie(query)
            }
        }
    }

}

@Composable
fun SearchedMovieColumn(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    movieViewModel: MovieViewModel = hiltViewModel()
) {

    val searched by remember {
        movieViewModel.searched
    }

    val currentState by remember {
        movieViewModel.currentStateSearched
    }

    val endReached by remember {
        movieViewModel.endReachedSearched
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(end = 8.dp)
    ) {
        items(searched.size) {
            if (it >= searched.size - 1 && currentState !is Resource.Loading && !endReached) {
                LaunchedEffect(key1 = true) {
                    movieViewModel.loadSearchedCachedMovie()
                }
            }

            MovieSearchedRow(movie = searched[it]) { res ->
                val path = Screen.MovieSearched.navigateDetailMovieWithArgs(
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
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}

@Composable
fun MovieSearchedRow(
    movie: Result,
    modifier: Modifier = Modifier,
    onClicked: (Result) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
            .clickable {
                onClicked(movie)
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
                    .data(movie.posterPath)
                    .crossfade(true)
                    .build(),
                contentDescription = movie.originalTitle,
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
                text = movie.title.toString(),
                fontSize = 18.sp,
                color = Color.Black,
                maxLines = 2,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = movie.releaseDate.toString(),
                fontSize = 16.sp,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movie.overview.toString(),
                fontSize = 16.sp,
                color = Color.Black,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun SearchedBox(
    value: String,
    onValueChanged: (String) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            value = value,
            onValueChange = {
                onValueChanged(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White),
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            placeholder = {
                Text(
                    text = "Search",
                    color = Color.Black,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.Black
                )
            }
        )
    }
}