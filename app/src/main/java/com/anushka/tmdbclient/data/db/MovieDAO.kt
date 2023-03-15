package com.anushka.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anushka.tmdbclient.data.model.movie.Movie

@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllMovies(movie: List<Movie>)

    @Query("DELETE FROM popular_movies")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM popular_movies")
    suspend fun getAllMovies() : List<Movie>
}