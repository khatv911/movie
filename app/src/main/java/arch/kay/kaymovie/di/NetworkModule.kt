package arch.kay.kaymovie.di

import android.app.Application
import android.content.SharedPreferences
import arch.kay.kaymovie.api.MovieService
import arch.kay.kaymovie.api.RequestInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.*
import javax.inject.Singleton


/**
 * Created by Kay Tran on 11/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpCache(application: Application): Cache {
        val cacheSize = 10L * 1024 * 1024 // 10MB of network cache
        return Cache(application.cacheDir, cacheSize)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache, prefs: SharedPreferences): OkHttpClient =
        OkHttpClient.Builder().apply {
            cache(cache)
            addInterceptor(RequestInterceptor())
            addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                Timber.tag("OkHttp").d(message)
            }).setLevel(HttpLoggingInterceptor.Level.BODY))
        }.build()


    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
         Moshi.Builder()
             .add(Date::class.java,Rfc3339DateJsonAdapter().nullSafe())
            .build()
    

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()


    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)

}