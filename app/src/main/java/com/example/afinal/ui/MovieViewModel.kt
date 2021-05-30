package com.example.afinal.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afinal.Util.Resource
import com.example.afinal.models.Movie
import com.example.afinal.models.MovieResponse
import com.example.afinal.reprository.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.Response

abstract class MovieViewModel(
    val movieRepository: MovieRepository

) : ViewModel() {



    val popularMovies: MutableLiveData<Resource<MovieResponse>> by lazy {
        MutableLiveData<Resource<MovieResponse>>().also {     getPopularMovie() }
    }

    val topRatedMovies: MutableLiveData<Resource<MovieResponse>> by lazy {
        MutableLiveData<Resource<MovieResponse>>().also  {     getTopRatedMovie()
    }
    }
    val upComingMovies: MutableLiveData<Resource<MovieResponse>> by lazy {
        MutableLiveData<Resource<MovieResponse>>().also {    getUpComingMovie() }
    }



    var popularpageNum = 1
    var topRatedpageNum = 1
    var upComingpageNum = 1
     var popularmovieResponse : MovieResponse? = null
     var topRatedrmovieResponse : MovieResponse? = null
     var upComingmovieResponse : MovieResponse? = null





    fun getPopularMovie() = viewModelScope.launch {
        popularMovies.postValue(Resource.Loading())
        val popularMovieResponse = movieRepository.getPopularMovie(popularpageNum)
        popularMovies.postValue(handlePopularMovieResponse(popularMovieResponse))
    }


    fun getTopRatedMovie() = viewModelScope.launch {
        topRatedMovies.postValue(Resource.Loading())
        val topRatedMovieResponse = movieRepository.getTopRatedMovie(topRatedpageNum)
        topRatedMovies.postValue(handletopRatedMovieResponse(topRatedMovieResponse))
    }


    fun getUpComingMovie() = viewModelScope.launch {
        upComingMovies.postValue(Resource.Loading())
        val upComingMovieResponse = movieRepository.getUpComingrMovie(upComingpageNum)
        upComingMovies.postValue(handleupComingMovieResponse(upComingMovieResponse))
    }

    private fun handlePopularMovieResponse(response: Response<MovieResponse>) : Resource<MovieResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                popularpageNum++
                if(popularmovieResponse ==null){
                    popularmovieResponse = resultResponse
                }else{
                    val oldResponse = popularmovieResponse?.movie
                    val newResponse = resultResponse.movie
                    oldResponse?.addAll(newResponse)
                }
                return Resource.Success(popularmovieResponse?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    private fun handletopRatedMovieResponse(response: Response<MovieResponse>) : Resource<MovieResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                topRatedpageNum++
                if(topRatedrmovieResponse ==null){
                    topRatedrmovieResponse = resultResponse
                }else{
                    val oldResponse = topRatedrmovieResponse?.movie
                    val newResponse = resultResponse.movie
                    oldResponse?.addAll(newResponse)
                }
                return Resource.Success(topRatedrmovieResponse?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    private fun handleupComingMovieResponse(response: Response<MovieResponse>) : Resource<MovieResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                upComingpageNum++
                if( upComingmovieResponse==null){
                    upComingmovieResponse = resultResponse
                }else{
                    val oldResponse = upComingmovieResponse?.movie
                    val newResponse = resultResponse.movie
                    oldResponse?.addAll(newResponse)
                }
                return Resource.Success(upComingmovieResponse?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveMovie(movie: Movie)= viewModelScope.launch {
        movieRepository.addMovietoDb(movie)
    }

    fun deletMovie(movie: Movie) = viewModelScope.launch {
        movieRepository.deletMovie(movie)
    }

   fun getFavorite() = movieRepository.getFavorite()

     fun addCacheMovie(movies : List<Movie>) = viewModelScope.launch {
         movieRepository.addCacheMovie(movies)
     }

    fun getCachedMovies() = movieRepository.getCachedMovie()



}

