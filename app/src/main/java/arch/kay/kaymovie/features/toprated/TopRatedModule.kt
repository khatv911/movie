package arch.kay.kaymovie.features.toprated

import androidx.lifecycle.ViewModel
import arch.kay.kaymovie.api.MovieService
import arch.kay.kaymovie.db.TopRatedMovieDB
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
@Module(includes = [TopRatedModule.RepoModule::class])
abstract class TopRatedModule {

    @Binds
    @IntoMap
    @ViewModelKey(TopRatedViewModel::class)
    abstract fun bindListVM(vm: TopRatedViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeTopRatedScreen(): TopRatedScreen

    @Module
    class RepoModule {
        @Provides
        @Singleton
        fun provideRepo(db: TopRatedMovieDB, movieService: MovieService) =
            TopRatedRepository(movieService, db.movieDao())
    }
}