package com.anushka.tmdbclient.data.repository.movie

import com.anushka.tmdbclient.data.model.movie.Movie
import com.anushka.tmdbclient.domain.repository.MovieRepository

class FakeMovieRepository : MovieRepository{

    private val movie = mutableListOf<Movie>()

    init {
        movie.add(Movie(id = 1, overview = "overview1", posterPath = "posterPath1",releaseDate = "releaseDate1", title = "title1"))
        movie.add(Movie(id = 2, overview = "overview2", posterPath = "posterPath2",releaseDate = "releaseDate2", title = "title2"))
    }

    override suspend fun getAllMovies(): List<Movie>? {
        return movie
    }

    override suspend fun updateAllMovies(): List<Movie>? {
        movie.clear()
        movie.add(Movie(id = 3, overview = "overview3", posterPath = "posterPath3",releaseDate = "releaseDate3", title = "title3"))
        movie.add(Movie(id = 4, overview = "overview4", posterPath = "posterPath4",releaseDate = "releaseDate4", title = "title4"))

        return movie
    }

}