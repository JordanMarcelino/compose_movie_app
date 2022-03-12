package com.example.compose_movie.ui.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_movie.data.model.domain.Movie
import com.example.compose_movie.data.model.web.Result
import com.example.compose_movie.data.util.Resource
import com.example.compose_movie.domain.usecase.DeleteMovieUseCase
import com.example.compose_movie.domain.usecase.GetPopularMovieUseCase
import com.example.compose_movie.domain.usecase.GetSavedMovieUseCase
import com.example.compose_movie.domain.usecase.SaveMovieUseCase
import com.example.compose_movie.ui.App
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val app : Application,
    private val getPopularMovieUseCase: GetPopularMovieUseCase,
    private val saveMovieUseCase: SaveMovieUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase,
    private val getSavedMovieUseCase: GetSavedMovieUseCase
) : AndroidViewModel(app){

    private val movies = mutableStateOf<List<Result>>(listOf())
    val movie = movies

    var currentState = mutableStateOf<Resource<Any>>(Resource.Loading())
    var endReached = mutableStateOf(false)

    private var page = 1

    init {
        loadPopularMovie()
    }

    fun loadPopularMovie() = viewModelScope.launch {
        try {
            if (isNetworkAvailable()){
                when(val response = getPopularMovieUseCase.execute(page)){
                    is Resource.Success -> {
                        endReached.value = page * 20 >= response.data!!.totalResults!!
                        response.data.results?.forEach {
                            it.apply {
                                posterPath = "https://image.tmdb.org/t/p/w500" + it.posterPath
                            }
                        }
                        movies.value += response.data.results!!
                        currentState.value = Resource.Success("Success")
                        page++
                    }
                    is Resource.Error -> {
                        currentState.value = Resource.Error(response.message.toString())
                    }
                    else -> { currentState.value = Resource.Error(response.message.toString()) }
                }
            }else{
                currentState.value = Resource.Error("Internet is not available")
            }
        }catch (e : Exception){
            currentState.value = Resource.Error(e.message.toString())
        }
    }

    fun saveMovie(movie : Movie) = viewModelScope.launch {
        saveMovieUseCase.execute(movie)
    }

    fun deleteMovie(movie : Movie) = viewModelScope.launch {
        deleteMovieUseCase.execute(movie)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            (getApplication<App>().getSystemService(Context.CONNECTIVITY_SERVICE)) as ConnectivityManager
        val activeNetwork = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork ?: return false
        } else {
            TODO("VERSION.SDK_INT < M")
        }

        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}