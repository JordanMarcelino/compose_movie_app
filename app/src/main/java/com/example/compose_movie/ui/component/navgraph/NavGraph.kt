package com.example.compose_movie.ui.component.navgraph

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.compose_movie.ui.component.details.movie.*
import com.example.compose_movie.ui.component.details.tvshow.PopularTvShowRow
import com.example.compose_movie.ui.component.details.tvshow.SavedTvShowRow
import com.example.compose_movie.ui.component.details.tvshow.TopRatedTvShowRow
import com.example.compose_movie.ui.component.details.tvshow.TvShowDetails

fun NavGraphBuilder.searchedMovie(
    navController: NavHostController
){
    composable(
        route = "${Screen.MovieSearched.navigation}/{title}/{id}/{overview}/{rate}/{date}/{adult}/{url}",
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
            searched = true,
            movieRow = {

            },
            adult = adult
        )
    }
}

fun NavGraphBuilder.searchedTvShow(
    navController: NavHostController
){
    composable(
        route = "${Screen.TvShowSearched.navigation}/{title}/{id}/{overview}/{rate}/{date}/{url}",
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
        val overview = remember {
            bundle.getString("overview")
        }
        val id = remember {
            bundle.getInt("id")
        }
        TvShowDetails(
            navController = navController,
            url = url.toString(),
            title = title.toString(),
            rate = rate.toString(),
            date = date.toString(),
            overview = overview.toString(),
            id = id,
            tvShowRow = {

            },
            searched = true,
        )
    }
}

fun NavGraphBuilder.topRatedTvShow(
    navController: NavHostController
){
    composable(
        route = "${Screen.TvShowTopRatedDetail.navigation}/{title}/{id}/{overview}/{rate}/{date}/{url}",
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
        val overview = remember {
            bundle.getString("overview")
        }
        val id = remember {
            bundle.getInt("id")
        }
        TvShowDetails(
            navController = navController,
            url = url.toString(),
            title = title.toString(),
            rate = rate.toString(),
            date = date.toString(),
            overview = overview.toString(),
            id = id,
            tvShowRow = {
                TopRatedTvShowRow(
                    navController = navController,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            },
            searched = false,
        )
    }
}

fun NavGraphBuilder.savedTvShow(
    navController: NavHostController
){
    composable(
        route = "${Screen.TvShowSavedDetail.navigation}/{title}/{id}/{overview}/{rate}/{date}/{url}",
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
        val overview = remember {
            bundle.getString("overview")
        }
        val id = remember {
            bundle.getInt("id")
        }
        TvShowDetails(
            navController = navController,
            url = url.toString(),
            title = title.toString(),
            rate = rate.toString(),
            date = date.toString(),
            overview = overview.toString(),
            id = id,
            tvShowRow = {
                SavedTvShowRow(
                    navController = navController,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            },
            searched = false,
        )
    }
}

fun NavGraphBuilder.tvShowDetail(
    navController: NavHostController
){
    composable(
        route = "${Screen.TvShowDetail.navigation}/{title}/{id}/{overview}/{rate}/{date}/{url}",
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
        val overview = remember {
            bundle.getString("overview")
        }
        val id = remember {
            bundle.getInt("id")
        }
        TvShowDetails(
            navController = navController,
            url = url.toString(),
            title = title.toString(),
            rate = rate.toString(),
            date = date.toString(),
            overview = overview.toString(),
            id = id,
            tvShowRow = {
                PopularTvShowRow(
                    navController = navController,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            },
            searched = false,
        )
    }
}

fun NavGraphBuilder.savedMovie(
    navController: NavHostController
){
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
            adult = adult,
            searched = false,
        )
    }
}

fun NavGraphBuilder.nowPlayingMovie(
    navController: NavHostController
){
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
            adult = adult,
            searched = false,
        )
    }
}

fun NavGraphBuilder.movieDetail(
    navController: NavHostController
){
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
            adult = adult,
            searched = false,
        )
    }
}