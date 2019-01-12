package arch.kay.kaymovie

import arch.kay.kaymovie.di.AppModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import timber.log.Timber
import javax.inject.Singleton


/**
 * Created by Kay Tran on 11/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
class MoviesApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerMoviesApp_Component.builder().create(this)
    }


    @Singleton
    @dagger.Component(modules = [AndroidSupportInjectionModule::class, AppModule::class])
    interface Component : AndroidInjector<MoviesApp> {
        @dagger.Component.Builder
        abstract class Builder : AndroidInjector.Builder<MoviesApp>()
    }


    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}