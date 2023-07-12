package kr.co.move.part4plus.movieapp.features.common.entity

data class CategoryEntity(
    val id: Int,
    val genre: String,
    val movieFeedEntities: List<MovieFeedItemEntity>
)
