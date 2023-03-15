package com.anushka.tmdbclient.data.repository.artist.datasource

import com.anushka.tmdbclient.data.model.artist.ArtistList
import retrofit2.Response

interface ArtistRemoteDataSource {

    suspend fun getAllArtist() : Response<ArtistList>
}