package kr.co.move.part4plus.movieapp.features.detail.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.move.part4plus.movieapp.features.detail.domain.usecase.GetMovieDetailUseCase
import kr.co.move.part4plus.movieapp.features.detail.domain.usecase.IGetMovieDetailUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DetailUseCaseModule {

    @Singleton
    @Binds
    abstract fun bindGetMovieDetailUseCase(getMovieDetailUseCase: GetMovieDetailUseCase): IGetMovieDetailUseCase
}
