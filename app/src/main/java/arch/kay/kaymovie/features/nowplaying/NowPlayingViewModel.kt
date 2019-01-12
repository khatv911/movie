package arch.kay.kaymovie.features.nowplaying

import arch.kay.kaymovie.features.movie.MoviesViewModel
import arch.kay.kaymovie.repository.MovieRepository
import arch.kay.kaymovie.repository.NowPlayingRepository
import javax.inject.Inject


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
class NowPlayingViewModel @Inject constructor(private val repo: NowPlayingRepository): MoviesViewModel(repo)