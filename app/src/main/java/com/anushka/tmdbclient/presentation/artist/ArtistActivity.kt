package com.anushka.tmdbclient.presentation.artist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anushka.tmdbclient.R
import com.anushka.tmdbclient.databinding.ActivityArtistBinding
import com.anushka.tmdbclient.presentation.di.Injector
import com.anushka.tmdbclient.utils.Constant
import javax.inject.Inject

class ArtistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtistBinding

    private lateinit var artistAdapter: ArtistAdapter

    @Inject
    lateinit var artisViewModelFactory: ArtistViewModelFactory
    private lateinit var artistViewModel: ArtistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@ArtistActivity,R.layout.activity_artist)

        (application as Injector).createArtistSubComponent().inject(this)

        artistViewModel = ViewModelProvider(this, artisViewModelFactory)[ArtistViewModel::class.java]

        initRecyclerView()
        displayPopularArtist()
    }

    private fun initRecyclerView() {
        val linearLayoutManger = LinearLayoutManager(this)
        artistAdapter = ArtistAdapter()
        binding.movieRecyclerView.apply {
            layoutManager = linearLayoutManger
            adapter = artistAdapter
        }
    }

    private fun displayPopularArtist() {
        binding.movieProgressBar.visibility = View.VISIBLE
        artistViewModel.getArtist().observe(this) { movieList ->
            movieList?.let {
                binding.movieProgressBar.visibility = View.GONE
                Log.e(Constant.TAG, "displayPopularMovie: $it")
                artistAdapter.setList(it)
            } ?: kotlin.run {
                binding.movieProgressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No data available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /** This function is used to update the movies using menu item
     *
     */
    private fun updateArtist() {
        binding.movieProgressBar.visibility = View.VISIBLE
        artistViewModel.updateArtistList().observe(this) { movieList ->
            movieList?.let {
                binding.movieProgressBar.visibility = View.GONE
                Log.e(Constant.TAG, "displayPopularMovie: $it")
                artistAdapter.setList(it)
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
                updateArtist()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}