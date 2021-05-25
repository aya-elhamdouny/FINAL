package com.example.afinal.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.afinal.models.Movie
import org.jetbrains.annotations.NotNull

@Dao
interface MovieDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie : Movie)

    @Query("select * from Movie")
    fun getMovies() : LiveData<List<Movie>>


    @Delete
    suspend fun deleteMovie(movie: Movie)

}