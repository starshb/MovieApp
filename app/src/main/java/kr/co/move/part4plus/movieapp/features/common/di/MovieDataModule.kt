package kr.co.move.part4plus.movieapp.features.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.move.part4plus.movieapp.features.common.network.api.IMovieAppNetworkApi
import kr.co.move.part4plus.movieapp.features.common.network.api.MovieAppNetworkApi
import kr.co.move.part4plus.movieapp.features.common.repository.IMovieDataSource
import kr.co.move.part4plus.movieapp.features.common.repository.MovieRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieDataModule {

    @Singleton
    @Binds
    abstract fun bindMovieRepository(movieRepository: MovieRepository): IMovieDataSource

    @Singleton
    @Binds
    abstract fun bindNetwork(networkApi: MovieAppNetworkApi): IMovieAppNetworkApi

}
