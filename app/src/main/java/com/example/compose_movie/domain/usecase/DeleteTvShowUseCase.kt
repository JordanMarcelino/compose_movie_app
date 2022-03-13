package com.example.compose_movie.domain.usecase

import com.example.compose_movie.data.model.domain.TvShow
import com.example.compose_movie.domain.repository.TvShowRepository

class DeleteTvShowUseCase (private val tvShowRepository: TvShowRepository) {
    suspend fun execute(tvShow : TvShow) = tvShowRepository.deleteTvShow(tvShow)
}