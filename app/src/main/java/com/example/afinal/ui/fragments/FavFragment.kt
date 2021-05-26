package com.example.afinal.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.afinal.R
import com.example.afinal.adapter.MovieAdapter
import com.example.afinal.ui.MainActivity
import com.example.afinal.ui.MovieViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_fav.*
import kotlinx.android.synthetic.main.fragment_movie.*

class FavFragment : Fragment(R.layout.fragment_fav) {
    lateinit var viewModel: MovieViewModel
    lateinit var madapter: MovieAdapter
    val args : FavFragmentDirections by navArgs()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setFavMovieRecyclerView()
        //val movie = args.movie
        madapter.setOnItemClickListener{
            val bundle = Bundle().apply{
                putSerializable("movie" , it) }
            findNavController().navigate( FavFragmentDirections.actionFavFragmentToDetailFragment(it)
            )
        }

        back_btn?.setOnClickListener {
            findNavController().navigate(R.id.movieFragment)

        }

        viewModel.getFavorite().observe(viewLifecycleOwner, Observer { movies ->
            madapter.differ.submitList(movies)
        })

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val postion = viewHolder.adapterPosition
                val movie = madapter.differ.currentList[postion]
                viewModel.deletMovie(movie)
                Snackbar.make(view, "Successfully deleted movie", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.deletMovie(movie)
                    }
                }.show()
            }

        }


        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(fav_recyclerview)
        }


    }

    private fun setFavMovieRecyclerView() {
        madapter = MovieAdapter()
        fav_recyclerview.apply {
            adapter = madapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}