package arch.kay.kaymovie.features.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import arch.kay.kaymovie.R
import kotlinx.android.synthetic.main.activity_detail.*


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        if (savedInstanceState == null) {
            val extras = intent.extras ?: return
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, MovieDetailScreen.newInstance(extras.getLong(MOVIE)))
                .addToBackStack("MovieDetails")
                .commit()
        }

        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) finish()
        }
    }


    companion object {
        const val MOVIE = "movie"

        fun newIntent(context: Context, id: Long): Intent {
            return Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(MOVIE, id)
            }
        }

    }
}