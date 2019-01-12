package arch.kay.kaymovie.shared.utils

import arch.kay.kaymovie.shared.BuildConfig
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext



/**
 * forward the exception
 */
val exceptionThrower: CoroutineContext = CoroutineExceptionHandler { _, throwable ->
    throw throwable
}

/**
 * Take
 */
fun BaseRepository.withRetryExceptionHandler(_retry: Retry): CoroutineContext {
    return CoroutineExceptionHandler { _, e ->
        if (BuildConfig.DEBUG) e.printStackTrace()
        // ensure to work on the main thread.
        requestStateEvent.postValue(RequestState.ERROR(e))
        retryEvent.postValue(_retry)
    }
}

// dispatches execution into Android main thread
val uiDispatcher: CoroutineDispatcher = Dispatchers.Main
// represent a pool of shared threads as coroutine dispatcher
val bgDispatcher: CoroutineDispatcher = Dispatchers.IO