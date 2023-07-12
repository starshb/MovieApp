package kr.co.move.part4plus.movieapp.ui.components.dialog

import androidx.compose.runtime.Composable
import kr.co.move.part4plus.movieapp.ui.models.dialog.DialogButton
import kr.co.move.part4plus.movieapp.ui.models.dialog.DialogContent
import kr.co.move.part4plus.movieapp.ui.models.dialog.DialogText
import kr.co.move.part4plus.movieapp.ui.models.dialog.DialogTitle

@Composable
fun DialogPopup.Alert(
    title: String,
    bodyText: String,
    buttons: List<DialogButton>
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Header(
            title
        ),
        dialogContent = DialogContent.Large(
            DialogText.Default(
                bodyText
            )
        ),
        buttons = buttons
    )
}
