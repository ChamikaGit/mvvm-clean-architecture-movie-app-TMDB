package com.anushka.tmdbclient.data.repository.artist.datasource

import com.anushka.tmdbclient.data.model.artist.Artist

interface ArtistCacheDataSource {

    suspend fun getArtistFromCache(): List<Artist>

    suspend fun saveArtistToCache(artist: List<Artist>)

}