package arch.kay.kaymovie.di

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import arch.kay.kaymovie.db.NowPlayingDB
import arch.kay.kaymovie.db.TopRatedMovieDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
@Module
class DbModule {
    @Provides
    @Singleton
    fun provideTopRatedDB(@NonNull application: Application): TopRatedMovieDB {
        return Room.inMemoryDatabaseBuilder(application, TopRatedMovieDB::class.java).allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideNowPlaying(@NonNull application: Application): NowPlayingDB {
        return Room.inMemoryDatabaseBuilder(application, NowPlayingDB::class.java).allowMainThreadQueries().build()
    }

}