package com.example.compose_movie.data.repository.tvshow

import com.example.compose_movie.data.model.domain.TvShow
import com.example.compose_movie.data.model.web.tvshow.TvShowResponse
import com.example.compose_movie.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.example.compose_movie.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.example.compose_movie.data.util.Resource
import com.example.compose_movie.domain.repository.TvShowRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class TvShowRepositoryImpl(
    private val remoteDataSource: TvShowRemoteDataSource,
    private val localDataSource: TvShowLocalDataSource
) : TvShowRepository {

    override suspend fun getPopularTvShow(page: Int): Resource<TvShowResponse> =
        responseToResource(remoteDataSource.getPopularTvShow(page))

    override suspend fun getTopRatedTvShow(page: Int): Resource<TvShowResponse> = responseToResource(remoteDataSource.getTopRatedTvShow(page))

    override suspend fun saveTvShow(tvShow: TvShow) = localDataSource.saveTvShow(tvShow)

    override suspend fun deleteTvShow(tvShow: TvShow) = localDataSource.deleteTvShow(tvShow)

    override fun getSavedTvShow(): Flow<List<TvShow>> = localDataSource.getSavedTvShow()

    private fun responseToResource(response: Response<TvShowResponse>): Resource<TvShowResponse> {
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