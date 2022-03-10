package com.example.compose_movie.domain.repository

import com.example.compose_movie.data.model.MovieResponse
import com.example.compose_movie.data.util.Resource

interface MovieRepository {

    suspend fun getPopularMovie(page : Int) : Resource<MovieResponse>
}