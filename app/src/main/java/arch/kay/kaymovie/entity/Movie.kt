package arch.kay.kaymovie.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import com.squareup.moshi.JsonClass
import java.util.*


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
@Entity(primaryKeys = [("id")])
data class Movie(
    var page: Int,
    val poster_path: String?,
    val adult: Boolean,
    val overview: String,
    val release_date: Date,
    val id: Long,
    val original_title: String,
    val original_language: String,
    val title: String,
    val backdrop_path: String?,
    val popularity: Float,
    val vote_count: Int,
    val video: Boolean,
    val runtime: Int,
    val vote_average: Float


) {
    @Ignore
    val genres: List<Genre> = emptyList()
}

data class Genre(val id: Int, val name: String)




