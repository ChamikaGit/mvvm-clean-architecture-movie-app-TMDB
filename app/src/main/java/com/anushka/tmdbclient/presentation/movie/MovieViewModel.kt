package com.anushka.tmdbclient.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.anushka.tmdbclient.domain.usecase.GetMoviesUseCase
import com.anushka.tmdbclient.domain.usecase.UpdateMovieUseCase

class MovieViewModel constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMovieUseCase: UpdateMovieUseCase
) : ViewModel() {

    /** we pass the liveData{} block here because we directly call the suspend function of our data source here
     * and also we call here in UI (Main) Thread.
     */

    fun getMovies() = liveData {
        val movieList = getMoviesUseCase.execute()
        emit(movieList)
    }

    fun updateMoviesList() = liveData {

        val movieList = updateMovieUseCase.execute()
        emit(movieList)
    }


}