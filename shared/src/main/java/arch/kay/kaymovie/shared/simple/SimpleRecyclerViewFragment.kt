package arch.kay.kaymovie.shared.simple

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import arch.kay.kaymovie.shared.R
import arch.kay.kaymovie.shared.utils.LoadingState
import timber.log.Timber

abstract class SimpleRecyclerViewFragment<T, VM : SimpleDataModel<T>> : SimpleDataFragment<T, VM>() {


    @LoadingState.Value
    private var mLoadingState: Int = LoadingState.NONE

    /**
     *
     */
    protected var mSwipeRefreshLayout: SwipeRefreshLayout? = null

    /**
     *
     */
    protected lateinit var mRecyclerView: RecyclerView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        mSwipeRefreshLayout = view.findViewById(R.id.base_refresh_layout)

        mRecyclerView = view.findViewById(R.id.base_recycler_view)

        checkNotNull(mRecyclerView) { Timber.e("Layout must has an RecyclerView with id base_recycler_view") }

        setupRecyclerView()

        mSwipeRefreshLayout?.setOnRefreshListener {
            doOnRefresh()
        }
    }


    override fun onLoadingStateChanged(@LoadingState.Value loadingState: Int) {
        if (loadingState == LoadingState.NONE) {
            when (mLoadingState) {
                LoadingState.NONE -> {
                    /** nothing to do*/
                }
                LoadingState.NORMAL -> renderLoading(false)
                LoadingState.REFRESHING -> mSwipeRefreshLayout?.isRefreshing = false
                LoadingState.MORE -> renderLoadingMore(false)
            }
        } else {
            when (loadingState) {
                LoadingState.MORE -> renderLoadingMore(true)
                LoadingState.NORMAL -> renderLoading(true)
                LoadingState.REFRESHING -> mSwipeRefreshLayout?.isRefreshing = true
                LoadingState.NONE -> {
                    /** handled already*/
                }
            }

        }

        mLoadingState = loadingState
    }

    protected open fun renderLoading(show: Boolean) {
        if (show)
            showLoadingView()
        else
            hideLoadingView()
    }

    /**
     * render load more requestStateEvent.
     */
    protected open fun renderLoadingMore(boolean: Boolean) {}


    /**
     * set layout manager, item decoration and animation, and adapter
     */
    abstract fun setupRecyclerView()

    /**
     * Do on refresh
     */
    abstract fun doOnRefresh()
}