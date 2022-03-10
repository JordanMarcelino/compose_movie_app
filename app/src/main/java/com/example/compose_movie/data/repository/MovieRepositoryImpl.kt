package com.example.compose_movie.data.repository

import com.example.compose_movie.data.model.MovieResponse
import com.example.compose_movie.data.repository.datasource.MovieRemoteDataSource
import com.example.compose_movie.data.util.Resource
import com.example.compose_movie.domain.repository.MovieRepository
import retrofit2.Response

class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override suspend fun getPopularMovie(page: Int): Resource<MovieResponse> =
        responseToResource(remoteDataSource.getPopularMovie(page))

    private fun responseToResource(response: Response<MovieResponse>): Resource<MovieResponse> {
        try {
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            } else {
                return Resource.Error(response.message())
            }
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }

        return Resource.Error("Failed converting response")
    }

}