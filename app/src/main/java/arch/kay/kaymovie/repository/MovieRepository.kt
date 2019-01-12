package arch.kay.kaymovie.repository

import arch.kay.kaymovie.db.MovieDao
import arch.kay.kaymovie.entity.Movie
import arch.kay.kaymovie.entity.MovieResponse
import arch.kay.kaymovie.shared.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.gildor.coroutines.retrofit.awaitResult
import ru.gildor.coroutines.retrofit.getOrThrow
import javax.inject.Inject


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */

abstract class MovieRepository constructor(private val dao: MovieDao) : BaseRepository() {

    abstract fun createNetworkCall(page: Int): retrofit2.Call<MovieResponse>

    private fun getMovieFromDBLiveData(page: Int) = dao.getMovieList()

    fun getMovies(page: Int) = createNetworkBoundResource(
        getMovieFromDBLiveData(page),
        createNetworkCall(page),
        { network ->
            dao.insertMovieList(network.results)
        },
        { db -> db?.isEmpty() != false }
    )

    suspend fun loadMore(page: Int) {
        try {
            requestStateEvent.postValue(RequestState.STARTED)
            val networkResult = createNetworkCall(page).awaitResult()
            val network = networkResult.getOrThrow()
            withContext(bgDispatcher + exceptionThrower) {
                dao.insertMovieList(network.results)
                requestStateEvent.postValue(RequestState.DONE())
            }
        } catch (e: Throwable) {
            requestStateEvent.postValue(RequestState.ERROR(e))
        }

    }


    suspend fun refresh() {
        try {
            val networkResult = createNetworkCall(1).awaitResult()
            val network = networkResult.getOrThrow()
            withContext(bgDispatcher + exceptionThrower) {
                dao.removeAll()
                dao.insertMovieList(network.results)
                requestStateEvent.postValue(RequestState.DONE())
            }
        } catch (e: Throwable) {
            requestStateEvent.postValue(RequestState.ERROR(e))
        }
    }


}