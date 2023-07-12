package kr.co.move.part4plus.movieapp.features.common.network.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("actors")
    val actors: List<String>,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("directors")
    val directors: List<String>,
    @SerializedName("genre")
    val genre: List<String>,
    @SerializedName("image_url")
    val imageUrl: String, // https://m.media-amazon.com/images/M/MV5BZThiZjAzZjgtNDU3MC00YThhLThjYWUtZGRkYjc2ZWZlOTVjXkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_.jpg
    @SerializedName("thumb_url")
    val thumbUrl: String,
    @SerializedName("imdb_url")
    val imdbPath: String, // /title/tt0055630/
    @SerializedName("name")
    val title: String, // Clementine
    @SerializedName("rating")
    val rating: Float, // 8.2
    @SerializedName("year")
    val year: Int?
)
