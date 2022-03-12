package com.example.compose_movie.ui.di

import com.example.compose_movie.data.db.MovieDao
import com.example.compose_movie.data.repository.datasource.MovieLocalDataSource
import com.example.compose_movie.data.repository.datasourceimpl.MovieLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Singleton
    @Provides
    fun providesMovieLocalDataSource(
        movieDao: MovieDao
    ) : MovieLocalDataSource = MovieLocalDataSourceImpl(
        movieDao
    )
}