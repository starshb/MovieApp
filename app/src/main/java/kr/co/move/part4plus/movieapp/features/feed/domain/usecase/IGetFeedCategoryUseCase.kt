package kr.co.move.part4plus.movieapp.features.feed.domain.usecase

import kr.co.move.part4plus.movieapp.features.common.entity.CategoryEntity
import kr.co.move.part4plus.movieapp.features.common.entity.EntityWrapper
import kr.co.move.part4plus.movieapp.features.feed.domain.enum.SortOrder

interface IGetFeedCategoryUseCase {
    suspend operator fun invoke(sortOrder: SortOrder? = null): EntityWrapper<List<CategoryEntity>>
}
