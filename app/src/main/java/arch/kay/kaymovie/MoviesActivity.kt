package arch.kay.kaymovie

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import arch.kay.kaymovie.features.movie.MainViewPagerAdapter
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_now_playing -> {
                main_viewpager.currentItem =0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_top_rated -> {
                main_viewpager.currentItem =1
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        main_viewpager.adapter = MainViewPagerAdapter(supportFragmentManager)
        main_viewpager.offscreenPageLimit = 2

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
