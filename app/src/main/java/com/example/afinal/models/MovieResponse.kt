package com.example.afinal.models

import androidx.lifecycle.MutableLiveData
import com.google.gson.annotations.SerializedName
import java.io.Serializable
data class MovieResponse(
    val page: Int,
    @SerializedName("results")
    val movie: MutableList<Movie>,
    val total_pages: Int,
    val total_results: Int
) :Serializable



