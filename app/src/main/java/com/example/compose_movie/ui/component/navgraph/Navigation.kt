package com.example.compose_movie.ui.component.navgraph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_movie.ui.component.screen.FavouriteScreen
import com.example.compose_movie.ui.component.SearchScreen
import com.example.compose_movie.ui.component.screen.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationView(navController = navController) {
                navController.navigate(it.destination.navigation) {
                    popUpTo(Screen.Home.navigation)
                    launchSingleTop = true
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.navigation,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(
                route = Screen.Home.navigation,
            ) {
                HomeScreen(navController)
            }
            composable(
                route = Screen.Search.navigation,
            ) {
                SearchScreen()
            }
            composable(
                route = Screen.Favourite.navigation,
            ) {
                FavouriteScreen(navController)
            }
            movieDetail(navController)
            nowPlayingMovie(navController)
            savedMovie(navController)
            tvShowDetail(navController)
            savedTvShow(navController)
            topRatedTvShow(navController)
        }

    }
}

