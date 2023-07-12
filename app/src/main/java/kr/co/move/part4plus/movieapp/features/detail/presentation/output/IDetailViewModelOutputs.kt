package kr.co.move.part4plus.movieapp.features.detail.presentation.output

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface IDetailViewModelOutputs {
    val detailState: StateFlow<MovieDetailState>
    val detailUiEffect: SharedFlow<DetailUiEffect>
}

sealed class DetailUiEffect {
    object Back : DetailUiEffect()

    data class OpenUrl(
        val url: String
    ) : DetailUiEffect()

    data class RateMovie(
        val movieTitle: String,
        val rating: Float
    ) : DetailUiEffect()
}
