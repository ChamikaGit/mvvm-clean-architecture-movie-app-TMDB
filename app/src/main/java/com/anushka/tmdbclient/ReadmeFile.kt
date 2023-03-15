package com.anushka.tmdbclient

/** This file is used to note down the steps of MVVM Clean architecture.
 *
 * There is mainly 3 layers,
 *
 * -------------------------------------------------------------------
 * @Presentation_Layer(views,layout,activity,fragments and ViewModels)
 * ------------------------------------------------------------------
 * |
 * --------------------------------------------------------------------
 * @Domain_Layer(UseCase and Repository Interfaces)
 * ------------------------------------------------------------------
 * |
 * ------------------------------------------------------------------------
 * @Data_layer(Remote DataSource,Local DataSource or Cache DataSource
 * and Repository classes connected with domain layer repository interfaces (MovieRepositoryImpl-classes))
 * ----------------------------------------------------------------------
 *
 * Creating single superpose smaller classes is the recommended software engineering best practice.(Clean architecture)- S.O.L.I.D Principal
 *
 * IN NORMALLY REAL WORLD PROJECT WE NEED TO START THE PROJECT WITH DOMAIN LAYER AFTER IDENTIFY THE USE-CASES
 *
 *
 * In this project use-cases are,
 *
 * view movies and update movies.
 * view artist and update artists
 * view tvShows and update tvShows
 *
 *
 *
 * 1) First we start from the Data layer
 *
 *      Start to create the Data classes and Entity classes
 *      then create a retrofit Interfaces(return type is ex- Response<MovieList>)
 *
 *      *Then start to create entity classes(if we use local database functions)
 *      *Then start to create the DAO interfaces.
 *      *Then Database instance.
 *
 *
 *
 * 2)Second we start from the Domain Layer
 *
 *
 * after identify the use-cases create UseCase classes (ex:- class GetMovieUsecase)
 *
 * Use-case directly can't communicate with datalayer for that we need to do the repository interfaces(abstract).
 *
 * Then UseCase class can start communicate for that, we need to inject
 * the repository interface to UseCase as a constructor parameters.
 *
 * Then we can implement the "suspend fun execute()" function on Use-Case class and can return the relevant data
 *
 *--------------------------------------------------------------------------
 *after done above mentioned things we need to switch again to data layer
 *then need to create a repository implementation classes and that relevant data source interfaces and data source classes
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */