package com.example.compose_movie.ui.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import com.example.compose_movie.MainActivity
import com.example.compose_movie.domain.usecase.GetPopularMovieUseCase
import com.example.compose_movie.ui.App
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val app : Application,
    private val getPopularMovieUseCase: GetPopularMovieUseCase
) : AndroidViewModel(app){

    @RequiresApi(Build.VERSION_CODES.M)
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            (getApplication<App>().getSystemService(Context.CONNECTIVITY_SERVICE)) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false

        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}