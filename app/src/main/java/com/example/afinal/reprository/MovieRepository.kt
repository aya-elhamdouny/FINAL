package com.example.afinal.reprository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.afinal.api.RetrofitBuilder
import com.example.afinal.database.MovieDatabase
import com.example.afinal.domain.Model
import com.example.afinal.models.Movie
import com.example.afinal.models.MovieResponse
import com.example.afinal.models.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MovieRepository( val database: MovieDatabase){

    suspend fun getPopularMovie(pageNum : Int) =
        RetrofitBuilder.api.getPopularMovies(page = pageNum)


    suspend fun getTopRatedMovie(pageNum : Int) =
        RetrofitBuilder.api.getTopRatedMovies(page = pageNum)


    suspend fun getUpComingrMovie(pageNum : Int) =
         RetrofitBuilder.api.getUpcomingMovies(page = pageNum)






    suspend fun addMovietoDb(movie: Movie)= database.MovieDao.insert(movie)

   suspend fun deletMovie(movie: Movie) = database.MovieDao.deleteMovie(movie)

    fun getFavorite() =database.MovieDao.getMovies()

    suspend fun addCacheMovie(movies :List<Movie>) = database.MovieDao.cacheResponse(movies)
     fun getCachedMovie() = database.MovieDao.getCachedMovies()



    /*//caching

    val movies: LiveData<List<Model>> =
        Transformations.map(database.MovieDao().getMovies()){
            it.asDomainModel()
        }


    suspend fun refresh(){
        withContext(Dispatchers.IO){
         //   val moveList = RetrofitBuilder.api.getPopularMovies().await()
         //   database.MovieDao().insert(moveList.asDatabasemodel())
        }
    }*/










}