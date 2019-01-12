package arch.kay.kaymovie.shared.simple

import androidx.lifecycle.LiveData
import arch.kay.kaymovie.shared.utils.RequestState
import arch.kay.kaymovie.shared.utils.uiDispatcher
import arch.kay.kaymovie.shared.viewmodel.AbsBaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren


abstract class SimpleDataModel<T> : AbsBaseViewModel() {
    lateinit var mLiveData: LiveData<T>

    fun isLoading() = mStateEvent.value == RequestState.STARTED

    private val job = SupervisorJob()

    protected val uiScope = CoroutineScope(uiDispatcher + job)

    override fun onCleared() {
        super.onCleared()
        uiScope.coroutineContext.cancelChildren()
    }
}