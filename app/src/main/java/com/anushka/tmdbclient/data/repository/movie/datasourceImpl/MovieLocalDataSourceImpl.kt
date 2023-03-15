package com.anushka.tmdbclient.data.repository.movie.datasourceImpl

import com.anushka.tmdbclient.data.db.MovieDAO
import com.anushka.tmdbclient.data.model.movie.Movie
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieLocalDataSourceImpl constructor(private val movieDAO: MovieDAO) : MovieLocalDataSource {

    override suspend fun getAllMoviesFromDB(): List<Movie> = movieDAO.getAllMovies()

    override suspend fun saveAllMoviesToDB(movies: List<Movie>) {
        CoroutineScope(Dispatchers.IO).launch{
            movieDAO.saveAllMovies(movies)
        }
    }

    override suspend fun clearAllMoviesDB() {
        CoroutineScope(Dispatchers.IO).launch {
            movieDAO.deleteAllMovies()
        }
    }
}