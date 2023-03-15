package com.anushka.tmdbclient.data.repository.tvshow.datasource

import com.anushka.tmdbclient.data.model.tvshow.TvShow

interface TvShowLocalDataSource {

    suspend fun getAllTvShowsFromDB(): List<TvShow>

    suspend fun saveAllTvShowsToDB(tvShow: List<TvShow>)

    suspend fun clearAllTvShows()
}