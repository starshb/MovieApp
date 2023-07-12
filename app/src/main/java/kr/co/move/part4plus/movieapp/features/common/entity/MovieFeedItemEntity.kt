package kr.co.move.part4plus.movieapp.features.common.entity

import java.io.Serializable

open class MovieFeedItemEntity(
    open val genre: List<String> = listOf(),
    open val thumbUrl: String = "",
    open val title: String = "",
    open val rating: Float = 0F,
    open val year: Int? = 0
) : Serializable
