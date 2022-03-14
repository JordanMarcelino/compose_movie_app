package com.example.compose_movie.ui.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_movie.data.model.domain.TvShow
import com.example.compose_movie.data.model.web.tvshow.Result
import com.example.compose_movie.data.util.Resource
import com.example.compose_movie.domain.usecase.*
import com.example.compose_movie.ui.App
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.net.URLEncoder
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(
    private val app: Application,
    private val getPopularTvShowUseCase: GetPopularTvShowUseCase,
    private val getTopRatedTvShowUseCase: GetTopRatedTvShowUseCase,
    private val saveTvShowUseCase: SaveTvShowUseCase,
    private val deleteTvShowUseCase: DeleteTvShowUseCase,
    private val getSavedTvShowUseCase: GetSavedTvShowUseCase,
    private val getSearchedTvShowUseCase: GetSearchedTvShowUseCase
) : AndroidViewModel(app) {

    private val _popularTvShow = mutableStateOf<List<Result>>(listOf())
    val popularTvShow = _popularTvShow

    private val _topRated = mutableStateOf<List<Result>>(listOf())
    val topRated = _topRated

    private val _searched = mutableStateOf<List<Result>>(listOf())
    private val _searchedCached = mutableStateOf<List<Result>>(listOf())
    val _query = mutableStateOf("")
    val searched = _searchedCached

    var currentState = mutableStateOf<Resource<Any>>(Resource.Loading())
    var currentStateTopRated = mutableStateOf<Resource<Any>>(Resource.Loading())
    var currentStateSearched = mutableStateOf<Resource<Any>>(Resource.Loading())

    var endReached = mutableStateOf(false)
    var endReachedTopRated = mutableStateOf(false)
    var endReachedSearched = mutableStateOf(false)

    private var page = 1
    private var pageTopRated = 1
    private var pageSearched = 1

    fun loadSearchedCachedTvShow() = viewModelScope.launch(Dispatchers.IO) {
        try {
            if (isNetworkAvailable()) {
                when (val response = getSearchedTvShowUseCase.execute(pageSearched, URLEncoder.encode(_query.value, "utf-8"))) {
                    is Resource.Success -> {
                        endReachedSearched.value = pageSearched * 20 >= response.data!!.totalResults!!
                        response.data.results?.forEach {
                            it.apply {
                                posterPath = "https://image.tmdb.org/t/p/w500" + it.posterPath
                            }
                        }
                        _searchedCached.value += response.data.results!!
                        currentStateSearched.value = Resource.Success("Success")
                        pageSearched++
                    }
                    is Resource.Error -> {
                        currentStateSearched.value = Resource.Error(response.message.toString())
                    }
                    else -> {
                        currentStateSearched.value = Resource.Error(response.message.toString())
                    }
                }
            } else {
                currentStateSearched.value = Resource.Error("Internet is not available")
            }
        } catch (e: Exception) {
            currentStateSearched.value = Resource.Error(e.message.toString())
        }
    }

    fun loadSearchedTvShow(query: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            if (isNetworkAvailable()) {
                when (val response = getSearchedTvShowUseCase.execute(pageSearched, URLEncoder.encode(query, "utf-8"))) {
                    is Resource.Success -> {
                        endReachedSearched.value = pageSearched * 20 >= response.data!!.totalResults!!
                        _query.value = query
                        response.data.results?.forEach {
                            it.apply {
                                posterPath = "https://image.tmdb.org/t/p/w500" + it.posterPath
                            }
                        }
                        _searched.value = response.data.results!!
                        _searchedCached.value = _searched.value
                        currentStateSearched.value = Resource.Success("Success")
                        pageSearched = 1
                    }
                    is Resource.Error -> {
                        currentStateSearched.value = Resource.Error(response.message.toString())
                    }
                    else -> {
                        currentStateSearched.value = Resource.Error(response.message.toString())
                    }
                }
            } else {
                currentStateSearched.value = Resource.Error("Internet is not available")
            }
        } catch (e: Exception) {
            currentStateSearched.value = Resource.Error(e.message.toString())
        }
    }

    fun loadPopularTvShow() = viewModelScope.launch(Dispatchers.IO) {
        try {
            if (isNetworkAvailable()) {
                when (val response = getPopularTvShowUseCase.execute(page)) {
                    is Resource.Success -> {
                        endReached.value = page * 20 >= response.data!!.totalResults!!
                        response.data.results?.forEach {
                            it.apply {
                                posterPath = "https://image.tmdb.org/t/p/w500" + it.posterPath
                            }
                        }
                        _popularTvShow.value += response.data.results!!
                        currentState.value = Resource.Success("Success")
                        page++
                    }
                    is Resource.Error -> {
                        currentState.value = Resource.Error(response.message.toString())
                    }
                    else -> {
                        currentState.value = Resource.Error(response.message.toString())
                    }
                }
            } else {
                currentState.value = Resource.Error("Internet is not available")
            }
        } catch (e: Exception) {
            currentState.value = Resource.Error(e.message.toString())
        }
    }

    fun loadTopRatedTvShow() = viewModelScope.launch(Dispatchers.IO) {
        try {
            if (isNetworkAvailable()) {
                when (val response = getTopRatedTvShowUseCase.execute(pageTopRated)) {
                    is Resource.Success -> {
                        endReachedTopRated.value =
                            pageTopRated * 20 >= response.data!!.totalResults!!
                        response.data.results?.forEach {
                            it.apply {
                                posterPath = "https://image.tmdb.org/t/p/w500" + it.posterPath
                            }
                        }
                        _topRated.value += response.data.results!!
                        currentStateTopRated.value = Resource.Success("Success")
                        pageTopRated++
                    }
                    is Resource.Error -> {
                        currentStateTopRated.value = Resource.Error(response.message.toString())
                    }
                    else -> {
                        currentStateTopRated.value = Resource.Error(response.message.toString())
                    }
                }
            } else {
                currentStateTopRated.value = Resource.Error("Internet is not available")
            }
        } catch (e: Exception) {
            currentStateTopRated.value = Resource.Error(e.message.toString())
        }
    }

    fun saveTvShow(tvShow: TvShow) = viewModelScope.launch(Dispatchers.IO) {
        saveTvShowUseCase.execute(tvShow)
    }

    fun deleteTvShow(tvShow: TvShow) = viewModelScope.launch(Dispatchers.IO) {
        deleteTvShowUseCase.execute(tvShow)
    }

    fun getSavedTvShow() = flow {
        getSavedTvShowUseCase.execute().collect {
            emit(it)
        }
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