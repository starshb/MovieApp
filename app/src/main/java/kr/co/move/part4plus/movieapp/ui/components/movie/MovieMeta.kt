package kr.co.move.part4plus.movieapp.ui.components.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kr.co.move.part4plus.movieapp.ui.theme.Paddings
import kr.co.move.part4plus.movieapp.ui.util.getAnnotatedText

@Composable
fun MovieMeta(
    modifier: Modifier = Modifier,
    key: String,
    value: String
) {
    Column(modifier = modifier) {
        // Key
        Text(
            text = key,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
        )

        // Value
        Text(
            text = getAnnotatedText(text = value),
            style = MaterialTheme.typography.body2
        )

        Spacer(modifier = Modifier.height(Paddings.large))
    }
}
