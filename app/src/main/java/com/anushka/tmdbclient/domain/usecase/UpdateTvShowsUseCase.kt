package com.anushka.tmdbclient.domain.usecase

import com.anushka.tmdbclient.data.model.tvshow.TvShow
import com.anushka.tmdbclient.domain.repository.TvShowRepository

class UpdateTvShowsUseCase constructor(private val tvShowRepository: TvShowRepository) {

    suspend fun execute() : List<TvShow>? = tvShowRepository.updateAllTvShows()
}