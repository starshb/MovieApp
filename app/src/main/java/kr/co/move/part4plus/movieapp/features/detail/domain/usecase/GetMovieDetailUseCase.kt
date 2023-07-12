package kr.co.move.part4plus.movieapp.features.detail.domain.usecase

import kr.co.move.part4plus.movieapp.features.common.entity.MovieDetailEntity
import kr.co.move.part4plus.movieapp.features.common.repository.IMovieDataSource
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val dataSource: IMovieDataSource
) : IGetMovieDetailUseCase {
    override suspend fun invoke(name: String): MovieDetailEntity {
        return dataSource.getMovieDetail(name)
    }
}
