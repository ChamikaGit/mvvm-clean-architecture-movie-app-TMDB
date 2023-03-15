package com.anushka.tmdbclient.data.repository.artist.datasourceImpl

import com.anushka.tmdbclient.data.api.TMDBService
import com.anushka.tmdbclient.data.model.artist.ArtistList
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import retrofit2.Response

class ArtistRemoteDataSourceImpl constructor(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : ArtistRemoteDataSource {


    override suspend fun getAllArtist(): Response<ArtistList> =
        tmdbService.getPopularArtists(apiKey = apiKey)

}