package arch.kay.kaymovie.shared.simple

import android.os.Bundle
import arch.kay.kaymovie.shared.utils.observeK
import arch.kay.kaymovie.shared.viewmodel.ViewModelFactory
import javax.inject.Inject


abstract class SimpleDataFragment<T, VM : SimpleDataModel<T>> : AbsBaseFragment() {


    /**
     * The ViewModelFactory
     */
    @Inject
    lateinit var VIEW_MODEL_FACTORY: ViewModelFactory

    protected lateinit var mViewModel: VM

    abstract fun getViewModel(): VM

    abstract fun onDataChanged(t: T?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel.setup(this)
        mViewModel.mLiveData.observeK(viewLifecycleOwner) {
            onDataChanged(it)
        }
    }




}