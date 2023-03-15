package com.anushka.tmdbclient.presentation.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anushka.tmdbclient.R
import com.anushka.tmdbclient.data.model.artist.Artist
import com.anushka.tmdbclient.databinding.ListItemBinding
import com.anushka.tmdbclient.utils.Constant
import com.bumptech.glide.Glide

class ArtistAdapter : RecyclerView.Adapter<ArtistAdapter.MyViewHolder>() {

    private val artistList = ArrayList<Artist>()

    fun setList(movie: List<Artist>) {
        artistList.clear()
        artistList.addAll(movie)
        notifyDataSetChanged()
    }


    inner class MyViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Artist) {
            binding.tvTitle.text = movie.name
            binding.tvDescription.text = movie.name

            movie.profilePath?.let {
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
        holder.bind(artistList[position])
    }

    override fun getItemCount(): Int = artistList.size
}