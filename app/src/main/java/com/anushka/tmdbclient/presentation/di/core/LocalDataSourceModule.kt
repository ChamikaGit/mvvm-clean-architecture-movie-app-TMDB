package com.anushka.tmdbclient.presentation.di.core

import com.anushka.tmdbclient.data.db.ArtistDAO
import com.anushka.tmdbclient.data.db.MovieDAO
import com.anushka.tmdbclient.data.db.TvShowDAO
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.anushka.tmdbclient.data.repository.artist.datasourceImpl.ArtistLocalDataSourceImpl
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.anushka.tmdbclient.data.repository.movie.datasourceImpl.MovieLocalDataSourceImpl
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.anushka.tmdbclient.data.repository.tvshow.datasourceImpl.TvShowLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataSourceModule {

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(movieDAO: MovieDAO): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(movieDAO = movieDAO)
    }

    @Singleton
    @Provides
    fun provideArtistLocalDataSource(artistDAO: ArtistDAO): ArtistLocalDataSource {
        return ArtistLocalDataSourceImpl(artistDAO = artistDAO)
    }

    @Singleton
    @Provides
    fun provideTvShowLocalDataSource(tvShowDAO: TvShowDAO): TvShowLocalDataSource {
        return TvShowLocalDataSourceImpl(tvShowDAO = tvShowDAO)
    }

}