package com.anushka.tmdbclient.presentation.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anushka.tmdbclient.data.model.movie.Movie
import com.anushka.tmdbclient.data.repository.movie.FakeMovieRepository
import com.anushka.tmdbclient.domain.usecase.GetMoviesUseCase
import com.anushka.tmdbclient.domain.usecase.UpdateMovieUseCase
import com.anushka.tmdbclient.getOrAwaitValue
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MovieViewModelTest {

    /**InstantTaskExecutorRule is a class from Junit Library.
     * This run all the jetpack Architecture component related background jobs in the same thread.
     * Because that test run results will happened synchronously and in a repeatable order.
     * as ex:- if we use liveData we need to use this InstantTaskExecutorRule()
     */

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieViewModel: MovieViewModel


    @Before
    fun setUp() {
        /**In here instead of Mocks we use Fake as testDoubles.
         * below we initialize the movieViewModel using getMovieUseCase and updateMovieUseCase
         * for above useCase classes dependency is repository so we used fakeMovieRepository as the dependency.
         */

        val fakeMovieRepository = FakeMovieRepository()
        val getMoviesUseCase = GetMoviesUseCase(movieRepository = fakeMovieRepository)
        val updateMovieUseCase = UpdateMovieUseCase(movieRepository = fakeMovieRepository)

        movieViewModel = MovieViewModel(
            getMoviesUseCase = getMoviesUseCase,
            updateMovieUseCase = updateMovieUseCase
        )
    }

    @Test
    fun getMovies_returnCurrentList() {

        val movie = mutableListOf<Movie>()

        movie.add(
            Movie(
                id = 1,
                overview = "overview1",
                posterPath = "posterPath1",
                releaseDate = "releaseDate1",
                title = "title1"
            )
        )
        movie.add(
            Movie(
                id = 2,
                overview = "overview2",
                posterPath = "posterPath2",
                releaseDate = "releaseDate2",
                title = "title2"
            )
        )

        /** we used getOrAwaitValue() method as extention funtion in LiveData from our LiveDataTestUtil.kt File under test directory
         */
        val currentList = movieViewModel.getMovies().getOrAwaitValue()
        Truth.assertThat(currentList).isEqualTo(movie)

    }

    @Test
    fun updateMovies_returnUpdatedList() {
        val movie = mutableListOf<Movie>()

        movie.add(
            Movie(
                id = 3,
                overview = "overview3",
                posterPath = "posterPath3",
                releaseDate = "releaseDate3",
                title = "title3"
            )
        )
        movie.add(
            Movie(
                id = 4,
                overview = "overview4",
                posterPath = "posterPath4",
                releaseDate = "releaseDate4",
                title = "title4"
            )
        )

        val updatedList = movieViewModel.updateMoviesList().getOrAwaitValue()
        Truth.assertThat(updatedList).isEqualTo(movie)
    }
}