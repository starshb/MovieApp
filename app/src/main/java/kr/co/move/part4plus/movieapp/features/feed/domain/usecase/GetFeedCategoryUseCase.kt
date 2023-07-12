package kr.co.move.part4plus.movieapp.features.feed.domain.usecase

import kr.co.move.part4plus.movieapp.features.common.entity.CategoryEntity
import kr.co.move.part4plus.movieapp.features.common.entity.EntityWrapper
import kr.co.move.part4plus.movieapp.features.common.repository.IMovieDataSource
import kr.co.move.part4plus.movieapp.features.feed.domain.enum.SortOrder
import javax.inject.Inject

class GetFeedCategoryUseCase @Inject constructor(
    private val dataSource: IMovieDataSource
) : IGetFeedCategoryUseCase {
    override suspend fun invoke(sortOrder: SortOrder?): EntityWrapper<List<CategoryEntity>> {
        return dataSource.getCategories(sortOrder)
    }
}
