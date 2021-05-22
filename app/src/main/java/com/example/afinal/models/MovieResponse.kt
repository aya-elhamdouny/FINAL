package com.example.afinal.models

import java.io.Serializable
data class MovieResponse(
    val page: Int,
    val movie: List<Movie>,
    val total_pages: Int,
    val total_results: Int
) :Serializable



