package arch.kay.kaymovie.shared.errorhandler

import retrofit2.HttpException

abstract class AbsResolution : Resolution {

    override fun resolve(throwable: Throwable) = when (throwable) {
        is HttpException -> onHttpException(throwable)
        else -> onGenericException(throwable)
    }


    override fun onHttpException(httpException: HttpException) {
        when (httpException.code()) {
            401 -> onUnAuthorized()
            403 -> onForbidden()
            404 -> onNotFound()
            400 -> onClientError()
            500 -> onInternalServerError()
            503 -> onUnavailableService()
            else -> onGenericException(httpException)
        }
    }

    abstract fun onUnavailableService()

    abstract fun onInternalServerError()

    abstract fun onClientError()

    abstract fun onNotFound()

    abstract fun onUnAuthorized()

    abstract fun onForbidden()

}
