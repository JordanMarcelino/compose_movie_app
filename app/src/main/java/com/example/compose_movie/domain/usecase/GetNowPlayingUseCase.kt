package com.example.compose_movie.domain.usecase

import com.example.compose_movie.domain.repository.MovieRepository

class GetNowPlayingUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(page : Int) = movieRepository.getNowPlaying(page)
}