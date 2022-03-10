package com.example.compose_movie.ui.di

import com.example.compose_movie.data.api.ApiService
import com.example.compose_movie.data.repository.datasource.MovieRemoteDataSource
import com.example.compose_movie.data.repository.datasourceimpl.MovieRemoteDataSourceImpl
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

}