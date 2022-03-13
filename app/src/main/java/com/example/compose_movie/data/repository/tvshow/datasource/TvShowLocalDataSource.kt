package com.example.compose_movie.data.repository.tvshow.datasource

import com.example.compose_movie.data.model.domain.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowLocalDataSource {

    suspend fun saveTvShow(tvShow: TvShow)
    suspend fun deleteTvShow(tvShow: TvShow)
    fun getSavedTvShow() : Flow<List<TvShow>>

}