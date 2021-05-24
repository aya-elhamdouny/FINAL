package com.example.afinal.api

import com.example.afinal.models.Movie
import com.example.afinal.models.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {




    @GET("movie/popular")
    suspend  fun getPopularMovies(
        @Query("api_key") apiKey: String = "bf70bd0d200feb5b6281cd46a3bb40d2",
        @Query("page") page: Int=1
    ): Response<MovieResponse>



    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = "bf70bd0d200feb5b6281cd46a3bb40d2",
        @Query("page") page: Int=1
          ): Response<MovieResponse>




    @GET("movie/upcoming")
   suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = "bf70bd0d200feb5b6281cd46a3bb40d2",
        @Query("page") page: Int=1
        ): Response<MovieResponse>


    @GET("search/movie")
    suspend fun searchMovies(
        @Query("q") movieSearch : String,
        @Query("api_key") apiKey: String = "bf70bd0d200feb5b6281cd46a3bb40d2",
        @Query("page") page: Int=1
    ): Response<Movie>



}