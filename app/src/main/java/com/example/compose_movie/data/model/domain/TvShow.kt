package com.example.compose_movie.data.model.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show_table")
data class TvShow(
    @PrimaryKey
    val id : Int,
    val url: String,
    val title: String,
    val rate: String,
    val date: String,
    val overview: String,
)
