package com.example.compose_movie.domain.repository

import com.example.compose_movie.data.model.domain.Movie
import com.example.compose_movie.data.model.web.MovieResponse
import com.example.compose_movie.data.model.web.Result
import com.example.compose_movie.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopularMovie(page : Int) : Resource<MovieResponse>
    suspend fun getNowPlaying(page : Int) : Resource<MovieResponse>
    suspend fun saveMovie(movie: Movie)
    suspend fun deleteMovie(movie: Movie)
    fun getSavedMovie() : Flow<List<Movie>>
}