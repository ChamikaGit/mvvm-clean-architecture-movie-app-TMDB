package com.anushka.tmdbclient.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.anushka.tmdbclient.domain.usecase.GetArtistsUseCase
import com.anushka.tmdbclient.domain.usecase.UpdateArtistsUseCase

class ArtistViewModel constructor(
    private val getArtistsUseCase: GetArtistsUseCase,
    private val updateArtistsUseCase: UpdateArtistsUseCase
) : ViewModel() {

    /** we pass the liveData{} block here because we directly call the suspend function of our data source here
     * and also we call here in UI (Main) Thread.
     */

    fun getArtist() = liveData {
        val artistList = getArtistsUseCase.execute()
        emit(artistList)
    }

    fun updateArtistList() = liveData {

        val artistList = updateArtistsUseCase.execute()
        emit(artistList)
    }
}