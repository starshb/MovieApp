package kr.co.move.part4plus.movieapp.features.detail.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import kr.co.fastcampus.part4plus.movieapp.R
import kr.co.move.part4plus.movieapp.features.common.entity.MovieDetailEntity
import kr.co.move.part4plus.movieapp.features.detail.presentation.input.IDetailViewModelInputs
import kr.co.move.part4plus.movieapp.features.detail.presentation.output.MovieDetailState
import kr.co.move.part4plus.movieapp.ui.components.buttons.PrimaryButton
import kr.co.move.part4plus.movieapp.ui.components.buttons.SecondaryButton
import kr.co.move.part4plus.movieapp.ui.components.movie.MovieMeta
import kr.co.move.part4plus.movieapp.ui.theme.Paddings
import kr.co.move.part4plus.movieapp.ui.util.getAnnotatedText


@Composable
fun MovieDetailScreen(
    movieDetailState: State<MovieDetailState>,
    input: IDetailViewModelInputs
) {
    val movieDetail by movieDetailState

    if (movieDetail is MovieDetailState.Main) {
        MovieDetail(
            movie = (movieDetail as MovieDetailState.Main).movieDetailEntity,
            input = input
        )
    }

    BackHandler {
        input.goBackToFeed()
    }
}

@Composable
fun MovieDetail(
    movie: MovieDetailEntity,
    input: IDetailViewModelInputs
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                modifier = Modifier.requiredHeight(70.dp),
                navigationIcon = {
                    IconButton(onClick = { input.goBackToFeed() }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                            contentDescription = null
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 0.dp
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = Paddings.extra
                )
                .scrollable(
                    state = scrollState,
                    orientation = Orientation.Vertical
                )
        ) {
            Row {
                // Poster
                BigPoster(
                    movie = movie
                )

                // Meta
                Column(
                    modifier = Modifier.padding(start = Paddings.xlarge)
                ) {
                    // Rating
                    MovieMeta(
                        key = "Rating",
                        value = movie.rating.toString()
                    )

                    // Director
                    MovieMeta(
                        key = "Director",
                        value = movie.directors.joinToString(separator = ", ")
                    )

                    // Starring
                    MovieMeta(
                        key = "Starring",
                        value = movie.actors.joinToString(separator = ", ")
                    )

                    // Genre
                    MovieMeta(
                        key = "Genre",
                        value = movie.genre.joinToString(separator = ", ")
                    )
                }
            }

            // Title
            Text(
                text = getAnnotatedText(text = movie.title),
                modifier = Modifier.padding(
                    top = Paddings.extra,
                    bottom = Paddings.large
                ),
                style = MaterialTheme.typography.h3
            )

            // Desc
            Text(
                text = getAnnotatedText(text = movie.desc),
                modifier = Modifier.padding(
                    bottom = Paddings.xlarge
                ),
                style = MaterialTheme.typography.body2
            )

            // Rating
            PrimaryButton(
                modifier = Modifier
                    .padding(top = Paddings.xlarge)
                    .fillMaxWidth(),
                text = "Add Rating Score",
                onClick = {
                    input.rateClicked()
                }
            )

            // IMDB Button
            SecondaryButton(
                modifier = Modifier
                    .padding(top = Paddings.xlarge)
                    .fillMaxWidth(),
                text = "OPEN IMDB",
                onClick = {
                    input.openImdbClicked()
                }
            )
        }
    }
}

@Composable
fun BigPoster(
    movie: MovieDetailEntity
) {
    Card {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest
                    .Builder(LocalContext.current)
                    .data(data = movie.thumbUrl)
                    .apply {
                        crossfade(true)
                        scale(Scale.FILL)
                    }.build()
            ),
            modifier = Modifier
                .width(180.dp)
                .height(250.dp),
            contentScale = ContentScale.FillHeight,
            contentDescription = "Movie Poster Image"
        )
    }
}
