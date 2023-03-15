package com.anushka.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anushka.tmdbclient.data.model.artist.Artist

@Dao
interface ArtistDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllArtists(artists: List<Artist>)

    @Query("DELETE FROM popular_artist")
    suspend fun deleteAllArtists()

    @Query("SELECT * FROM popular_artist")
    suspend fun getAllArtists() : List<Artist>
}