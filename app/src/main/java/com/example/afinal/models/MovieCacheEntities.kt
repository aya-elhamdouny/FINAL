package com.example.afinal.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.afinal.domain.Model
import com.google.gson.annotations.SerializedName
import java.io.Serializable



@Entity(tableName = "cacheMovie")
data class MovieCacheEntitiesEntity(
    val backdrop_path: String,
    @PrimaryKey
    val id: Int,
    val overview: String,
    @SerializedName("poster_path")
    val poster: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val isFav: Boolean
): Serializable

fun List<Movie>.asDomainModel(): List<Model> {
    return map {
        Model(
            title = it.title,
            overview = it.overview,
            poster = it.poster,
            release_date = it.release_date,
            backdrop_path = it.backdrop_path,
            video = it.video,
            id = it.id,
            isFav = it.isFav
        )
    }
}