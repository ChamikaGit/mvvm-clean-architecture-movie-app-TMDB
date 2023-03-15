package com.anushka.tmdbclient.data.repository.movie.datasourceImpl

import com.anushka.tmdbclient.data.api.TMDBService
import com.anushka.tmdbclient.data.model.movie.MovieList
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl constructor(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : MovieRemoteDataSource {
    override suspend fun getAllMovies(): Response<MovieList> =
        tmdbService.getPopularMovies(apiKey = apiKey)
}