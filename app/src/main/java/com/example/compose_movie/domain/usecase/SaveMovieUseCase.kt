package com.example.compose_movie.domain.usecase

import com.example.compose_movie.data.model.domain.Movie
import com.example.compose_movie.data.model.web.Result
import com.example.compose_movie.domain.repository.MovieRepository

class SaveMovieUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(movie: Movie) = movieRepository.saveMovie(movie = movie)
}