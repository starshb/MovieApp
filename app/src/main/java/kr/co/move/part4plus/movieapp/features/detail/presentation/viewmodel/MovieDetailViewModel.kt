package kr.co.move.part4plus.movieapp.features.detail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kr.co.move.part4plus.movieapp.features.detail.domain.usecase.IGetMovieDetailUseCase
import kr.co.move.part4plus.movieapp.features.detail.presentation.input.IDetailViewModelInputs
import kr.co.move.part4plus.movieapp.features.detail.presentation.output.DetailUiEffect
import kr.co.move.part4plus.movieapp.features.detail.presentation.output.IDetailViewModelOutputs
import kr.co.move.part4plus.movieapp.features.detail.presentation.output.MovieDetailState
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: IGetMovieDetailUseCase
) : ViewModel(), IDetailViewModelInputs, IDetailViewModelOutputs {

    val inputs: IDetailViewModelInputs = this
    val outputs: IDetailViewModelOutputs = this

    private val _detailState: MutableStateFlow<MovieDetailState> =
        MutableStateFlow(MovieDetailState.Initial)
    override val detailState: StateFlow<MovieDetailState>
        get() = _detailState

    private val _detailUiEffect = MutableSharedFlow<DetailUiEffect>(replay = 0)
    override val detailUiEffect: SharedFlow<DetailUiEffect>
        get() = _detailUiEffect

    suspend fun initMovieName(movieName: String) {
        _detailState.value = MovieDetailState.Main(
            movieDetailEntity = getMovieDetailUseCase(movieName)
        )
    }

    override fun goBackToFeed() {
        viewModelScope.launch {
            _detailUiEffect.emit(
                DetailUiEffect.Back
            )
        }
    }

    override fun openImdbClicked() {
        viewModelScope.launch {
            if (detailState.value is MovieDetailState.Main) {
                val value = detailState.value as MovieDetailState.Main
                _detailUiEffect.emit(
                    DetailUiEffect.OpenUrl(
                        value.movieDetailEntity.imdbPath
                    )
                )
            }
        }
    }

    override fun rateClicked() {
        viewModelScope.launch {
            if (detailState.value is MovieDetailState.Main) {
                val value = detailState.value as MovieDetailState.Main
                _detailUiEffect.emit(
                    DetailUiEffect.RateMovie(
                        movieTitle = value.movieDetailEntity.title,
                        rating = value.movieDetailEntity.rating
                    )
                )
            }
        }
    }
}
