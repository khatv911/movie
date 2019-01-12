package arch.kay.kaymovie.utils


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
object ImagesPathHelper {

    private const val BASE_PATH = "https://image.tmdb.org/t/p/"


    private val BACKDROP_SIZES = arrayOf(
        "w300",
        "w780",
        "w1280",
        "original"
    )

    private val POSTER_SIZE = arrayOf(
        "w92",
        "w154",
        "w185",
        "w342",
        "w500",
        "w780",
        "original"
    )

    private val PROFILE_SIZE = arrayOf(
        "w45",
        "w185",
        "original"
    )

    fun toPosterPath(minWidth: Int, path: String) =
        "$BASE_PATH${POSTER_SIZE[
                when {
                    minWidth <= 92 -> 0
                    minWidth <= 154 -> 1
                    minWidth <= 185 -> 2
                    minWidth <= 342 -> 3
                    minWidth <= 500 -> 4
                    minWidth <= 780 -> 5
                    else -> 6
                }
        ]}$path"


    fun toBackDropPath(minWidth: Int, path: String) =
        "$BASE_PATH${BACKDROP_SIZES[
                when {
                    minWidth <= 300 -> 0
                    minWidth <= 780 -> 1
                    minWidth <= 1280 -> 2
                    else -> 3
                }
        ]}$path"

    fun toProfilePath(minWidth: Int, path: String) =
        "$BASE_PATH${PROFILE_SIZE[
                when {
                    minWidth <= 45 -> 0
                    minWidth <= 185 -> 1
                    else -> 2
                }
        ]}$path"
}