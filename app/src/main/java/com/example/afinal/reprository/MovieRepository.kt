package com.example.afinal.reprository

import com.example.afinal.api.RetrofitBuilder
import com.example.afinal.database.MovieDatabase
import com.example.afinal.models.Movie
import com.example.afinal.models.MovieResponse
import retrofit2.Response

class MovieRepository( val database: MovieDatabase){

    suspend fun getPopularMovie(pageNum : Int) =
         RetrofitBuilder.api.getPopularMovies(page = pageNum)

    suspend fun getTopRatedMovie(pageNum : Int) =
         RetrofitBuilder.api.getTopRatedMovies(page = pageNum)

    suspend fun getUpComingrMovie(pageNum : Int) =
    RetrofitBuilder.api.getUpcomingMovies(page = pageNum)

    suspend fun addMovietoDb(movie: Movie)= database.MovieDao().insert(movie)

   suspend fun deletMovie(movie: Movie) = database.MovieDao().deleteMovie(movie)

    fun getFavorite() =database.MovieDao().getMovies()





}