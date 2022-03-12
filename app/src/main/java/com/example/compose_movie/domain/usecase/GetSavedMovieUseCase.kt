package com.example.compose_movie.domain.usecase

import com.example.compose_movie.domain.repository.MovieRepository

class GetSavedMovieUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute() = movieRepository.getSavedMovie()
}