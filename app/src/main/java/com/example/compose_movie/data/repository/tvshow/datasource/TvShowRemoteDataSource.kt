package com.example.compose_movie.data.repository.tvshow.datasource

import com.example.compose_movie.data.model.web.tvshow.TvShowResponse
import retrofit2.Response

interface TvShowRemoteDataSource {

    suspend fun getPopularTvShow(page : Int) : Response<TvShowResponse>
    suspend fun getTopRatedTvShow(page : Int) : Response<TvShowResponse>
    suspend fun getSearchedTvShow(page : Int, query : String) : Response<TvShowResponse>
}