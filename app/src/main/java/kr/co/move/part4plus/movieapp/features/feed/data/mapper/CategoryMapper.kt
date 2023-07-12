package kr.co.move.part4plus.movieapp.features.feed.data.mapper

import kr.co.move.part4plus.movieapp.features.common.entity.CategoryEntity
import kr.co.move.part4plus.movieapp.features.common.entity.EntityWrapper
import kr.co.move.part4plus.movieapp.features.common.entity.MovieDetailEntity
import kr.co.move.part4plus.movieapp.features.common.mapper.BaseMapper
import kr.co.move.part4plus.movieapp.features.common.network.model.MovieResponse
import kr.co.move.part4plus.movieapp.features.feed.data.FeedConstants
import kr.co.move.part4plus.movieapp.features.feed.domain.enum.SortOrder
import kr.co.move.part4plus.movieapp.library.storage.IStorage
import javax.inject.Inject

class CategoryMapper @Inject constructor(
    private val storage: IStorage
) : BaseMapper<List<MovieResponse>, List<CategoryEntity>>() {
    override fun getSuccess(
        model: List<MovieResponse>?,
        extra: Any?
    ): EntityWrapper.Success<List<CategoryEntity>> {
        return model?.let {
            EntityWrapper.Success(
                entity = mutableListOf<MovieDetailEntity>()
                    .apply {
                        addAll(model.map { it.toMovieDetailEntity() })
                    }
                    .also {
                        storage.save(FeedConstants.MOVIE_LIST_KEY, it)
                    }
                    .map {
                        it.toMovieThumbnailEntity()
                    }
                    .toCategoryList(if (extra is SortOrder) extra else SortOrder.RATING)
            )
        } ?: EntityWrapper.Success(
            entity = listOf()
        )
    }

    override fun getFailure(error: Throwable): EntityWrapper.Fail<List<CategoryEntity>> {
        return EntityWrapper.Fail(error)
    }
}
