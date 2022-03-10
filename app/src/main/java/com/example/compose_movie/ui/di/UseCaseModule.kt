package com.example.compose_movie.ui.di

import com.example.compose_movie.domain.repository.MovieRepository
import com.example.compose_movie.domain.usecase.GetPopularMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun providesGetPopularMovieUseCase(
        movieRepository: MovieRepository
    ): GetPopularMovieUseCase = GetPopularMovieUseCase(
        movieRepository
    )
}