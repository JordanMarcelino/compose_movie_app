package com.example.compose_movie.ui.di

import android.app.Application
import androidx.room.Room
import com.example.compose_movie.data.db.AppDatabase
import com.example.compose_movie.data.db.MovieDao
import com.example.compose_movie.data.db.TvShowDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providesAppDatabase(application: Application): AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "app_database"
    ).fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun providesMovieDao(appDatabase: AppDatabase) : MovieDao = appDatabase.getMovieDao()

    @Singleton
    @Provides
    fun providesTvShowDao(appDatabase: AppDatabase) : TvShowDao = appDatabase.getTvShowDao()
}