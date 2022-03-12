package com.example.compose_movie.ui.component.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.compose_movie.ui.component.MovieSection
import com.example.compose_movie.ui.viewmodel.MovieViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    movieViewModel: MovieViewModel = hiltViewModel()
) {

    var index by remember {
        mutableStateOf(0)
    }

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            TabSection(
                navController = navController,
            ) {
                index = it
            }
            when (index) {
                0 -> MovieSection(
                    navController = navController,
                )
            }
        }
    }
}

@Composable
fun TabSection(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onTabSelected: (index: Int) -> Unit
) {
    var indexSelected by remember {
        mutableStateOf(0)
    }

    TabRow(
        selectedTabIndex = indexSelected,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 8.dp,
                vertical = 8.dp
            ),
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        Tab(
            selected = indexSelected == 0,
            onClick = {
                indexSelected = 0
                onTabSelected(0)
            },
            modifier = Modifier.padding(
                bottom = 10.dp
            )
        ) {
            Text(
                text = "Movies",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Tab(
            selected = indexSelected == 1,
            onClick = {
                indexSelected = 1
                onTabSelected(1)
            }
        ) {
            Text(
                text = "TV Show",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Tab(
            selected = indexSelected == 2,
            onClick = {
                indexSelected = 2
                onTabSelected(2)
            },
        ) {
            Text(
                text = "Artist",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

