package kr.co.move.part4plus.movieapp.features.common.repository

import kr.co.move.part4plus.movieapp.features.common.entity.CategoryEntity
import kr.co.move.part4plus.movieapp.features.common.entity.EntityWrapper
import kr.co.move.part4plus.movieapp.features.common.entity.MovieDetailEntity
import kr.co.move.part4plus.movieapp.features.feed.domain.enum.SortOrder

interface IMovieDataSource {
    suspend fun getCategories(sortOrder: SortOrder? = null): EntityWrapper<List<CategoryEntity>>
    suspend fun getMovieDetail(movieName: String): MovieDetailEntity
}
