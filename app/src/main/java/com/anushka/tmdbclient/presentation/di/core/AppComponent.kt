package com.anushka.tmdbclient.presentation.di.core

import com.anushka.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.anushka.tmdbclient.presentation.di.movie.MovieSubComponent
import com.anushka.tmdbclient.presentation.di.tvshow.TvShowSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        NetModule::class,
        DataBaseModule::class,
        RepositoryModule::class,
        RemoteDataSourceModule::class,
        LocalDataSourceModule::class,
        CacheDataSourceModule::class,
        UseCaseModule::class]
)
interface AppComponent {

    fun movieSubComponent() : MovieSubComponent.Factory
    fun artistSubComponent() : ArtistSubComponent.Factory
    fun tvShowSubComponent() : TvShowSubComponent.Factory



}