package com.example.compose_movie.ui.component.details.tvshow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.compose_movie.data.model.domain.TvShow
import com.example.compose_movie.ui.component.details.movie.ImageDesc
import com.example.compose_movie.ui.component.details.movie.SnackBarHost
import com.example.compose_movie.ui.component.details.movie.TopSection
import com.example.compose_movie.ui.viewmodel.TvShowViewModel
import kotlinx.coroutines.launch
import java.net.URLDecoder

@Composable
fun TvShowDetails(
    navController: NavHostController,
    url: String,
    title: String,
    rate: String,
    date: String,
    overview: String,
    id: Int,
    tvShowRow: @Composable () -> Unit,
    tvShowViewModel: TvShowViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val tvShow = TvShow(
        url = url,
        title = title,
        rate = rate,
        date = date,
        overview = overview,
        id = id,
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        snackbarHost = { snackbarHostState ->
            SnackBarHost(snackBarHostState = snackbarHostState) {
                tvShowViewModel.deleteTvShow(tvShow)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    TvShowImage(
                        url = url,
                        title = title,
                        rate = rate,
                        date = date,
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
                        text = "Other Movie",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    tvShowRow()
                    Spacer(modifier = Modifier.height(100.dp))
                }
                TopSection(
                    navController = navController,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(16.dp, 16.dp),
                    onSave = {
                        scope.launch {
                            tvShowViewModel.saveTvShow(
                                tvShow
                            )
                            scaffoldState.snackbarHostState.showSnackbar(
                                "Success adding tv show to favourite",
                                duration = SnackbarDuration.Short,
                                actionLabel = "Undo"
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun TvShowImage(
    url: String,
    title: String,
    rate: String,
    date: String,
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
                ImageDesc(desc = date)
                ImageDesc(desc = rate)
            }
        }
    }
}