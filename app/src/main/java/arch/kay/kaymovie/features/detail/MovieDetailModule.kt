package arch.kay.kaymovie.features.detail

import androidx.lifecycle.ViewModel
import arch.kay.kaymovie.features.nowplaying.NowPlayingScreen
import arch.kay.kaymovie.features.nowplaying.NowPlayingViewModel
import arch.kay.kaymovie.shared.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
@Module
abstract class MovieDetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindVM(vm: MovieDetailViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeScreen(): MovieDetailScreen
}