package com.example.compose_movie.data.repository.datasource

import com.example.compose_movie.data.model.MovieResponse
import retrofit2.Response

interface MovieRemoteDataSource {

    suspend fun getPopularMovie(page : Int) : Response<MovieResponse>
}