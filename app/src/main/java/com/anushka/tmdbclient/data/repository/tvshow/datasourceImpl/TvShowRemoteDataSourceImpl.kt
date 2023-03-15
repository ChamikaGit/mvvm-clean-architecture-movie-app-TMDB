package com.anushka.tmdbclient.data.repository.tvshow.datasourceImpl

import com.anushka.tmdbclient.data.api.TMDBService
import com.anushka.tmdbclient.data.model.tvshow.TvShowList
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import retrofit2.Response

class TvShowRemoteDataSourceImpl constructor(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : TvShowRemoteDataSource {

    override suspend fun getAllTvShows(): Response<TvShowList> =
        tmdbService.getPopularTvShows(apiKey = apiKey)
}