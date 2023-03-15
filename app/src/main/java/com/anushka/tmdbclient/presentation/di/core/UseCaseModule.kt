package com.anushka.tmdbclient.presentation.di.core

import com.anushka.tmdbclient.domain.repository.ArtistRepository
import com.anushka.tmdbclient.domain.repository.MovieRepository
import com.anushka.tmdbclient.domain.repository.TvShowRepository
import com.anushka.tmdbclient.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {


    @Provides
    fun provideGetMovieUseCase(movieRepository: MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(movieRepository = movieRepository)
    }

    @Provides
    fun provideUpdateMovieUseCase(movieRepository: MovieRepository): UpdateMovieUseCase {
        return UpdateMovieUseCase(movieRepository = movieRepository)
    }

    @Provides
    fun provideGetArtistUseCase(artistRepository: ArtistRepository): GetArtistsUseCase {
        return GetArtistsUseCase(artistRepository = artistRepository)
    }

    @Provides
    fun provideUpdateArtistUseCase(artistRepository: ArtistRepository): UpdateArtistsUseCase {
        return UpdateArtistsUseCase(artistRepository = artistRepository)
    }

    @Provides
    fun provideGetTvShowUseCase(tvShowRepository: TvShowRepository): GetTvShowsUseCase {
        return GetTvShowsUseCase(tvShowRepository = tvShowRepository)
    }

    @Provides
    fun provideUpdateTvShowUseCase(tvShowRepository: TvShowRepository): UpdateTvShowsUseCase {
        return UpdateTvShowsUseCase(tvShowRepository = tvShowRepository)
    }
}