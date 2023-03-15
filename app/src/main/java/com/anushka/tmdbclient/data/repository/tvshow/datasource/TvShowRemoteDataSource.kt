package com.anushka.tmdbclient.data.repository.tvshow.datasource

import com.anushka.tmdbclient.data.model.tvshow.TvShowList
import retrofit2.Response

interface TvShowRemoteDataSource {

    suspend fun getAllTvShows() : Response<TvShowList>
}