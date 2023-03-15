package com.anushka.tmdbclient.data.repository.artist.datasource

import com.anushka.tmdbclient.data.model.artist.Artist

interface ArtistLocalDataSource {

    suspend fun getAllArtistFromDb() : List<Artist>

    suspend fun saveAllArtistToDb(artist: List<Artist>)

    suspend fun clearAllAristDB()

}