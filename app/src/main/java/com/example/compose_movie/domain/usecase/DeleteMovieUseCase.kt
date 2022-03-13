package com.example.compose_movie.domain.usecase

import com.example.compose_movie.data.model.domain.Movie
import com.example.compose_movie.domain.repository.MovieRepository

class DeleteMovieUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(movie: Movie) = movieRepository.deleteMovie(movie = movie)
}