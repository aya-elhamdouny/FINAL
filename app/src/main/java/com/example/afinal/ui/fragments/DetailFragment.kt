
package com.example.afinal.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.afinal.R
import com.example.afinal.ui.MainActivity
import com.example.afinal.ui.MovieViewModel

class DetailFragment : Fragment(R.layout.fragment_detail) {
    lateinit var  viewModel : MovieViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }

}