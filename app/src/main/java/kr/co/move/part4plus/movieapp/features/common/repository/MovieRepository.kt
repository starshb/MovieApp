package kr.co.move.part4plus.movieapp.features.common.repository

import kr.co.move.part4plus.movieapp.features.common.entity.CategoryEntity
import kr.co.move.part4plus.movieapp.features.common.entity.EntityWrapper
import kr.co.move.part4plus.movieapp.features.common.entity.MovieDetailEntity
import kr.co.move.part4plus.movieapp.features.common.network.api.IMovieAppNetworkApi
import kr.co.move.part4plus.movieapp.features.feed.data.FeedConstants
import kr.co.move.part4plus.movieapp.features.feed.data.mapper.CategoryMapper
import kr.co.move.part4plus.movieapp.features.feed.domain.enum.SortOrder
import kr.co.move.part4plus.movieapp.library.storage.IStorage
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieNetworkApi: IMovieAppNetworkApi,
    private val storage: IStorage,
    private val categoryMapper: CategoryMapper
) : IMovieDataSource {
    override suspend fun getCategories(sortOrder: SortOrder?): EntityWrapper<List<CategoryEntity>> {
        return categoryMapper.mapFromResult(
            result = movieNetworkApi.getMovies(),
            extra = sortOrder
        )
    }

    override suspend fun getMovieDetail(movieName: String): MovieDetailEntity {
        return storage
            .get<List<MovieDetailEntity>>(FeedConstants.MOVIE_LIST_KEY)
            ?.single { it.title == movieName }
            ?: MovieDetailEntity()
    }
}
