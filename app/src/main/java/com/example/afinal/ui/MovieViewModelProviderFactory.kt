package com.example.afinal.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.afinal.reprository.MovieRepository

class movieViewModelProviderFactory(
    val movieRepository: MovieRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return MovieViewModel(movieRepository) as T
    }
}