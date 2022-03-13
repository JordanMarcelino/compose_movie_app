package com.example.compose_movie.domain.usecase

import com.example.compose_movie.domain.repository.TvShowRepository

class GetTopRatedTvShowUseCase (private val tvShowRepository: TvShowRepository) {
    suspend fun execute(page : Int) = tvShowRepository.getTopRatedTvShow(page)
}