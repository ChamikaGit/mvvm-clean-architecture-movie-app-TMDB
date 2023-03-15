package com.anushka.tmdbclient.data.repository.tvshow.datasource

import com.anushka.tmdbclient.data.model.tvshow.TvShow

interface TvShowCacheDataSource {

    suspend fun getAllTvShowsFromCache(): List<TvShow>

    suspend fun saveAllTvShowsToCache(tvShow: List<TvShow>)
}