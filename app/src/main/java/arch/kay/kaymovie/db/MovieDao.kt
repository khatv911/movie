package arch.kay.kaymovie.db

import androidx.lifecycle.LiveData
import androidx.room.*
import arch.kay.kaymovie.entity.Movie


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(movies: List<Movie>)

    @Query("SELECT * FROM MOVIE WHERE id = :id_")
    fun getMovie(id_: Int): Movie

    @Query("SELECT * FROM Movie")
    fun getMovieList(): LiveData<List<Movie>>

    @Query("delete from movie")
    fun removeAll()

}