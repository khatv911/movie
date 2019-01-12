package arch.kay.kaymovie.repository

import arch.kay.kaymovie.api.MovieService
import arch.kay.kaymovie.db.MovieDao
import arch.kay.kaymovie.entity.Movie
import arch.kay.kaymovie.entity.MovieResponse
import retrofit2.Call
import javax.inject.Inject


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
class TopRatedRepository @Inject constructor(private val movieService: MovieService, dao: MovieDao) : MovieRepository(dao) {

    override fun createNetworkCall(page: Int): Call<MovieResponse>  = movieService.getTopRated(page)

}