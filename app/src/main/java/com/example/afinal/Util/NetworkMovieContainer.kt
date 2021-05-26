package com.example.afinal.Util

import com.example.afinal.domain.Model
import com.example.afinal.models.Movie

class NetworkMovieContainer(val movies: List<Movie>)

fun NetworkMovieContainer.asDomainModel(): List<Model> {
    return movies.map {
        Model(
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
fun NetworkMovieContainer.asDatabaseModel(): Array<Movie> {
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