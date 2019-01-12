package arch.kay.kaymovie.shared.utils

import androidx.annotation.IntDef

class LoadingState {
    companion object {
        const val NONE = 1
        const val NORMAL = 2
        const val REFRESHING = 3
        const val MORE = 4
        const val SEARCHING = 5
    }

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(NONE, NORMAL, REFRESHING, MORE, SEARCHING)
    annotation class Value
}

