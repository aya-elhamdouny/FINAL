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

class MovieViewModel(
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


    var pageNum = 1
     var movieResponse : MovieResponse? = null


    fun getPopularMovie() = viewModelScope.launch {
        popularMovies.postValue(Resource.Loading())
        val popularMovieResponse = movieRepository.getPopularMovie(pageNum)
        popularMovies.postValue(handleMovieResponse(popularMovieResponse))
    }


    fun getTopRatedMovie() = viewModelScope.launch {
        topRatedMovies.postValue(Resource.Loading())
        val topRatedMovieResponse = movieRepository.getTopRatedMovie(pageNum)
        topRatedMovies.postValue(handleMovieResponse(topRatedMovieResponse))
    }


    fun getUpComingMovie() = viewModelScope.launch {
        upComingMovies.postValue(Resource.Loading())
        val upComingMovieResponse = movieRepository.getUpComingrMovie(pageNum)
        upComingMovies.postValue(handleMovieResponse(upComingMovieResponse))
    }

    private fun handleMovieResponse(response: Response<MovieResponse>) : Resource<MovieResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                pageNum++
                if(movieResponse ==null){
                    movieResponse = resultResponse
                }else{
                    val oldResponse = movieResponse?.movie
                    val newResponse = resultResponse.movie
                    oldResponse?.addAll(newResponse)
                }
                return Resource.Success(movieResponse?: resultResponse)
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

}

