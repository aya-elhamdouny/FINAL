package com.example.afinal.models

import java.io.Serializable

data class MovieResponse (

    val id: String,

    val title: String,

    val poster: String?,

    val overview: String,

    val backdropPath: String,

    val rating: String,

    val release: String?): Serializable



