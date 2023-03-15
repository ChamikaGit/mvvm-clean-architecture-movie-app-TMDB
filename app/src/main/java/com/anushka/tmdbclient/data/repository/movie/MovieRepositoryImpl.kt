package com.anushka.tmdbclient.data.repository.movie

import android.util.Log
import com.anushka.tmdbclient.data.model.movie.Movie
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.anushka.tmdbclient.domain.repository.MovieRepository
import com.anushka.tmdbclient.utils.Constant.Companion.TAG

class MovieRepositoryImpl constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MovieRepository {

    override suspend fun getAllMovies(): List<Movie>? {
        return getMoviesFromCache()
    }

    override suspend fun updateAllMovies(): List<Movie>? {
        val newListOfMovies = getMoviesFromAPI()
        movieLocalDataSource.clearAllMoviesDB()
        movieLocalDataSource.saveAllMoviesToDB(newListOfMovies)
        movieCacheDataSource.saveMoviesToCache(newListOfMovies)

        return newListOfMovies
    }


    private suspend fun getMoviesFromAPI(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {

            val response = movieRemoteDataSource.getAllMovies()
            val body = response.body()
            body?.let {
                movieList = it.movies
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getMoviesFromAPI: ${e.message}")
        }

        return movieList
    }

    private suspend fun getMoviesFromDB(): List<Movie> {

        lateinit var movieList: List<Movie>

        try {
            movieList = movieLocalDataSource.getAllMoviesFromDB()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Log.e(TAG, "getMoviesFromDB: ${e.message}")
        }
        if (movieList.isNotEmpty()) {
            return movieList
        } else {
            movieList = getMoviesFromAPI()
            movieLocalDataSource.saveAllMoviesToDB(movieList)
        }
        return movieList
    }

    private suspend fun getMoviesFromCache(): List<Movie> {

        lateinit var movieList: List<Movie>

        try {
            movieList = movieCacheDataSource.getMoviesFromCache()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Log.e(TAG, "getMoviesFromCache: ${e.message}")
        }
        if (movieList.isNotEmpty()) {
            return movieList
        } else {
            movieList = getMoviesFromDB()
            movieCacheDataSource.saveMoviesToCache(movieList)
        }
        return movieList
    }
}