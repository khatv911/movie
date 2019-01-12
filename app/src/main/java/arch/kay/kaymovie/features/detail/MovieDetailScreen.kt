package arch.kay.kaymovie.features.detail

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.core.os.bundleOf
import arch.kay.kaymovie.R
import arch.kay.kaymovie.entity.Movie
import arch.kay.kaymovie.features.detail.MovieDetailActivity.Companion.MOVIE
import arch.kay.kaymovie.shared.simple.SimpleRecyclerViewFragment
import arch.kay.kaymovie.shared.utils.getScreenWidth
import arch.kay.kaymovie.shared.utils.inject
import arch.kay.kaymovie.utils.ImagesPathHelper
import arch.kay.kaymovie.utils.year
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_movie_detail.*


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
class MovieDetailScreen : SimpleRecyclerViewFragment<Movie, MovieDetailViewModel>() {


    private var movieId = -1L

    override fun setupRecyclerView() {

    }


    override fun doOnRefresh() {
        //NOOP
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieId = arguments?.getLong(MOVIE)!!
    }

    override fun getViewModel(): MovieDetailViewModel =
        VIEW_MODEL_FACTORY.inject(this, MovieDetailViewModel::class.java)

    override fun onDataChanged(t: Movie?) {
        t?.run {
            backdrop_path?.let {
                Glide.with(this@MovieDetailScreen)
                    .load(ImagesPathHelper.toBackDropPath(getScreenWidth(), it))
                    .apply(RequestOptions().centerCrop())
                    .into(ivBackDrop)
            }
            tvTitleAndYear.text = String.format("%s (%d)", title, release_date.year())

            tvMics.text = getString(
                R.string.misc,
                getString(R.string.rating, vote_average),
                getString(R.string.duration, runtime),
                getString(R.string.genres, genres.joinToString { it.name })
            )

            tvOverview.text = overview


        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolBar.setNavigationOnClickListener {
            requireActivity().finish()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel.loadMovie(movieId)

    }

    override fun getLayoutId(): Int = R.layout.fragment_movie_detail

    companion object {
        fun newInstance(id: Long) = MovieDetailScreen().apply {
            arguments = bundleOf(MOVIE to id)
        }
    }
}