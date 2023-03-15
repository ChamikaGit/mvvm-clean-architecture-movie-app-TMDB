package com.anushka.tmdbclient.data.repository.movie.datasource

import com.anushka.tmdbclient.data.model.movie.Movie

interface MovieLocalDataSource {

    suspend fun getAllMoviesFromDB() : List<Movie>

    suspend fun saveAllMoviesToDB(movies : List<Movie>)

    suspend fun clearAllMoviesDB()
}