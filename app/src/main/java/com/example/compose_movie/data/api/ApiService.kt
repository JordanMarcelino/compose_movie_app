package com.example.compose_movie.data.api

import com.example.compose_movie.BuildConfig
import com.example.compose_movie.data.model.web.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("page")
        page : Int,
        @Query("api_key")
        apiKey : String = BuildConfig.API_KEY,
    ) : Response<MovieResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page")
        page : Int,
        @Query("api_key")
        apiKey : String = BuildConfig.API_KEY,
    ) : Response<MovieResponse>
}