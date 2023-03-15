package com.anushka.tmdbclient.presentation.di.tvshow

import com.anushka.tmdbclient.domain.usecase.GetTvShowsUseCase
import com.anushka.tmdbclient.domain.usecase.UpdateTvShowsUseCase
import com.anushka.tmdbclient.presentation.tvshow.TvShowViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvShowModule {

    @TvShowScope
    @Provides
    fun provideTvShowViewModelFactory(
        getTvShowsUseCase: GetTvShowsUseCase,
        updateTvShowsUseCase: UpdateTvShowsUseCase
    ): TvShowViewModelFactory {
        return TvShowViewModelFactory(getTvShowsUseCase, updateTvShowsUseCase)
    }
}