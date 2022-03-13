package com.example.compose_movie.data.repository.movie.datasourceimpl

import com.example.compose_movie.data.api.ApiService
import com.example.compose_movie.data.model.web.movie.MovieResponse
import com.example.compose_movie.data.repository.movie.datasource.MovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val apiService: ApiService
) : MovieRemoteDataSource {

    override suspend fun getPopularMovie(page: Int): Response<MovieResponse> = apiService.getPopularMovie(page)

    override suspend fun getNowPlayingMovie(page: Int): Response<MovieResponse> = apiService.getNowPlaying(page)
}