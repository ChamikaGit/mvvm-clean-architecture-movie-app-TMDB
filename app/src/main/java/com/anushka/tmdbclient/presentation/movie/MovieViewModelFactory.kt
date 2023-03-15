package com.anushka.tmdbclient.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anushka.tmdbclient.domain.usecase.GetMoviesUseCase
import com.anushka.tmdbclient.domain.usecase.UpdateMovieUseCase

class MovieViewModelFactory constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMovieUseCase: UpdateMovieUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(getMoviesUseCase, updateMovieUseCase) as T

    }
}