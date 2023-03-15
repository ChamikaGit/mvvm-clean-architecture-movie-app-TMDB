package com.anushka.tmdbclient.data.repository.movie.datasource

import com.anushka.tmdbclient.data.model.movie.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {

    suspend fun getAllMovies() : Response<MovieList>
}