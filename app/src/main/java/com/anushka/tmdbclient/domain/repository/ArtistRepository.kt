package com.anushka.tmdbclient.domain.repository

import com.anushka.tmdbclient.data.model.artist.Artist

interface ArtistRepository {

    suspend fun getAllArtists() : List<Artist>?

    suspend fun updateAllArtists() : List<Artist>?
}