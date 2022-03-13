package com.example.compose_movie.data.db

import androidx.room.*
import com.example.compose_movie.data.model.domain.TvShow
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTvShow(tvShow: TvShow)

    @Delete
    suspend fun deleteTvShow(tvShow: TvShow)

    @Query("SELECT * FROM tv_show_table")
    fun getSavedTvShow () : Flow<List<TvShow>>
}