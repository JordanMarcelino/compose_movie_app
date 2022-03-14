package com.example.compose_movie.domain.usecase

import com.example.compose_movie.domain.repository.TvShowRepository

class GetSearchedTvShowUseCase (private val tvShowRepository: TvShowRepository) {
    suspend fun execute(page : Int, query : String) = tvShowRepository.getSearchedTvShow(page, query)
}