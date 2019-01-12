package arch.kay.kaymovie.di

import android.app.Application
import android.content.SharedPreferences
import arch.kay.kaymovie.MoviesApp
import arch.kay.kaymovie.features.detail.MovieDetailModule
import arch.kay.kaymovie.features.nowplaying.NowPlayingModule
import arch.kay.kaymovie.features.toprated.TopRatedModule
import arch.kay.kaymovie.shared.utils.PrefHelper
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Kay Tran on 11/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */

@Module(
    includes = [AppModule.ApplicationBinder::class,
        NetworkModule::class,
        DbModule::class,
        TopRatedModule::class,
        NowPlayingModule::class,
        MovieDetailModule::class
    ]
)
class AppModule {

    @Module
    abstract class ApplicationBinder {
        @Binds
        abstract fun application(app: MoviesApp): Application
    }

    @Provides
    @Singleton
    fun provideSharedPrefs(application: Application): SharedPreferences {
        return PrefHelper.defaultPrefs(application)
    }

}