package com.example.compose_movie.ui.component.screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.compose_movie.ui.component.details.movie.SearchedBox
import com.example.compose_movie.ui.component.details.movie.SearchedMovie
import com.example.compose_movie.ui.component.details.tvshow.SearchedTvShow
import com.example.compose_movie.ui.component.details.tvshow.SearchedTvShowColumn
import com.example.compose_movie.ui.viewmodel.MovieViewModel
import com.example.compose_movie.ui.viewmodel.TvShowViewModel

@Composable
fun SearchScreen(
    navController: NavHostController,
    movieViewModel: MovieViewModel = hiltViewModel(),
    tvShowViewModel: TvShowViewModel= hiltViewModel()
) {

    var query by remember {
        mutableStateOf("")
    }

    val queryMovie by remember {
        movieViewModel._query
    }

    val queryTvShow by remember {
        tvShowViewModel._query
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
            if (index == 0) movieViewModel.loadSearchedMovie(it)
            else if (index == 1) tvShowViewModel.loadSearchedTvShow(it)
            query = it
        }
        TabSection(navController = navController) {
            index = it
        }
        Spacer(modifier = Modifier.height(10.dp))
        when (index) {
            0 -> {
                SearchedMovie(
                    navController = navController,
                    query = queryMovie
                )
                Spacer(modifier = Modifier.height(100.dp))
            }
            1 -> {
                SearchedTvShow(
                    navController = navController,
                    query = queryTvShow
                )
                Spacer(modifier = Modifier.height(100.dp))
            }
        }

    }
}

