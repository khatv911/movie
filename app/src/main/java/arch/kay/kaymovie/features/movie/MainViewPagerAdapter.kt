package arch.kay.kaymovie.features.movie

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import arch.kay.kaymovie.features.nowplaying.NowPlayingScreen
import arch.kay.kaymovie.features.toprated.TopRatedScreen


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
class MainViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> NowPlayingScreen()
            else -> TopRatedScreen()
        }

    override fun getCount(): Int = 2

}