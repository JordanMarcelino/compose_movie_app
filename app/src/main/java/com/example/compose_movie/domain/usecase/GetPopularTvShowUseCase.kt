package com.example.compose_movie.domain.usecase

import com.example.compose_movie.domain.repository.TvShowRepository

class GetPopularTvShowUseCase (private val tvShowRepository: TvShowRepository) {
    suspend fun execute(page : Int) = tvShowRepository.getPopularTvShow(page)
}