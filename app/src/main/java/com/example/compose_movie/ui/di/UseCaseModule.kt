package com.example.compose_movie.ui.di

import com.example.compose_movie.domain.repository.MovieRepository
import com.example.compose_movie.domain.usecase.*
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

    @Singleton
    @Provides
    fun providesSaveMovieUseCase(
        movieRepository: MovieRepository
    ): SaveMovieUseCase = SaveMovieUseCase(
        movieRepository
    )

    @Singleton
    @Provides
    fun providesDeleteMovieUseCase(
        movieRepository: MovieRepository
    ): DeleteMovieUseCase = DeleteMovieUseCase(
        movieRepository
    )

    @Singleton
    @Provides
    fun providesGetSavedMovieUseCase(
        movieRepository: MovieRepository
    ): GetSavedMovieUseCase = GetSavedMovieUseCase(
        movieRepository
    )

    @Singleton
    @Provides
    fun providesGetNowPlayingMovieUseCase(
        movieRepository: MovieRepository
    ): GetNowPlayingUseCase = GetNowPlayingUseCase(
        movieRepository
    )
}