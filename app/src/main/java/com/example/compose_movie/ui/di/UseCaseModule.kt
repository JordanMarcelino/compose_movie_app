package com.example.compose_movie.ui.di

import com.example.compose_movie.domain.repository.MovieRepository
import com.example.compose_movie.domain.repository.TvShowRepository
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

    @Singleton
    @Provides
    fun providesGetPopularTvShowUseCase(
        tvShowRepository: TvShowRepository
    ): GetPopularTvShowUseCase = GetPopularTvShowUseCase(
        tvShowRepository
    )

    @Singleton
    @Provides
    fun providesGetTopRatedTvShowUseCase(
        tvShowRepository: TvShowRepository
    ): GetTopRatedTvShowUseCase = GetTopRatedTvShowUseCase(
        tvShowRepository
    )

    @Singleton
    @Provides
    fun providesSaveTvShowUseCase(
        tvShowRepository: TvShowRepository
    ): SaveTvShowUseCase = SaveTvShowUseCase(
        tvShowRepository
    )

    @Singleton
    @Provides
    fun providesDeleteTvShowUseCase(
        tvShowRepository: TvShowRepository
    ): DeleteTvShowUseCase = DeleteTvShowUseCase(
        tvShowRepository
    )

    @Singleton
    @Provides
    fun providesGetSavedTvShowUseCase(
        tvShowRepository: TvShowRepository
    ): GetSavedTvShowUseCase = GetSavedTvShowUseCase(
        tvShowRepository
    )

    @Singleton
    @Provides
    fun providesGetSearchedTvShowUseCase(
        tvShowRepository: TvShowRepository
    ): GetSearchedTvShowUseCase = GetSearchedTvShowUseCase(
        tvShowRepository
    )

    @Singleton
    @Provides
    fun providesGetSearchedMovieUseCase(
        movieRepository : MovieRepository
    ): GetSearchedMovieUseCase = GetSearchedMovieUseCase(
        movieRepository
    )

}
