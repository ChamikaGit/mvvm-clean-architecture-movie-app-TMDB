package com.anushka.tmdbclient.presentation.di.movie

import com.anushka.tmdbclient.domain.usecase.GetMoviesUseCase
import com.anushka.tmdbclient.domain.usecase.UpdateMovieUseCase
import com.anushka.tmdbclient.presentation.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @MovieScope
    @Provides
    fun provideMovieViewModelFactory(
        getMoviesUseCase: GetMoviesUseCase,
        updateMovieUseCase: UpdateMovieUseCase
    ): MovieViewModelFactory {

        return MovieViewModelFactory(getMoviesUseCase, updateMovieUseCase)
    }
}