package com.example.compose_movie.data.repository.tvshow.datasourceimpl

import com.example.compose_movie.data.api.ApiService
import com.example.compose_movie.data.model.web.movie.MovieResponse
import com.example.compose_movie.data.model.web.tvshow.TvShowResponse
import com.example.compose_movie.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.compose_movie.data.repository.tvshow.datasource.TvShowRemoteDataSource
import retrofit2.Response

class TvShowRemoteDataSourceImpl(
    private val apiService: ApiService
) : TvShowRemoteDataSource {

    override suspend fun getPopularTvShow(page: Int): Response<TvShowResponse> = apiService.getPopularTvShow(page)

    override suspend fun getTopRatedTvShow(page: Int): Response<TvShowResponse> = apiService.getTopRatedTvShow(page)

    override suspend fun getSearchedTvShow(page: Int, query: String): Response<TvShowResponse> = apiService.getSearchedTvShow(page, query)
}