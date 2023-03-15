package com.anushka.tmdbclient.presentation.di.core

import android.content.Context
import androidx.room.Room
import com.anushka.tmdbclient.data.db.ArtistDAO
import com.anushka.tmdbclient.data.db.MovieDAO
import com.anushka.tmdbclient.data.db.TMDBDatabase
import com.anushka.tmdbclient.data.db.TvShowDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun provideTMDBDatabase(context: Context): TMDBDatabase {
        return Room.databaseBuilder(context, TMDBDatabase::class.java, "tmdb_data_base").build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(tmdbDatabase: TMDBDatabase): MovieDAO {
        return tmdbDatabase.movieDao()
    }

    @Singleton
    @Provides
    fun provideArtistDao(tmdbDatabase: TMDBDatabase): ArtistDAO {
        return tmdbDatabase.artistsDao()
    }

    @Singleton
    @Provides
    fun provideTvShowDao(tmdbDatabase: TMDBDatabase): TvShowDAO {
        return tmdbDatabase.tvShowDao()
    }

}