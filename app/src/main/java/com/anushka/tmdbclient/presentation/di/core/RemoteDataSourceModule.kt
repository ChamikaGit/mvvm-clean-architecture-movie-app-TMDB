package com.anushka.tmdbclient.presentation.di.core

import com.anushka.tmdbclient.data.api.TMDBService
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.anushka.tmdbclient.data.repository.artist.datasourceImpl.ArtistRemoteDataSourceImpl
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.anushka.tmdbclient.data.repository.movie.datasourceImpl.MovieRemoteDataSourceImpl
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.anushka.tmdbclient.data.repository.tvshow.datasourceImpl.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataSourceModule(private val apiKey: String) {

    @Singleton
    @Provides
    fun provideMovieRemote(tmdbService: TMDBService): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(tmdbService = tmdbService, apiKey = apiKey)
    }

    @Singleton
    @Provides
    fun provideArtistRemote(tmdbService: TMDBService): ArtistRemoteDataSource {
        return ArtistRemoteDataSourceImpl(tmdbService = tmdbService, apiKey = apiKey)
    }

    @Singleton
    @Provides
    fun provideTvShowRemote(tmdbService: TMDBService): TvShowRemoteDataSource {
        return TvShowRemoteDataSourceImpl(tmdbService = tmdbService, apiKey = apiKey)
    }
}