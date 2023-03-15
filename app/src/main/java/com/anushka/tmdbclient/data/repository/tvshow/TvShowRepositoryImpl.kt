package com.anushka.tmdbclient.data.repository.tvshow

import android.util.Log
import com.anushka.tmdbclient.data.model.tvshow.TvShow
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.anushka.tmdbclient.domain.repository.TvShowRepository
import com.anushka.tmdbclient.utils.Constant.Companion.TAG

class TvShowRepositoryImpl constructor(
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource
) : TvShowRepository {


    override suspend fun getAllTvShows(): List<TvShow>? {
       return getAllTvShowsFromAPI()
    }

    override suspend fun updateAllTvShows(): List<TvShow>? {
        val newTvShowsList = getAllTvShowsFromAPI()
        tvShowLocalDataSource.clearAllTvShows()
        tvShowLocalDataSource.saveAllTvShowsToDB(newTvShowsList)
        tvShowCacheDataSource.saveAllTvShowsToCache(newTvShowsList)

        return newTvShowsList
    }

    private suspend fun getAllTvShowsFromAPI(): List<TvShow> {

        lateinit var tvShowList: List<TvShow>

        try {
            val response = tvShowRemoteDataSource.getAllTvShows()
            val body = response.body()
            body?.let {
                tvShowList = it.tvShows
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getAllTvShowsFromAPI: ${e.message}")
        }

        return tvShowList
    }

    private suspend fun getAllTvShowsFromDB(): List<TvShow> {

        lateinit var tvShowList: List<TvShow>

        try {
            tvShowList = tvShowLocalDataSource.getAllTvShowsFromDB()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getAllTvShowsFromDB: ${e.message}")
        }

        if (tvShowList.isNotEmpty()) {

            return tvShowList
        } else {
            tvShowList = getAllTvShowsFromAPI()
            tvShowLocalDataSource.saveAllTvShowsToDB(tvShowList)
        }

        return tvShowList


    }

    private suspend fun getAllTvShowsFromCache(): List<TvShow> {

        lateinit var tvShowList: List<TvShow>

        try {
            tvShowList = tvShowCacheDataSource.getAllTvShowsFromCache()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getAllTvShowsFromCache: ${e.message}")
        }

        if (tvShowList.isNotEmpty()) {

            return tvShowList
        } else {
            tvShowList = getAllTvShowsFromDB()
            tvShowCacheDataSource.saveAllTvShowsToCache(tvShowList)
        }

        return tvShowList


    }
}