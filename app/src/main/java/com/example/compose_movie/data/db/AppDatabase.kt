package com.example.compose_movie.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.compose_movie.data.model.domain.Movie
import com.example.compose_movie.data.model.domain.TvShow

@Database(
    entities = [Movie::class, TvShow::class],
    exportSchema = false,
    version = 2
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getMovieDao() : MovieDao
    abstract fun getTvShowDao() : TvShowDao
}