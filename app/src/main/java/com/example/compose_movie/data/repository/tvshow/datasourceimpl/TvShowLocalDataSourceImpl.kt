package com.example.compose_movie.data.repository.tvshow.datasourceimpl

import com.example.compose_movie.data.db.TvShowDao
import com.example.compose_movie.data.model.domain.TvShow
import com.example.compose_movie.data.repository.tvshow.datasource.TvShowLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TvShowLocalDataSourceImpl(
    private val tvShowDao: TvShowDao
) : TvShowLocalDataSource {

    override suspend fun saveTvShow(tvShow: TvShow) {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.saveTvShow(tvShow)
        }
    }

    override suspend fun deleteTvShow(tvShow: TvShow) {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.deleteTvShow(tvShow)
        }
    }

    override fun getSavedTvShow() : Flow<List<TvShow>> = tvShowDao.getSavedTvShow()

}