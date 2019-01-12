package arch.kay.kaymovie.api

import arch.kay.kaymovie.entity.Movie
import arch.kay.kaymovie.entity.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
interface MovieService {

    @GET("/3/movie/top_rated?language=en")
    fun getTopRated(@Query("page") page: Int): retrofit2.Call<MovieResponse>

    @GET("/3/movie/now_playing?language=en")
    fun getNowPlaying(@Query("page") page: Int):retrofit2.Call<MovieResponse>

    @GET("/3/movie/{move_id}")
    fun getMovie(@Path("move_id") id: Long): retrofit2.Call<Movie>

}