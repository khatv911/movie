package arch.kay.kaymovie.shared.viewmodel

import androidx.lifecycle.LifecycleOwner
import arch.kay.kaymovie.shared.utils.LoadingState




interface LifecycleOwnerExt : LifecycleOwner {
    /**
     * loading requestStateEvent change
     */
    fun onLoadingStateChanged(@LoadingState.Value loadingState: Int = LoadingState.NORMAL)

    /**
     *
     */
    fun onError(throwable: Throwable?)

    /**
     * show or log success message?
     */
    fun onSuccess(message: String?)
}
