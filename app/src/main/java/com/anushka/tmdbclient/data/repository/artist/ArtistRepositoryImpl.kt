package com.anushka.tmdbclient.data.repository.artist

import android.util.Log
import com.anushka.tmdbclient.data.model.artist.Artist
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.anushka.tmdbclient.domain.repository.ArtistRepository
import com.anushka.tmdbclient.utils.Constant

class ArtistRepositoryImpl constructor(
    private val artistCacheDataSource: ArtistCacheDataSource,
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource
) : ArtistRepository {


    override suspend fun getAllArtists(): List<Artist>? {
        return getArtistFromCache()
    }

    override suspend fun updateAllArtists(): List<Artist>? {
        val newListOfArtist = getArtistFromAPI()
        artistLocalDataSource.clearAllAristDB()
        artistLocalDataSource.saveAllArtistToDb(newListOfArtist)
        artistCacheDataSource.saveArtistToCache(newListOfArtist)

        return newListOfArtist

    }

    private suspend fun getArtistFromAPI(): List<Artist> {
        lateinit var artistList: List<Artist>
        try {
            val response = artistRemoteDataSource.getAllArtist()
            val body = response.body()
            body?.let {
                artistList = it.people
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(Constant.TAG, "getMoviesFromAPI: ${e.message}")
        }

        return artistList
    }

    private suspend fun getArtistFromDB(): List<Artist> {

        lateinit var artistList: List<Artist>

        try {
            artistList = artistLocalDataSource.getAllArtistFromDb()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(Constant.TAG, "getArtistFromDB: ${e.message}")
        }

        if (artistList.isNotEmpty()) {
            return artistList
        } else {
            artistList = getArtistFromAPI()
            artistLocalDataSource.saveAllArtistToDb(artistList)
        }

        return artistList

    }

    private suspend fun getArtistFromCache(): List<Artist> {

        lateinit var artistList: List<Artist>

        try {
            artistList = artistCacheDataSource.getArtistFromCache()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(Constant.TAG, "getArtistFromCache: ${e.message}")
        }

        if (artistList.isNotEmpty()) {
            return artistList
        } else {
            artistList = getArtistFromDB()
            artistCacheDataSource.saveArtistToCache(artistList)
        }

        return artistList

    }
}