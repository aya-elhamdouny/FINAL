package com.example.afinal.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.HorizontalScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.afinal.R
import com.example.afinal.Util.Resource
import com.example.afinal.adapter.MovieAdapter
import com.example.afinal.database.MovieDatabase
import com.example.afinal.reprository.MovieRepository
import com.example.afinal.ui.MainActivity
import com.example.afinal.ui.MovieViewModel
import com.example.afinal.ui.movieViewModelProviderFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_movie.paginationProgressBar
import kotlinx.android.synthetic.main.fragment_movie.view.*


class MovieFragment : Fragment(R.layout.fragment_movie) {

    lateinit var viewModel: MovieViewModel
    lateinit var popularadapter: MovieAdapter
    lateinit var upcomingAdapter: MovieAdapter
    lateinit var topratedAdapter: MovieAdapter
    val TAG = "error"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setPopularMovieRecyclerView()
        settopRatedMovieRecyclerView()
        setupComingMovieRecyclerView()

        popularadapter.setOnItemClickListener{
            val bundle = Bundle().apply{
                putSerializable("movie" , it) }
            findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToDetailFragment(it))
            //findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToFavFragment(it))
            }


        upcomingAdapter.setOnItemClickListener{
            val bundle = Bundle().apply{
                putSerializable("movie" , it) }
            findNavController().navigate(
                R.id.action_movieFragment_to_detailFragment,
                bundle
            )}
        topratedAdapter.setOnItemClickListener{
            val bundle = Bundle().apply{
                putSerializable("movie" , it) }
            findNavController().navigate(
                R.id.action_movieFragment_to_detailFragment,
                bundle
            )}

        fav_home_btn?.setOnClickListener {
            findNavController().navigate(R.id.favFragment)
        }

        viewModel.popularMovies.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { movieResponse ->
                        popularadapter.differ.submitList(movieResponse.movie)
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
                        topratedAdapter.differ.submitList(movieResponse.movie)
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
                        upcomingAdapter.differ.submitList(movieResponse.movie)
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

    var isLoading = false
    var isScrolling = false
    var isLastPage = false

    val popularscrollViewListner = object  : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisableItem = layoutManager.findFirstVisibleItemPosition()
            val visableItemCount = layoutManager.childCount
            val totalItem = layoutManager.itemCount

            val isNotLoadindAndNotScrolling = !isLoading && !isScrolling
            val isLastItem = firstVisableItem + visableItemCount >= totalItem
            val isNotBeginning =firstVisableItem >=0
            val isMore = totalItem >= 10
            val paginate = isNotLoadindAndNotScrolling && isMore && isNotBeginning
                    &&isLastItem&& isScrolling
            if(paginate){
                viewModel.getPopularMovie()
                isScrolling = false
            } }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                isScrolling = true
        }
    }

    val topRatedrscrollViewListner = object  : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisableItem = layoutManager.findFirstVisibleItemPosition()
            val visableItemCount = layoutManager.childCount
            val totalItem = layoutManager.itemCount

            val isNotLoadindAndNotScrolling = !isLoading && !isScrolling
            val isLastItem = firstVisableItem + visableItemCount >= totalItem
            val isNotBeginning =firstVisableItem >=0
            val isMore = totalItem >= 10
            val paginate = isNotLoadindAndNotScrolling && isMore && isNotBeginning
                    &&isLastItem&& isScrolling
            if(paginate){
                viewModel.getTopRatedMovie()
                isScrolling = false
            } }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                isScrolling = true
        }
    }


    val upComingrscrollViewListner = object  : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisableItem = layoutManager.findFirstVisibleItemPosition()
            val visableItemCount = layoutManager.childCount
            val totalItem = layoutManager.itemCount

            val isNotLoadindAndNotScrolling = !isLoading && !isScrolling
            val isLastItem = firstVisableItem + visableItemCount >= totalItem
            val isNotBeginning =firstVisableItem >=0
            val isMore = totalItem >= 10
            val paginate = isNotLoadindAndNotScrolling && isMore && isNotBeginning
                    &&isLastItem&& isScrolling
            if(paginate){
                viewModel.getUpComingMovie()
                isScrolling = false
            } }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                isScrolling = true
        }
    }



    private fun setPopularMovieRecyclerView(){
        popularadapter = MovieAdapter()
        popular_movies_rv.apply {
            adapter= popularadapter
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,
                false)
        }


    }
    private fun settopRatedMovieRecyclerView(){
        topratedAdapter = MovieAdapter()
        top_rated_movies_rv.apply {
            adapter= topratedAdapter
            layoutManager =  LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,
                false)
        }


    }
    private fun setupComingMovieRecyclerView(){
        upcomingAdapter = MovieAdapter()
        upcoming_movies_rv.apply {
            adapter= upcomingAdapter
            layoutManager =  LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,
                false)
        }


    }



}