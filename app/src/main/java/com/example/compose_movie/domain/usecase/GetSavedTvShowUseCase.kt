package com.example.compose_movie.domain.usecase

import com.example.compose_movie.data.model.domain.TvShow
import com.example.compose_movie.domain.repository.TvShowRepository

class GetSavedTvShowUseCase (private val tvShowRepository: TvShowRepository) {
    fun execute() = tvShowRepository.getSavedTvShow()
}