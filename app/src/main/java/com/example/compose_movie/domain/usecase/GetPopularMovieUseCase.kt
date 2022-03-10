package com.example.compose_movie.domain.usecase

import com.example.compose_movie.domain.repository.MovieRepository

class GetPopularMovieUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(page : Int) = movieRepository.getPopularMovie(page)
}