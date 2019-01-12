package arch.kay.kaymovie.features.toprated

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import arch.kay.kaymovie.R
import arch.kay.kaymovie.entity.Movie
import arch.kay.kaymovie.features.movie.MovieItem
import arch.kay.kaymovie.features.movie.MoviesScreen
import arch.kay.kaymovie.features.movie.MoviesViewModel
import arch.kay.kaymovie.shared.simple.SimpleRecyclerViewFragment
import arch.kay.kaymovie.shared.utils.inject
import arch.kay.kaymovie.shared.utils.observeK
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
class TopRatedScreen : MoviesScreen(){
    override fun getViewModel()= VIEW_MODEL_FACTORY.inject(this, TopRatedViewModel::class.java)
}