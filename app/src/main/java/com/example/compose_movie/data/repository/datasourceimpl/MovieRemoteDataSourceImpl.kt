package com.example.compose_movie.data.repository.datasourceimpl

import com.example.compose_movie.data.api.ApiService
import com.example.compose_movie.data.model.web.MovieResponse
import com.example.compose_movie.data.repository.datasource.MovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val apiService: ApiService
) : MovieRemoteDataSource {

    override suspend fun getPopularMovie(page: Int): Response<MovieResponse> = apiService.getPopularMovie(page)
}