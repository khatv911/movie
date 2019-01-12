package arch.kay.kaymovie.shared.utils


import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations



// Needed for better type inference
fun <X, Y> LiveData<X>.map(func: (X) -> Y): LiveData<Y> =
        Transformations.map(this, func)

fun <X, Y> LiveData<X>.switchMap(func: (X) -> LiveData<Y>): LiveData<Y> =
        Transformations.switchMap(this, func)

inline fun <T> LiveData<T>.observeK(owner: LifecycleOwner, crossinline observer: (T?) -> Unit) {
    observe(owner, Observer { observer(it) })
}




