package com.example.compose_movie.data.repository.datasource

import com.example.compose_movie.data.model.domain.Movie
import com.example.compose_movie.data.model.web.Result
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {

    suspend fun saveMovie(movie: Movie)
    suspend fun deleteMovie(movie: Movie)
    fun getSavedMovie() : Flow<List<Movie>>

}