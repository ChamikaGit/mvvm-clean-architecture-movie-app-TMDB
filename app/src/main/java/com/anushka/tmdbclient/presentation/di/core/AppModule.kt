package com.anushka.tmdbclient.presentation.di.core

import android.content.Context
import com.anushka.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.anushka.tmdbclient.presentation.di.movie.MovieSubComponent
import com.anushka.tmdbclient.presentation.di.tvshow.TvShowSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [ArtistSubComponent::class, MovieSubComponent::class, TvShowSubComponent::class])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }

}