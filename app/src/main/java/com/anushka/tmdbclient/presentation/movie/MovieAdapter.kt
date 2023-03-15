package com.anushka.tmdbclient.presentation.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anushka.tmdbclient.R
import com.anushka.tmdbclient.data.model.movie.Movie
import com.anushka.tmdbclient.databinding.ListItemBinding
import com.anushka.tmdbclient.utils.Constant
import com.bumptech.glide.Glide

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {


    private val movieList = ArrayList<Movie>()

    fun setList(movie: List<Movie>) {
        movieList.clear()
        movieList.addAll(movie)
        notifyDataSetChanged()
    }


    inner class MyViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.tvTitle.text = movie.title
            binding.tvDescription.text = movie.overview

            movie.posterPath?.let {
                val posterUrl = Constant.IMG_PATH + it
                Glide.with(binding.imageView.context)
                    .load(posterUrl)
                    .into(binding.imageView)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size
}