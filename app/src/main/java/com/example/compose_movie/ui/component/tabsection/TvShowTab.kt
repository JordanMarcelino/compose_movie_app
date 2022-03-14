package com.example.compose_movie.ui.component.tabsection

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.compose_movie.ui.component.details.tvshow.FavoriteTvShow
import com.example.compose_movie.ui.component.details.tvshow.TopRatedTvShowSection
import com.example.compose_movie.ui.component.details.tvshow.TvShowSection

@Composable
fun ColumnScope.TvShowTab(
    navController: NavHostController,
) {
    TvShowSection(navController = navController)
    Spacer(modifier = Modifier.height(32.dp))
    Text(
        text = "Top Rated Tv Show",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .align(Alignment.Start),
        color = Color.White
    )
    Spacer(modifier = Modifier.height(8.dp))
    TopRatedTvShowSection(navController = navController)
    Spacer(modifier = Modifier.height(32.dp))
    Text(
        text = "My Favorites",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .align(Alignment.Start),
        color = Color.White
    )
    Spacer(modifier = Modifier.height(8.dp))
    FavoriteTvShow(navController = navController)
    Spacer(modifier = Modifier.height(100.dp))
}