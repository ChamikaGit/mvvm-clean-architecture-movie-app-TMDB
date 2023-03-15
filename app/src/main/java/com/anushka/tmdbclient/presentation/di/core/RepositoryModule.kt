package com.anushka.tmdbclient.presentation.di.core

import com.anushka.tmdbclient.data.repository.artist.ArtistRepositoryImpl
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.anushka.tmdbclient.data.repository.movie.MovieRepositoryImpl
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.anushka.tmdbclient.data.repository.tvshow.TvShowRepositoryImpl
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.anushka.tmdbclient.domain.repository.ArtistRepository
import com.anushka.tmdbclient.domain.repository.MovieRepository
import com.anushka.tmdbclient.domain.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {


    @Singleton
    @Provides
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource,
        movieCacheDataSource: MovieCacheDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(
            movieRemoteDataSource = movieRemoteDataSource,
            movieLocalDataSource = movieLocalDataSource,
            movieCacheDataSource = movieCacheDataSource
        )

    }

    @Singleton
    @Provides
    fun provideArtistRepository(
        artistCacheDataSource: ArtistCacheDataSource,
        artistRemoteDataSource: ArtistRemoteDataSource,
        artistLocalDataSource: ArtistLocalDataSource
    ): ArtistRepository {
        return ArtistRepositoryImpl(
            artistRemoteDataSource = artistRemoteDataSource,
            artistLocalDataSource = artistLocalDataSource,
            artistCacheDataSource = artistCacheDataSource
        )

    }

    @Singleton
    @Provides
    fun provideTvShowRepository(
        tvShowLocalDataSource: TvShowLocalDataSource,
        tvShowRemoteDataSource: TvShowRemoteDataSource,
        tvShowCacheDataSource: TvShowCacheDataSource
    ): TvShowRepository {
        return TvShowRepositoryImpl(
            tvShowRemoteDataSource = tvShowRemoteDataSource,
            tvShowLocalDataSource = tvShowLocalDataSource,
            tvShowCacheDataSource = tvShowCacheDataSource
        )

    }

}