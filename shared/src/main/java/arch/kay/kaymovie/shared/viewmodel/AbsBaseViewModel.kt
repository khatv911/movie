package arch.kay.kaymovie.shared.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arch.kay.kaymovie.shared.simple.AbsBaseFragment
import arch.kay.kaymovie.shared.utils.*


abstract class AbsBaseViewModel : ViewModel() {


    /**
     * Composite State Event
     */
    protected var mStateEvent = MediatorLiveData<RequestState>()

    /**
     * Composite Retry Event
     */
    protected val mRetryEvent = MediatorLiveData<Retry>()


    protected var mRetry: Retry = null

    /**
     * tell view to show loading or not
     *
     */
    private val mLoadingEvent = MutableLiveData<@LoadingState.Value Int>()

    /**
     * tell view about error
     */
    private val mErrorEvent = SingleLiveEvent<Throwable>()


    private val mSuccessEvent = SingleLiveEvent<String>()


    protected fun setLoading() {
        mLoadingEvent.value = LoadingState.NORMAL
    }

    protected fun setRefreshing() {
        mLoadingEvent.value = LoadingState.REFRESHING
    }

    protected fun setLoadingMore() {
        mLoadingEvent.value = LoadingState.MORE
    }

    protected fun stopLoading() {
        mLoadingEvent.value = LoadingState.NONE
    }


    fun extractState(state: RequestState) {

        when (state) {
            RequestState.STARTED -> {
                mLoadingEvent.value = LoadingState.NORMAL
            }
            is RequestState.DONE -> {
                mLoadingEvent.value = LoadingState.NONE
                when (state) {
                    is RequestState.SUCCESS -> {
                        setSuccessMessage(state.message)
                    }
                    is RequestState.ERROR -> {
                        setError(state.throwable)
                    }
                }
            }

        }
    }


    fun setError(throwable: Throwable) {
        mErrorEvent.value = throwable
    }

    protected fun setSuccessMessage(message: String?) {
        mSuccessEvent.value = message
    }


    override fun onCleared() {
        super.onCleared()
        mRetry = null
    }


    fun setup(fragment: AbsBaseFragment) {
        /**
         * Take care of loading requestStateEvent
         */
        mLoadingEvent.observeK(fragment.viewLifecycleOwner) { loading ->
            loading?.let { fragment.onLoadingStateChanged(it) }
        }
        /**
         * Take care of error
         */
        mErrorEvent.observeK(fragment.viewLifecycleOwner) {
            // error handler shall take are of this
            fragment.onError(it)
        }

        /**
         * Not so much useful
         */
        mSuccessEvent.observeK(fragment.viewLifecycleOwner) {
            fragment.onSuccess(it)
        }

        /**
         * No-Op
         */
        mStateEvent.observeK(fragment.viewLifecycleOwner) {}

        /**
         * No-Op
         */
        mRetryEvent.observeK(fragment.viewLifecycleOwner) {}
    }


}


