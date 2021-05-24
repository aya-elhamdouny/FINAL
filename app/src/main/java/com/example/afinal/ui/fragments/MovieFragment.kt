package com.example.afinal.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.afinal.R
import com.example.afinal.Util.Resource
import com.example.afinal.adapter.MovieAdapter
import com.example.afinal.database.MovieDatabase
import com.example.afinal.reprository.MovieRepository
import com.example.afinal.ui.MainActivity
import com.example.afinal.ui.MovieViewModel
import com.example.afinal.ui.movieViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_movie.view.*


class MovieFragment : Fragment(R.layout.fragment_movie) {

    lateinit var viewModel: MovieViewModel
    lateinit var madapter: MovieAdapter
    val TAG = "error"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setPopularMovieRecyclerView()
        settopRatedMovieRecyclerView()
        setupComingMovieRecyclerView()
        madapter.setOnItemClickListener{
            val bundle = Bundle().apply{
                putSerializable("movie" , it) }
            findNavController().navigate(
                R.id.action_movieFragment_to_detailFragment,
                bundle
            )}





        viewModel.popularMovies.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { movieResponse ->
                        madapter.differ.submitList(movieResponse.movie)
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
        viewModel.topRatedMovies.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { movieResponse ->
                        madapter.differ.submitList(movieResponse.movie)
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
        viewModel.upComingMovies.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { movieResponse ->
                        madapter.differ.submitList(movieResponse.movie)
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
        popular_movies_rv.apply {
            adapter= madapter
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,
                false)
        }


    }
    private fun settopRatedMovieRecyclerView(){
        madapter = MovieAdapter()
        top_rated_movies_rv.apply {
            adapter= madapter
            layoutManager =  LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,
                false)
        }


    }
    private fun setupComingMovieRecyclerView(){
        madapter = MovieAdapter()
        upcoming_movies_rv.apply {
            adapter= madapter
            layoutManager =  LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,
                false)
        }


    }



}