package com.example.afinal.domain

import com.google.gson.annotations.SerializedName

data class Model(
    val backdrop_path: String,
    val id: Int,
    val overview: String,
    @SerializedName("poster_path")
    val poster: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val isFav: Int
        )