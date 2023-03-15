package com.anushka.tmdbclient.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anushka.tmdbclient.data.model.movie.Movie
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDAOTest {

    /**for test the architecture component synchronously we need to used
     * InstantTaskExecutorRule
     */

    /** need to add android.enableJetifier=true on gradle.properties file
     * when you used gradle 8
     */

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieDAO: MovieDAO
    private lateinit var database: TMDBDatabase


    @Before
    fun setUp() {

        //initialize the in-memory database for testing and the movie dao instance
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TMDBDatabase::class.java
        ).build()

        movieDAO = database.movieDao()

    }

    @After
    fun tearDown() {
        //this used to close the database after done the testing
        database.close()

    }

    @Test
    fun saveAllMoviesTest() = runBlocking {

        val movies = listOf(
            Movie(
                id = 1,
                overview = "overview1",
                posterPath = "posterPath1",
                releaseDate = "releaseDate1",
                "title1"
            ),
            Movie(
                id = 2,
                overview = "overview2",
                posterPath = "posterPath2",
                releaseDate = "releaseDate2",
                "title2"
            ),
            Movie(
                id = 3,
                overview = "overview3",
                posterPath = "posterPath3",
                releaseDate = "releaseDate3",
                "title3"
            ),
            Movie(
                id = 4,
                overview = "overview4",
                posterPath = "posterPath4",
                releaseDate = "releaseDate41",
                "title4"
            )
        )
        movieDAO.saveAllMovies(movie = movies)

        val allMovies = movieDAO.getAllMovies()
        Truth.assertThat(allMovies).isEqualTo(movies)
    }

    @Test
    fun deleteMovies() = runBlocking {
        val movies = listOf(
            Movie(
                id = 1,
                overview = "overview1",
                posterPath = "posterPath1",
                releaseDate = "releaseDate1",
                "title1"
            ),
            Movie(
                id = 2,
                overview = "overview2",
                posterPath = "posterPath2",
                releaseDate = "releaseDate2",
                "title2"
            ),
            Movie(
                id = 3,
                overview = "overview3",
                posterPath = "posterPath3",
                releaseDate = "releaseDate3",
                "title3"
            ),
            Movie(
                id = 4,
                overview = "overview4",
                posterPath = "posterPath4",
                releaseDate = "releaseDate41",
                "title4"
            )
        )
        movieDAO.saveAllMovies(movies)

        movieDAO.deleteAllMovies()

        val result = movieDAO.getAllMovies()

        Truth.assertThat(result).isEmpty()

    }
}