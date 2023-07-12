package kr.co.move.part4plus.movieapp.ui.models.dialog

sealed class DialogContent {
    data class Default(
        val dialogText: DialogText.Default
    ) : DialogContent()

    data class Large(
        val dialogText: DialogText.Default
    ) : DialogContent()

    data class Rating(
        val dialogText: DialogText.Rating
    ) : DialogContent()
}
