package arch.kay.kaymovie.features.nowplaying

import androidx.lifecycle.ViewModel
import arch.kay.kaymovie.api.MovieService
import arch.kay.kaymovie.db.NowPlayingDB
import arch.kay.kaymovie.db.TopRatedMovieDB
import arch.kay.kaymovie.features.toprated.TopRatedModule
import arch.kay.kaymovie.features.toprated.TopRatedScreen
import arch.kay.kaymovie.features.toprated.TopRatedViewModel
import arch.kay.kaymovie.repository.NowPlayingRepository
import arch.kay.kaymovie.repository.TopRatedRepository
import arch.kay.kaymovie.shared.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Singleton


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
@Module(includes = [NowPlayingModule.RepoModule::class])
abstract class NowPlayingModule {

    @Binds
    @IntoMap
    @ViewModelKey(NowPlayingViewModel::class)
    abstract fun bindListVM(vm: NowPlayingViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeScreen(): NowPlayingScreen

    @Module
    class RepoModule {
        @Provides
        @Singleton
        fun provideRepo(db: NowPlayingDB, movieService: MovieService) =
            NowPlayingRepository(movieService, db.movieDao())
    }
}