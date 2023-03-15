package com.anushka.tmdbclient.presentation.movie

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anushka.tmdbclient.R
import com.anushka.tmdbclient.databinding.ActivityMovieBinding
import com.anushka.tmdbclient.presentation.di.Injector
import com.anushka.tmdbclient.utils.Constant.Companion.TAG
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMovieBinding
    private lateinit var movieAdapter: MovieAdapter

    @Inject
    lateinit var movieViewModelFactory: MovieViewModelFactory
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MovieActivity, R.layout.activity_movie)

        (application as Injector).createMovieSubComponent().inject(this)

        movieViewModel = ViewModelProvider(this, movieViewModelFactory)[MovieViewModel::class.java]

        initRecyclerView()
        displayPopularMovie()
    }

    private fun initRecyclerView() {
        val linearLayoutManger = LinearLayoutManager(this)
        movieAdapter = MovieAdapter()
        binding.movieRecyclerView.apply {
            layoutManager = linearLayoutManger
            adapter = movieAdapter
        }
    }

    private fun displayPopularMovie() {
        binding.movieProgressBar.visibility = View.VISIBLE
        movieViewModel.getMovies().observe(this) { movieList ->
            movieList?.let {
                binding.movieProgressBar.visibility = View.GONE
                Log.e(TAG, "displayPopularMovie: $it")
                movieAdapter.setList(it)
            } ?: kotlin.run {
                binding.movieProgressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No data available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /** This function is used to update the movies using menu item
     *
     */
    private fun updateMovies() {
        binding.movieProgressBar.visibility = View.VISIBLE
        movieViewModel.updateMoviesList().observe(this) { movieList ->
            movieList?.let {
                binding.movieProgressBar.visibility = View.GONE
                Log.e(TAG, "displayPopularMovie: $it")
                movieAdapter.setList(it)
            } ?: kotlin.run {
                binding.movieProgressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No data available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.update_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_update->{
                updateMovies()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}