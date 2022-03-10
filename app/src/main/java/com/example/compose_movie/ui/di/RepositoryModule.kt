package com.example.compose_movie.ui.di

import com.example.compose_movie.data.repository.MovieRepositoryImpl
import com.example.compose_movie.data.repository.datasource.MovieRemoteDataSource
import com.example.compose_movie.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesMovieRepository(
        remoteDataSource: MovieRemoteDataSource
    ): MovieRepository = MovieRepositoryImpl(
        remoteDataSource
    )
}