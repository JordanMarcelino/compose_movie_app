package com.example.compose_movie.data.repository.datasourceimpl

import com.example.compose_movie.data.db.MovieDao
import com.example.compose_movie.data.model.domain.Movie
import com.example.compose_movie.data.model.web.Result
import com.example.compose_movie.data.repository.datasource.MovieLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MovieLocalDataSourceImpl(
    private val movieDao: MovieDao
) : MovieLocalDataSource {

    override suspend fun saveMovie(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.saveMovie(movie)
        }
    }

    override suspend fun deleteMovie(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.deleteMovie(movie)
        }
    }

    override fun getSavedMovie() : Flow<List<Movie>> = movieDao.getSavedMovie()

}