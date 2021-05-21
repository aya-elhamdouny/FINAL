package com.example.afinal.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "Movie")
data class Movie(
    @PrimaryKey
    val id: String,

    val title: String,

    val poster: String?,

    val overview: String,

    val backdropPath: String,

    val rating: String,

    val release: String?,

    val isFav : Boolean
): Serializable