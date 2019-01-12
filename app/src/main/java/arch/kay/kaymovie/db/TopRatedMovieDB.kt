package arch.kay.kaymovie.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import arch.kay.kaymovie.entity.Movie
import arch.kay.kaymovie.utils.DateTimeConverter


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */

@Database(entities = [(Movie::class)], version = 1, exportSchema = false)
@TypeConverters(value = [(DateTimeConverter::class)])
abstract class TopRatedMovieDB : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}