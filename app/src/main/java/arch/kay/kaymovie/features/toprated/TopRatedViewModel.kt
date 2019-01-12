package arch.kay.kaymovie.features.toprated

import arch.kay.kaymovie.entity.Movie
import arch.kay.kaymovie.features.movie.MoviesViewModel
import arch.kay.kaymovie.repository.MovieRepository
import arch.kay.kaymovie.repository.TopRatedRepository
import arch.kay.kaymovie.shared.simple.SimpleDataModel
import javax.inject.Inject


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
class TopRatedViewModel @Inject constructor(private val repo: TopRatedRepository): MoviesViewModel(repo)