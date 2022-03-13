package com.example.compose_movie.domain.repository

import com.example.compose_movie.data.model.domain.TvShow
import com.example.compose_movie.data.model.web.tvshow.TvShowResponse
import com.example.compose_movie.data.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface TvShowRepository {

    suspend fun getPopularTvShow(page : Int) : Resource<TvShowResponse>
    suspend fun getTopRatedTvShow(page : Int) : Resource<TvShowResponse>
    suspend fun saveTvShow(tvShow : TvShow)
    suspend fun deleteTvShow(tvShow : TvShow)
    fun getSavedTvShow() : Flow<List<TvShow>>

}