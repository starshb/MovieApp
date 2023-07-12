package kr.co.move.part4plus.movieapp.ui.components.dialog.components.content

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.em
import kr.co.move.part4plus.movieapp.ui.models.dialog.DialogContent
import kr.co.move.part4plus.movieapp.ui.models.dialog.DialogText
import kr.co.move.part4plus.movieapp.ui.theme.Paddings

data class DialogContentStyle(
    val textStyle: @Composable () -> TextStyle = { MaterialTheme.typography.body1 },
    val contentTopPadding: Dp = Paddings.xlarge,
    val contentBottomPadding: Dp = Paddings.extra
)

val LocalDialogContentStyle = compositionLocalOf { DialogContentStyle() }

@Composable
fun ColumnScope.DialogContentWrapper(content: DialogContent) {
    when (content) {
        is DialogContent.Default -> {
            CompositionLocalProvider(
                LocalDialogContentStyle provides DialogContentStyle(
                    textStyle = {
                        MaterialTheme.typography.body2.copy(
                            lineHeight = 1.6.em
                        )
                    },
                    contentTopPadding = Paddings.small,
                    contentBottomPadding = Paddings.extra
                )
            ) {
                NormalTextContent(content.dialogText)
            }
        }

        is DialogContent.Large -> {
            CompositionLocalProvider(
                LocalDialogContentStyle provides DialogContentStyle(
                    textStyle = {
                        MaterialTheme.typography.subtitle1.copy(
                            lineHeight = 1.6.em
                        )
                    },
                    contentTopPadding = Paddings.extra,
                    contentBottomPadding = Paddings.extra
                )
            ) {
                NormalTextContent(content.dialogText)
            }
        }

        is DialogContent.Rating -> {
            RatingContent(content.dialogText)
        }
    }
}


@Composable
fun ColumnScope.NormalTextContent(text: DialogText.Default) {
    Text(
        text = getStringFromDialogText(text),
        modifier = Modifier
            .padding(
                top = LocalDialogContentStyle.current.contentTopPadding,
                bottom = LocalDialogContentStyle.current.contentBottomPadding
            )
            .align(Alignment.CenterHorizontally),
        textAlign = TextAlign.Center,
        style = LocalDialogContentStyle.current.textStyle.invoke()
    )
}

@Composable
fun getStringFromDialogText(text: DialogText.Default): String =
    text.id?.let {
        stringResource(id = it)
    } ?: text.text ?: ""

