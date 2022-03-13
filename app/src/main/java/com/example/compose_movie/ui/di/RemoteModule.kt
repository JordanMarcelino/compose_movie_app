package com.example.compose_movie.ui.di

import com.example.compose_movie.data.api.ApiService
import com.example.compose_movie.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.compose_movie.data.repository.movie.datasourceimpl.MovieRemoteDataSourceImpl
import com.example.compose_movie.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.example.compose_movie.data.repository.tvshow.datasourceimpl.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Singleton
    @Provides
    fun providesMovieRemoteDataSource(apiService: ApiService) : MovieRemoteDataSource = MovieRemoteDataSourceImpl(apiService)

    @Singleton
    @Provides
    fun providesTvShowRemoteDataSource(apiService: ApiService) : TvShowRemoteDataSource = TvShowRemoteDataSourceImpl(apiService)

}