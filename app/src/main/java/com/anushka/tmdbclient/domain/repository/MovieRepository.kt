package com.anushka.tmdbclient.domain.repository

import com.anushka.tmdbclient.data.model.movie.Movie

interface MovieRepository {

    suspend fun getAllMovies() : List<Movie>?

    suspend fun updateAllMovies() :List<Movie>?
}