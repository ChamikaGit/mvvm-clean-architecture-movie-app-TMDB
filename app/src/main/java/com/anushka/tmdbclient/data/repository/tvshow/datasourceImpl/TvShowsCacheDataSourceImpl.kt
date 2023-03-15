package com.anushka.tmdbclient.data.repository.tvshow.datasourceImpl

import com.anushka.tmdbclient.data.model.tvshow.TvShow
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource

class TvShowsCacheDataSourceImpl : TvShowCacheDataSource {

    private var tvShowList = ArrayList<TvShow>()

    override suspend fun getAllTvShowsFromCache(): List<TvShow> {
        return tvShowList
    }

    override suspend fun saveAllTvShowsToCache(tvShow: List<TvShow>) {
        tvShowList = ArrayList(tvShow)
    }
}