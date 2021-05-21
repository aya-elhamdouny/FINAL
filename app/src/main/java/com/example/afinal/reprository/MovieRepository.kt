package com.example.afinal.reprository

import com.example.afinal.api.RetrofitBuilder
import com.example.afinal.database.MovieDatabase
import com.example.afinal.models.MovieResponse
import retrofit2.Response

class MovieRepository( val database: MovieDatabase){

    suspend fun getPopularMovie(pageNum : Int) : Response<MovieResponse>{
        return RetrofitBuilder.api.getPopularMovies(page = pageNum)
    }


    suspend fun getTopRatedMovie(pageNum : Int) : Response<MovieResponse>{
        return RetrofitBuilder.api.getTopRatedMovies(page = pageNum)
    }


    suspend fun getUpComingrMovie(pageNum : Int) : Response<MovieResponse>{
        return RetrofitBuilder.api.getUpcomingMovies(page = pageNum)
    }



}