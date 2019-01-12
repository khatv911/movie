package arch.kay.kaymovie.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
abstract class InfiniteScrollListener(private var linearLayoutManager: LinearLayoutManager?) :
    RecyclerView.OnScrollListener() {

    private var previousTotal = 0 // The total number of items in the dataset after the last load
    private var loading = true // True if we are still waiting for the last set of data to load.
    private val visibleThreshold =
        5 // The minimum amount of items to have below your current scroll position before loading more.
    private var firstVisibleItem: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0
    private var currentPage = 0
    private val loadMore = Runnable { onLoadMore(currentPage) }

    fun setLinearLayoutManager(linearLayoutManager: LinearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        visibleItemCount = recyclerView.childCount
        totalItemCount = linearLayoutManager!!.itemCount
        firstVisibleItem = linearLayoutManager!!.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal || totalItemCount == 0) {
                loading = false
                previousTotal = totalItemCount
            }
        }

        // End has been reached
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
            currentPage++
            recyclerView.post(loadMore)
            loading = true
        }
    }

    abstract fun onLoadMore(current_page: Int)

    fun reset(){
        currentPage = 0
    }
}
