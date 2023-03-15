package com.anushka.tmdbclient.presentation.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.anushka.tmdbclient.domain.usecase.GetTvShowsUseCase
import com.anushka.tmdbclient.domain.usecase.UpdateTvShowsUseCase

class TvShowViewModel constructor(
    private val getTvShowsUseCase : GetTvShowsUseCase,
    private val updateTvShowsUseCase: UpdateTvShowsUseCase
) : ViewModel() {


    /** we pass the liveData{} block here because we directly call the suspend function of our data source here
     * and also we call here in UI (Main) Thread.
     */

    fun getTvShows() = liveData {
        val tvShowsList = getTvShowsUseCase.execute()
        emit(tvShowsList)
    }

    fun updateTvShowsList() = liveData {

        val tvShowsList = updateTvShowsUseCase.execute()
        emit(tvShowsList)
    }
}