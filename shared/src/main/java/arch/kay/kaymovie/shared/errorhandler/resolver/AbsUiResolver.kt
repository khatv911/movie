package arch.kay.kaymovie.shared.errorhandler.resolver

import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference


abstract class AbsUiResolver constructor(protected val fragmentRef: WeakReference<Fragment>) : UiResolver {

    constructor(fragment: Fragment) : this(WeakReference(fragment))

//    override fun resolveErrorMessage(message: String, retryOption: Pair<Boolean, Int?>) {}
//
//    override fun resolveErrorMessage(resource: Int, retryOption: Pair<Boolean, Int?>) {}
//
//    //NO-OP
//    override fun showConnectivity(available: Boolean) {}
//
//    // NO-OP
//    override fun resolveNotFound(retryOption: Pair<Boolean, Int?>) {}
//
//    override fun resolveUnAuthorized() {}
//
//    //NO-OP
//    override fun resolveForbidden() {}
//
//    override fun showSuccess(message: String?) {}
}