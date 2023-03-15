package com.anushka.tmdbclient.presentation.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anushka.tmdbclient.R
import com.anushka.tmdbclient.data.model.tvshow.TvShow
import com.anushka.tmdbclient.databinding.ListItemBinding
import com.anushka.tmdbclient.utils.Constant
import com.bumptech.glide.Glide

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.MyViewHolder>() {

    private val tvShowList = ArrayList<TvShow>()

    fun setList(tvShow: List<TvShow>) {
        tvShowList.clear()
        tvShowList.addAll(tvShow)
        notifyDataSetChanged()
    }


    inner class MyViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: TvShow) {
            binding.tvTitle.text = tvShow.name
            binding.tvDescription.text = tvShow.name +" "+tvShow.firstAirDate

            tvShow.posterPath?.let {
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
        holder.bind(tvShowList[position])
    }

    override fun getItemCount(): Int = tvShowList.size
}