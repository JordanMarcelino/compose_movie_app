package com.example.compose_movie.domain.usecase

import com.example.compose_movie.domain.repository.MovieRepository

class GetSearchedMovieUseCase (private val movieRepository: MovieRepository) {
    suspend fun execute(page : Int, query : String) = movieRepository.getSearchedMovie(page, query)
}