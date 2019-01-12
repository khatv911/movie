package arch.kay.kaymovie.shared.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import arch.kay.kaymovie.shared.viewmodel.ViewModelFactory



@Suppress("UNCHECKED_CAST")
fun <T : ViewModel> ViewModelFactory.inject(fragment: Fragment, _class: Class<T>): T = ViewModelProviders.of(fragment, this).get(_class)

@Suppress("UNCHECKED_CAST")
fun <T : ViewModel> ViewModelFactory.inject(fragmentActivity: FragmentActivity, _class: Class<T>): T = ViewModelProviders.of(fragmentActivity, this).get(_class)