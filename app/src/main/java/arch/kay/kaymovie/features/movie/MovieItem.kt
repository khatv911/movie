package arch.kay.kaymovie.features.movie

import arch.kay.kaymovie.R
import arch.kay.kaymovie.entity.Movie
import arch.kay.kaymovie.utils.ImagesPathHelper
import arch.kay.kaymovie.utils.toDayMonYear
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_view_movie.view.*


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
class MovieItem(val movie: Movie, private val photoWidth: Int = 100, private val photoHeight: Int = 150) :
    Item(movie.id) {
    override fun bind(viewHolder: ViewHolder, position: Int) {

        with(viewHolder.itemView) {
            Glide.with(context)
                .load(ImagesPathHelper.toProfilePath(photoWidth, movie.poster_path ?: ""))
                .apply(RequestOptions().override(photoWidth, photoHeight))
                .into(ivPoster)
            tvTitle.text = movie.title
            tvYear.text = movie.release_date.toDayMonYear()
            tvVotes.text = "${movie.vote_count}"
            tvRating.text = "${movie.vote_average}"
            tvOverview.text = movie.overview
        }

    }

    override fun getLayout(): Int = R.layout.item_view_movie
}