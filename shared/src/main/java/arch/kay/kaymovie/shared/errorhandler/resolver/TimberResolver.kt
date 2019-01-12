package arch.kay.kaymovie.shared.errorhandler.resolver

import timber.log.Timber


class TimberResolver: UiResolver {

    override fun resolveErrorMessage(message: String, retryOption: Pair<Boolean, Int?>) {
        Timber.tag("TimberResolver").e(message)
    }

    override fun resolveErrorMessage(resource: Int, retryOption: Pair<Boolean, Int?>) {
        Timber.tag("TimberResolver").e("error")
    }

    override fun showConnectivity(available: Boolean) {
        if(available){
            Timber.tag("TimberResolver").d("Back Online")
        }else{
            Timber.tag("TimberResolver").e("Connection lost!")
        }
    }

    override fun resolveNotFound(retryOption: Pair<Boolean, Int?>) {
        Timber.tag("TimberResolver").e("Resource not found")
    }

    override fun resolveUnAuthorized() {
        Timber.tag("TimberResolver").e("Unauthorized!")
    }

    override fun resolveForbidden() {
        Timber.tag("TimberResolver").e("Forbidden!")
    }

    override fun showSuccess(message: String?) {
        Timber.tag("TimberResolver").d("Action success!")
    }
}