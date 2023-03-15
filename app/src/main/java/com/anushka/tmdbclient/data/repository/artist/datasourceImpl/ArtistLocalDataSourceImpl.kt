package com.anushka.tmdbclient.data.repository.artist.datasourceImpl

import com.anushka.tmdbclient.data.db.ArtistDAO
import com.anushka.tmdbclient.data.model.artist.Artist
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistLocalDataSourceImpl constructor(private val artistDAO: ArtistDAO) :
    ArtistLocalDataSource {

    override suspend fun getAllArtistFromDb(): List<Artist> {

        return artistDAO.getAllArtists()
    }

    override suspend fun saveAllArtistToDb(artist: List<Artist>) {
        CoroutineScope(Dispatchers.IO).launch {
            artistDAO.saveAllArtists(artist)
        }

    }

    override suspend fun clearAllAristDB() {
        CoroutineScope(Dispatchers.IO).launch {
            artistDAO.deleteAllArtists()
        }
    }
}