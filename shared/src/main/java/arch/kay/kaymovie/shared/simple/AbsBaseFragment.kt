package arch.kay.kaymovie.shared.simple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.widget.ContentLoadingProgressBar
import arch.kay.kaymovie.shared.R
import arch.kay.kaymovie.shared.errorhandler.DefaultResolution
import arch.kay.kaymovie.shared.errorhandler.Resolution
import arch.kay.kaymovie.shared.errorhandler.resolver.TimberResolver
import arch.kay.kaymovie.shared.errorhandler.resolver.ToastyResolver
import arch.kay.kaymovie.shared.utils.LoadingState
import arch.kay.kaymovie.shared.utils.LoadingState.Companion.NORMAL
import dagger.android.support.DaggerFragment



abstract class AbsBaseFragment : DaggerFragment() {


    /**
     * provide a resolution lazily.
     * fallback to default
     */
    val uiResolution by lazy {
        getResolution()
    }


    /**
     * Override this method to provide proper resolution
     */
    open fun getResolution(): Resolution = DefaultResolution(mutableListOf()).apply {
        addResolver(TimberResolver())
        addResolver(ToastyResolver(this@AbsBaseFragment))
    }


    /**
     * The common loading view. it should respond to the ViewModel#mLoadingEvent
     * If you wish to have the ability to swipe&refresh,
     */
    protected lateinit var mLoadingView: ContentLoadingProgressBar

    /**
     * The content view, inflated from a single layout file
     */
    protected lateinit var mContentView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_base, container, false) as FrameLayout
        val contentFragmentView = inflater.inflate(getLayoutId(), view, false)
        contentFragmentView.id = R.id.base_content
        view.addView(contentFragmentView, FrameLayout.LayoutParams(-1, -1)) // short for match_parent
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLoadingView = view.findViewById(R.id.progress_bar)
        mContentView = view.findViewById(R.id.base_content)
    }


    open fun onLoadingStateChanged(@LoadingState.Value loadingState: Int) {
        when (loadingState) {
            // have to use postDelayed to show the loading,
            // this will cause temporary leak.
            NORMAL -> {
                showLoadingView()
            }
            else -> {
                hideLoadingView()
            }
        }
    }

    protected fun showLoadingView() {
        mLoadingView.show()
    }

    protected fun hideLoadingView() {
        mLoadingView.hide()
    }

    open fun onError(throwable: Throwable?) {
        throwable?.let {
            uiResolution.resolve(it)
        }
    }

    open fun onSuccess(message: String?) {
        uiResolution.success(message)
    }


    abstract fun getLayoutId(): Int


}