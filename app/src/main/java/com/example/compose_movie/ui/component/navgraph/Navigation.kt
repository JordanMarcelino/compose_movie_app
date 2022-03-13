package com.example.compose_movie.ui.component.navgraph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_movie.ui.component.*
import com.example.compose_movie.ui.component.details.MovieDetails
import com.example.compose_movie.ui.component.details.NowPlayingMovieRow
import com.example.compose_movie.ui.component.details.PopularMovieRow
import com.example.compose_movie.ui.component.details.SavedMovieRow
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
                FavouriteScreen()
            }
            composable(
                route = "${Screen.MovieDetail.navigation}/{title}/{id}/{overview}/{rate}/{date}/{adult}/{url}",
                arguments = listOf(
                    navArgument("url") {
                        type = NavType.StringType
                    },
                    navArgument("title") {
                        type = NavType.StringType
                    },
                    navArgument("rate") {
                        type = NavType.StringType
                    },
                    navArgument("date") {
                        type = NavType.StringType
                    },
                    navArgument("adult") {
                        type = NavType.BoolType
                    },
                    navArgument("overview") {
                        type = NavType.StringType
                    },
                    navArgument("id") {
                        type = NavType.IntType
                    }
                )
            ) {
                val bundle = it.arguments!!
                val url = remember {
                    bundle.getString("url")
                }
                val title = remember {
                    bundle.getString("title")
                }
                val rate = remember {
                    bundle.getString("rate")
                }
                val date = remember {
                    bundle.getString("date")
                }
                val adult = remember {
                    bundle.getBoolean("adult")
                }
                val overview = remember {
                    bundle.getString("overview")
                }
                val id = remember {
                    bundle.getInt("id")
                }
                MovieDetails(
                    navController = navController,
                    url = url.toString(),
                    title = title.toString(),
                    rate = rate.toString(),
                    date = date.toString(),
                    overview = overview.toString(),
                    id = id,
                    movieRow = {
                        PopularMovieRow(
                            navController = navController,
                            Modifier.padding(horizontal = 8.dp)
                        )
                    },
                    adult = adult
                )
            }
            composable(
                route = "${Screen.MovieNowPlayingDetail.navigation}/{title}/{id}/{overview}/{rate}/{date}/{adult}/{url}",
                arguments = listOf(
                    navArgument("url") {
                        type = NavType.StringType
                    },
                    navArgument("title") {
                        type = NavType.StringType
                    },
                    navArgument("rate") {
                        type = NavType.StringType
                    },
                    navArgument("date") {
                        type = NavType.StringType
                    },
                    navArgument("adult") {
                        type = NavType.BoolType
                    },
                    navArgument("overview") {
                        type = NavType.StringType
                    },
                    navArgument("id") {
                        type = NavType.IntType
                    }
                )
            ) {
                val bundle = it.arguments!!
                val url = remember {
                    bundle.getString("url")
                }
                val title = remember {
                    bundle.getString("title")
                }
                val rate = remember {
                    bundle.getString("rate")
                }
                val date = remember {
                    bundle.getString("date")
                }
                val adult = remember {
                    bundle.getBoolean("adult")
                }
                val overview = remember {
                    bundle.getString("overview")
                }
                val id = remember {
                    bundle.getInt("id")
                }
                MovieDetails(
                    navController = navController,
                    url = url.toString(),
                    title = title.toString(),
                    rate = rate.toString(),
                    date = date.toString(),
                    overview = overview.toString(),
                    id = id,
                    movieRow = {
                        NowPlayingMovieRow(
                            navController = navController,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    },
                    adult = adult
                )
            }
            composable(
                route = "${Screen.MovieSavedDetail.navigation}/{title}/{id}/{overview}/{rate}/{date}/{adult}/{url}",
                arguments = listOf(
                    navArgument("url") {
                        type = NavType.StringType
                    },
                    navArgument("title") {
                        type = NavType.StringType
                    },
                    navArgument("rate") {
                        type = NavType.StringType
                    },
                    navArgument("date") {
                        type = NavType.StringType
                    },
                    navArgument("adult") {
                        type = NavType.BoolType
                    },
                    navArgument("overview") {
                        type = NavType.StringType
                    },
                    navArgument("id") {
                        type = NavType.IntType
                    }
                )
            ) {
                val bundle = it.arguments!!
                val url = remember {
                    bundle.getString("url")
                }
                val title = remember {
                    bundle.getString("title")
                }
                val rate = remember {
                    bundle.getString("rate")
                }
                val date = remember {
                    bundle.getString("date")
                }
                val adult = remember {
                    bundle.getBoolean("adult")
                }
                val overview = remember {
                    bundle.getString("overview")
                }
                val id = remember {
                    bundle.getInt("id")
                }
                MovieDetails(
                    navController = navController,
                    url = url.toString(),
                    title = title.toString(),
                    rate = rate.toString(),
                    date = date.toString(),
                    overview = overview.toString(),
                    id = id,
                    movieRow = {
                        SavedMovieRow(
                            navController = navController,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    },
                    adult = adult
                )
            }
        }

    }
}
