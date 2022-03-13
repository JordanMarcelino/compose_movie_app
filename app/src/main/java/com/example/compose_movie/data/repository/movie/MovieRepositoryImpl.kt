package com.example.compose_movie.data.repository.movie

import com.example.compose_movie.data.model.domain.Movie
import com.example.compose_movie.data.model.web.movie.MovieResponse
import com.example.compose_movie.data.repository.movie.datasource.MovieLocalDataSource
import com.example.compose_movie.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.compose_movie.data.util.Resource
import com.example.compose_movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
) : MovieRepository {

    override suspend fun getPopularMovie(page: Int): Resource<MovieResponse> =
        responseToResource(remoteDataSource.getPopularMovie(page))

    override suspend fun getNowPlaying(page: Int): Resource<MovieResponse> = responseToResource(remoteDataSource.getNowPlayingMovie(page))

    override suspend fun saveMovie(movie: Movie) = localDataSource.saveMovie(movie)

    override suspend fun deleteMovie(movie: Movie) = localDataSource.deleteMovie(movie)

    override fun getSavedMovie(): Flow<List<Movie>> = localDataSource.getSavedMovie()

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