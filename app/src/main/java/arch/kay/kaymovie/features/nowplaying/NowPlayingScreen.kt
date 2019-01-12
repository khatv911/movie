package arch.kay.kaymovie.features.nowplaying

import androidx.fragment.app.Fragment
import arch.kay.kaymovie.features.movie.MoviesScreen
import arch.kay.kaymovie.features.movie.MoviesViewModel
import arch.kay.kaymovie.features.toprated.TopRatedViewModel
import arch.kay.kaymovie.shared.utils.inject


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
class NowPlayingScreen: MoviesScreen(){

    override fun getViewModel() = VIEW_MODEL_FACTORY.inject(this, NowPlayingViewModel::class.java)
}