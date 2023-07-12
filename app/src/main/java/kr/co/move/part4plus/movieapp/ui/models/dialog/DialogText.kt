package kr.co.move.part4plus.movieapp.ui.models.dialog

sealed class DialogText {
    abstract var text: String?
    abstract var id: Int?

    class Default() : DialogText() {
        override var text: String? = null
        override var id: Int? = null

        constructor(text: String) : this() {
            this.text = text
        }

        constructor(text: Int) : this() {
            this.id = text
        }
    }

    class Rating() : DialogText() {
        override var text: String? = null
        override var id: Int? = null
        var rating: Float = 0.0f

        constructor(text: String, rating: Float) : this() {
            this.text = text
            this.rating = rating
        }

        constructor(text: Int, rating: Float) : this() {
            this.id = text
            this.rating = rating
        }
    }
}
