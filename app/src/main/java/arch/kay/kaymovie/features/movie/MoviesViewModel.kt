package arch.kay.kaymovie.features.movie

import arch.kay.kaymovie.entity.Movie
import arch.kay.kaymovie.repository.MovieRepository
import arch.kay.kaymovie.shared.simple.SimpleDataModel
import kotlinx.coroutines.launch


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
open class MoviesViewModel(private val repo: MovieRepository) : SimpleDataModel<List<Movie>>() {

    init {
        mLiveData = repo.getMovies(1)

        mStateEvent.addSource(repo.requestStateEvent) {
            it?.run { extractState(this) }
        }
    }

    fun loadPage(page: Int) = uiScope.launch {

        repo.loadMore(page)
    }


    fun refresh() = uiScope.launch {
        setRefreshing()
        repo.refresh()
    }

}