package com.example.compose_movie.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationView(navController = navController) {
                navController.navigate(it.destination.navigation)
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = NavigateTo.Home().navigation,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(
                route = NavigateTo.Home().navigation,
            ) {
                HomeScreen()
            }
            composable(
                route = NavigateTo.Search().navigation,
            ) {
                SearchScreen()
            }
            composable(
                route = NavigateTo.Favourite().navigation,
            ) {
                FavouriteScreen()
            }
        }
    }
}