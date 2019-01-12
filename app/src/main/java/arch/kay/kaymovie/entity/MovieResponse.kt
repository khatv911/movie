package arch.kay.kaymovie.entity

import com.squareup.moshi.JsonClass


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */

data class MovieResponse(val page: Int,
                                 val results: List<Movie>,
                                 val total_results: Int,
                                 val total_pages: Int)