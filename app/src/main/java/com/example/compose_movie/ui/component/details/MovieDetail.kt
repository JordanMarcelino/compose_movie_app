package com.example.compose_movie.ui.component.details

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.compose_movie.data.model.domain.Movie
import com.example.compose_movie.ui.viewmodel.MovieViewModel
import kotlinx.coroutines.launch
import java.net.URLDecoder

@Composable
fun MovieDetails(
    navController: NavHostController,
    url: String,
    title: String,
    rate: String,
    date: String,
    overview: String,
    id: Int,
    adult: Boolean
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                MovieImage(
                    url = url,
                    title = title,
                    rate = rate,
                    date = date,
                    adult = adult
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = overview,
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Other movie",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                PopularMovieRow(
                    navController = navController,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Spacer(modifier = Modifier.height(100.dp))
            }
            TopSection(
                navController,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(16.dp, 16.dp),
                url = url,
                title = title,
                rate = rate,
                date = date,
                overview = overview,
                id = id,
                adult = adult
            )
        }
    }

}

@Composable
fun TopSection(
    navController: NavController,
    modifier: Modifier = Modifier,
    url: String,
    title: String,
    rate: String,
    date: String,
    overview: String,
    id: Int,
    adult: Boolean,
    movieViewModel: MovieViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember {
        SnackbarHostState()
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth(0.1f)
                .fillMaxHeight(0.08f)
                .background(
                    Color(
                        0xFF0a9396
                    ).copy(alpha = 0.55f)
                )
                .clickable {
                    navController.popBackStack()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }
        Box(
            modifier = modifier
                .offset((-24).dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth(0.2f)
                .fillMaxHeight(0.2f)
                .background(
                    Color(
                        0xFF001219
                    )
                )
                .padding(vertical = 8.dp)
                .clickable {
                    val movie = Movie(
                        url = url,
                        title = title,
                        rate = rate,
                        date = date,
                        overview = overview,
                        id = id,
                        adult = adult
                    )
                    movieViewModel.saveMovie(
                        movie
                    )
                    scope.launch {
                        val snackBarResult = snackBarHostState.showSnackbar(
                            "Success adding movie to favourite",
                            duration = SnackbarDuration.Short,
                            actionLabel = "Undo"
                        )
                        if (snackBarResult == SnackbarResult.ActionPerformed) {
                            movieViewModel.deleteMovie(movie)
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Back",
                tint = Color.Red,
                modifier = Modifier.size(36.dp)
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier.align(Alignment.Center)
        ) { snackbarData ->
            Card(
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.White),
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentSize()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Text(text = snackbarData.message)
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = snackbarData.actionLabel.toString())
                    }
                }
            }
            Log.i("MYTAG", snackbarData.message)
        }
    }
}

@Composable
fun MovieImage(
    url: String,
    title: String,
    rate: String,
    date: String,
    adult: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(URLDecoder.decode(url, "utf-8"))
                .crossfade(true)
                .build(),
            contentDescription = title,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)),
            contentScale = ContentScale.Crop,
            loading = {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary, modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
        )
        Box(
            modifier = Modifier
                .height(500.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        )
                    )
                )
                .align(Alignment.BottomCenter)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 36.sp,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageDesc(desc = if (adult) "18+" else "12+")
                ImageDesc(desc = date)
                ImageDesc(desc = rate)
            }
        }
    }
}

@Composable
fun ImageDesc(
    desc: String,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(
                Color(
                    0xFF0a9396
                ).copy(alpha = 0.55f)
            )
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = desc,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )
    }
}