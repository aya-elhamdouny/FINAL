package com.example.afinal.ui.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.afinal.R
import com.example.afinal.Util.Resource
import com.example.afinal.adapter.MovieAdapter
import com.example.afinal.ui.MainActivity
import com.example.afinal.ui.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*


class MovieFragment : Fragment(R.layout.fragment_movie) {

    lateinit var  viewModel : MovieViewModel
    lateinit var madapter: MovieAdapter
    val TAG = "error"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setPopularMovieRecyclerView()

        viewModel.popularMovies.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { movieResponse ->
                        madapter.differ.submitList(movieResponse)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.msg?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }




    private  fun hideProgressBar(){
        paginationProgressBar.visibility= View.INVISIBLE
    }
    private  fun showProgressBar(){
        paginationProgressBar.visibility= View.VISIBLE
    }


    private fun setPopularMovieRecyclerView(){
        madapter = MovieAdapter()
        popular_movies.apply {
            adapter= madapter
            layoutManager = LinearLayoutManager(activity)
        }


    }



}