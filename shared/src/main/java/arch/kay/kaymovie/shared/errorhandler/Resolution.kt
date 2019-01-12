package arch.kay.kaymovie.shared.errorhandler

import retrofit2.HttpException


/**
 * Resolve Http exception
 */
interface HttpResolution {
    fun onHttpException(httpException: HttpException)
}

/**
 * The fallback resolution for all un-handled case
 */
interface GenericResolution {
    /**
     *
     */
    fun onGenericException(throwable: Throwable)
}

interface ConnectivityResolution {
    /**
     *
     */
    fun onConnectivityAvailable()

    /**
     *
     */
    fun onConnectivityUnavailable()
}


interface Resolution : HttpResolution, GenericResolution, ConnectivityResolution {
    fun resolve(throwable: Throwable)
    fun success(message: String?)
}
