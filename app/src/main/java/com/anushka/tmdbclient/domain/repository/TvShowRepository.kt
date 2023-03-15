package com.anushka.tmdbclient.domain.repository

import com.anushka.tmdbclient.data.model.tvshow.TvShow

interface TvShowRepository {

    suspend fun getAllTvShows() : List<TvShow>?

    suspend fun updateAllTvShows() :List<TvShow>?
}