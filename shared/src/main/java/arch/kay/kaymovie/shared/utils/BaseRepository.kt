package arch.kay.kaymovie.shared.utils

import androidx.lifecycle.LiveData
import retrofit2.Call



/**
 * @property requestStateEvent network response status for a specific task,
 * @property retryEvent hold the latest [Retry] (that may points to a function)
 */
abstract class BaseRepository {
    val requestStateEvent = SingleLiveEvent<RequestState>()
    val retryEvent = SingleLiveEvent<Retry>()
}


inline fun <PersistedData, NetworkResponse : Any>
        BaseRepository.createNetworkBoundResource(dbCall: LiveData<PersistedData>,
                                                  networkCall: Call<NetworkResponse>,
                                                  crossinline persistNetworkResult: (NetworkResponse) -> Unit,
                                                  crossinline shouldFetch: (PersistedData?) -> Boolean): LiveData<PersistedData> {

    requestStateEvent.value = null
    retryEvent.value = null

    return object : NetworkBoundResource<PersistedData, NetworkResponse>() {
        override fun loadFromDB(): LiveData<PersistedData> = dbCall

        override fun createCall(): Call<NetworkResponse> = networkCall

        override fun saveCallResult(requestType: NetworkResponse) = persistNetworkResult(requestType)

        override fun shouldFetch(resultType: PersistedData?): Boolean {
            val fetch = shouldFetch(resultType)
            requestStateEvent.value = RequestState.STARTED
            return fetch
        }

        override fun onFetchSuccess() {
            requestStateEvent.value = RequestState.SUCCESS("New Updated")
            retryEvent.value = null
        }

        override fun onException(e: Throwable) {
            requestStateEvent.value = RequestState.ERROR(e)
            retryEvent.value = retry
        }

        override fun noNeedFetching() {
            requestStateEvent.value = RequestState.DONE()
            retryEvent.value = null
        }
    }.asLiveData()
}
