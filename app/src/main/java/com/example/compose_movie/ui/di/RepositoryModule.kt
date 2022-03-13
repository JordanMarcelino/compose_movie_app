package com.example.compose_movie.ui.di

import com.example.compose_movie.data.repository.movie.MovieRepositoryImpl
import com.example.compose_movie.data.repository.movie.datasource.MovieLocalDataSource
import com.example.compose_movie.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.compose_movie.data.repository.tvshow.TvShowRepositoryImpl
import com.example.compose_movie.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.example.compose_movie.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.example.compose_movie.domain.repository.MovieRepository
import com.example.compose_movie.domain.repository.TvShowRepository
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
        remoteDataSource: MovieRemoteDataSource,
        localDataSource: MovieLocalDataSource
    ): MovieRepository = MovieRepositoryImpl(
        remoteDataSource,
        localDataSource
    )

    @Singleton
    @Provides
    fun providesTvShowRepository(
        remoteDataSource: TvShowRemoteDataSource,
        localDataSource: TvShowLocalDataSource
    ): TvShowRepository = TvShowRepositoryImpl(
        remoteDataSource,
        localDataSource
    )
}