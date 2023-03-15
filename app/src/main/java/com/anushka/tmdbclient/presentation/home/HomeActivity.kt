package com.anushka.tmdbclient.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.anushka.tmdbclient.R
import com.anushka.tmdbclient.databinding.ActivityHomeBinding
import com.anushka.tmdbclient.presentation.artist.ArtistActivity
import com.anushka.tmdbclient.presentation.movie.MovieActivity
import com.anushka.tmdbclient.presentation.tvshow.TvShowActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@HomeActivity, R.layout.activity_home)

        binding.btnMovie.setOnClickListener {
            startActivity(Intent(this@HomeActivity, MovieActivity::class.java))
        }
        binding.btnTvShows.setOnClickListener {
            startActivity(Intent(this@HomeActivity, TvShowActivity::class.java))
        }
        binding.btnArtist.setOnClickListener {
            startActivity(Intent(this@HomeActivity, ArtistActivity::class.java))
        }

    }
}