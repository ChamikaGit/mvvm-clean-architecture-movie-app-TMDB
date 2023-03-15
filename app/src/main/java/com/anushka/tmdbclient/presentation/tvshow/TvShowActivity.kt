package com.anushka.tmdbclient.presentation.tvshow

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
import com.anushka.tmdbclient.databinding.ActivityTvShowBinding
import com.anushka.tmdbclient.presentation.artist.ArtistAdapter
import com.anushka.tmdbclient.presentation.artist.ArtistViewModelFactory
import com.anushka.tmdbclient.presentation.di.Injector
import com.anushka.tmdbclient.utils.Constant
import javax.inject.Inject

class TvShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvShowBinding

    private lateinit var tvShowAdapter: TvShowAdapter

    @Inject
    lateinit var tvShowViewModelFactory: TvShowViewModelFactory
    private lateinit var tvShowViewModel: TvShowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@TvShowActivity, R.layout.activity_tv_show)

        (application as Injector).createTvShowSubComponent().inject(this)

        tvShowViewModel = ViewModelProvider(this, tvShowViewModelFactory)[TvShowViewModel::class.java]

        initRecyclerView()
        displayPopularTvShows()
    }

    private fun initRecyclerView() {
        val linearLayoutManger = LinearLayoutManager(this)
        tvShowAdapter = TvShowAdapter()
        binding.movieRecyclerView.apply {
            layoutManager = linearLayoutManger
            adapter = tvShowAdapter
        }
    }

    private fun displayPopularTvShows() {
        binding.movieProgressBar.visibility = View.VISIBLE
        tvShowViewModel.getTvShows().observe(this) { tvShowsList ->
            tvShowsList?.let {
                binding.movieProgressBar.visibility = View.GONE
                Log.e(Constant.TAG, "displayPopularMovie: $it")
                tvShowAdapter.setList(it)
            } ?: kotlin.run {
                binding.movieProgressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No data available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /** This function is used to update the movies using menu item
     *
     */
    private fun updateTvShows() {
        binding.movieProgressBar.visibility = View.VISIBLE
        tvShowViewModel.updateTvShowsList().observe(this) { movieList ->
            movieList?.let {
                binding.movieProgressBar.visibility = View.GONE
                Log.e(Constant.TAG, "displayPopularMovie: $it")
                tvShowAdapter.setList(it)
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
                updateTvShows()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}