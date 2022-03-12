package com.example.compose_movie.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.compose_movie.data.model.Result
import com.example.compose_movie.ui.component.navgraph.BottomNavigationView
import java.net.URLDecoder
import java.net.URLEncoder

@Composable
fun MovieDetails(
    navController: NavHostController,
    url: String,
    title: String,
    rate: String,
    date: String,
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
            MovieImage(
                url = url,
                title = title,
                rate = rate,
                date = date,
                adult = adult
            )
            TopSection(
                navController,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(16.dp, 16.dp)
            )
        }
    }

}

@Composable
fun TopSection(
    navController: NavController,
    modifier: Modifier = Modifier
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
            modifier = Modifier.fillMaxSize(),
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