package com.example.afinal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.afinal.R
import com.example.afinal.Util.ConnectionType
import com.example.afinal.Util.NetworkMonitorUtil
import com.example.afinal.database.MovieDatabase
import com.example.afinal.reprository.MovieRepository

class MainActivity : AppCompatActivity() {

   lateinit var   viewModel: MovieViewModel
    private val networkMonitor = NetworkMonitorUtil(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieRepository = MovieRepository(MovieDatabase.getDatabase(this))
        val viewModelProviderFactory = movieViewModelProviderFactory(movieRepository)
        viewModel = ViewModelProvider(this , viewModelProviderFactory).get( MovieViewModel::class.java)


       /* networkMonitor.result = { isAvailable, type ->
            runOnUiThread {
                when (isAvailable) {
                    true -> {
                        viewModel.isOnline= true
                    }
                    false -> {
                        viewModel.isOnline = false
                    }
                }
            }
        }*/


}

   /* override fun onResume() {
        super.onResume()
        networkMonitor.register()

    }
    override fun onStop() {
        super.onStop()
        networkMonitor.unregister()
    }
*/


}

