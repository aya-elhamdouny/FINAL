
package com.example.afinal.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.afinal.R
import com.example.afinal.ui.MainActivity
import com.example.afinal.ui.MovieViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(R.layout.fragment_detail) {
    lateinit var  viewModel : MovieViewModel
     val args : DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        val movie = args.movie

        backbtn?.setOnClickListener {
            findNavController().navigate(R.id.movieFragment)
        }

        movie_poster.apply {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w1280${movie.poster}")
                .transform(CenterCrop())
                .into(this)

                    }
        movie_backdrop.apply {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w1280${movie.backdrop_path}")
                .transform(CenterCrop())
                .into(this)
        }
        movie_title.text = movie.title
        movie_overview.text = movie.overview
        movie_release_date.text = movie.release_date

        fav_btn?.setOnClickListener() {
            viewModel.saveMovie(movie)
            Snackbar.make(view , "movie added" , Snackbar.LENGTH_LONG).show()
        }



    }

}



