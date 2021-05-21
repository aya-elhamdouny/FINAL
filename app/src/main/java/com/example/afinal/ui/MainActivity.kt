package com.example.afinal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.afinal.R
import com.example.afinal.database.MovieDatabase
import com.example.afinal.reprository.MovieRepository

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieRepository = MovieRepository(MovieDatabase.getDatabase(this))
        val viewModelProviderFactory = movieViewModelProviderFactory(movieRepository)
        viewModel = ViewModelProvider(this , viewModelProviderFactory).get( MovieViewModel::class.java)


    }
}