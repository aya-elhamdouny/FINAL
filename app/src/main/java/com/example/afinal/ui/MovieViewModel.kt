package com.example.afinal.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afinal.Util.Resource
import com.example.afinal.models.MovieResponse
import com.example.afinal.reprository.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel(
    val movieRepository: MovieRepository

) : ViewModel() {

    val popularMovies: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    val topRatedMovies: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    val upComingMovies: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()

    var pageNum = 1

    init {
        getPopularMovie()
        getUpComingMovie()
        getTopRatedMovie()
    }

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
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


}

