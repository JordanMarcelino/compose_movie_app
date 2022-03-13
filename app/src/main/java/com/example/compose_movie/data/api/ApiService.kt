package com.example.compose_movie.data.api

import com.example.compose_movie.BuildConfig
import com.example.compose_movie.data.model.web.movie.MovieResponse
import com.example.compose_movie.data.model.web.tvshow.TvShowResponse
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

    @GET("tv/popular")
    suspend fun getPopularTvShow(
        @Query("page")
        page : Int,
        @Query("api_key")
        apiKey : String = BuildConfig.API_KEY,
    ) : Response<TvShowResponse>

    @GET("tv/top_rated")
    suspend fun getTopRatedTvShow(
        @Query("page")
        page : Int,
        @Query("api_key")
        apiKey : String = BuildConfig.API_KEY,
    ) : Response<TvShowResponse>
}