package com.anushka.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anushka.tmdbclient.data.model.tvshow.TvShow

@Dao
interface TvShowDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllTvShows(tvShows: List<TvShow>)

    @Query("DELETE FROM popular_tvShows")
    suspend fun deleteAllTvShow()

    @Query("SELECT * FROM popular_tvShows")
    suspend fun getAllTvShow() : List<TvShow>
}