package arch.kay.kaymovie.shared.utils


/**
 * Retry
 */
typealias Retry = (() -> Any)?

interface Retriable {
    fun retry()
}