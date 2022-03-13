package com.example.compose_movie.data.repository.datasource

import com.example.compose_movie.data.model.web.MovieResponse
import retrofit2.Response

interface MovieRemoteDataSource {

    suspend fun getPopularMovie(page : Int) : Response<MovieResponse>
    suspend fun getNowPlayingMovie(page : Int) : Response<MovieResponse>
}