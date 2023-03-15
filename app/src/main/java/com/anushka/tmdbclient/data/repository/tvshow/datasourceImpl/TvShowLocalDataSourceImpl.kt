package com.anushka.tmdbclient.data.repository.tvshow.datasourceImpl

import com.anushka.tmdbclient.data.db.TvShowDAO
import com.anushka.tmdbclient.data.model.tvshow.TvShow
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowLocalDataSourceImpl constructor(private val tvShowDAO: TvShowDAO) :
    TvShowLocalDataSource {


    override suspend fun getAllTvShowsFromDB(): List<TvShow> {
        return tvShowDAO.getAllTvShow()
    }

    override suspend fun saveAllTvShowsToDB(tvShow: List<TvShow>) {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDAO.saveAllTvShows(tvShow)
        }
    }

    override suspend fun clearAllTvShows() {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDAO.deleteAllTvShow()
        }
    }
}