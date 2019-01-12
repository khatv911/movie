package arch.kay.kaymovie.features.movie

import android.os.Bundle
import android.view.View
import androidx.core.view.postDelayed
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import arch.kay.kaymovie.R
import arch.kay.kaymovie.entity.Movie
import arch.kay.kaymovie.features.detail.MovieDetailActivity
import arch.kay.kaymovie.shared.simple.SimpleRecyclerViewFragment
import arch.kay.kaymovie.utils.InfiniteScrollListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
abstract class MoviesScreen : SimpleRecyclerViewFragment<List<Movie>, MoviesViewModel>() {


    private var posterWidth: Int = 100
    private var posterHeight: Int = 150


    private var adapter = GroupAdapter<ViewHolder>()

    private lateinit var scrollListener: InfiniteScrollListener
    private lateinit var layoutManager: LinearLayoutManager


    override fun doOnRefresh() {
        scrollListener.reset()
        mViewModel.refresh()
    }


    override fun onDataChanged(t: List<Movie>?) {
        if (t?.isEmpty() != false) return
        adapter.update(t.map { MovieItem(it, posterWidth, posterHeight) })
    }

    override fun getLayoutId(): Int = R.layout.fragment_movie


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutManager = LinearLayoutManager(requireContext())
        scrollListener = object : InfiniteScrollListener(layoutManager) {
            override fun onLoadMore(current_page: Int) {
                mViewModel.loadPage(current_page + 1)
            }
        }

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        posterWidth = resources.getDimensionPixelSize(R.dimen.mv_poster_width)
        posterHeight = resources.getDimensionPixelSize(R.dimen.mv_poster_height)

    }

    override fun setupRecyclerView() {

        mRecyclerView.layoutManager = layoutManager
        mRecyclerView.adapter = adapter
        adapter.setOnItemClickListener { item, view ->
            val movie = (item as MovieItem).movie
            startActivity(MovieDetailActivity.newIntent(requireContext(), movie.id))
        }
            // still can't figure it out
//        mRecyclerView.addOnScrollListener(scrollListener)
        mRecyclerView.addItemDecoration(
            MovieItemDecoration(
                requireContext(),
                resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin),
                0
            )
        )

    }


}