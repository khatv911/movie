package arch.kay.kaymovie.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arch.kay.kaymovie.api.MovieService
import arch.kay.kaymovie.entity.Movie
import arch.kay.kaymovie.repository.NowPlayingRepository
import arch.kay.kaymovie.repository.TopRatedRepository
import arch.kay.kaymovie.shared.simple.SimpleDataModel
import arch.kay.kaymovie.shared.utils.switchMap
import arch.kay.kaymovie.shared.utils.uiDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import ru.gildor.coroutines.retrofit.awaitResult
import ru.gildor.coroutines.retrofit.getOrThrow
import javax.inject.Inject


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
class MovieDetailViewModel @Inject constructor(
    private val service: MovieService
) : SimpleDataModel<Movie>() {


    private val detailLiveData = MutableLiveData<Movie>()

    init {
        mLiveData = detailLiveData
    }


    fun loadMovie(id: Long) {
        uiScope.launch {
            setLoading()
            try {
                val networkResult = service.getMovie(id).awaitResult()
                val result = networkResult.getOrThrow()
                detailLiveData.postValue(result)
                stopLoading()
            } catch (e: Throwable) {
                setError(e)
            }
        }
    }


}