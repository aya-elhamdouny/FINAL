package com.example.afinal.api

import com.example.afinal.models.Movie
import com.google.gson.annotations.SerializedName




class NetworkVideoContainer(val movies: List<NetworkMovie>) {

}

/**
 * Videos represent a devbyte that can be played.
 */
//@JsonClass(generateAdapter = true)
data class NetworkMovie(
    val backdrop_path: String,
    val id: Int,
    val overview: String,
    @SerializedName("poster_path")
    val poster: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val isFav: Boolean)

/**
 * Convert Network results to database objects
 */
fun NetworkVideoContainer.asDomainModel(): List<Movie> {
    return movies.map {
        Movie(
            title = it.title,
            overview = it.overview,
            poster = it.poster,
            release_date = it.release_date,
            backdrop_path = it.backdrop_path,
            video = it.video,
            id = it.id,
            isFav = it.isFav)
    }
}




fun NetworkVideoContainer.asDatabaseModel(): Array<Movie> {
    return movies.map {
        Movie(
            title = it.title,
            overview = it.overview,
            poster = it.poster,
            release_date = it.release_date,
            backdrop_path = it.backdrop_path,
            video = it.video,
            id = it.id,
            isFav = it.isFav)
    }.toTypedArray()
}