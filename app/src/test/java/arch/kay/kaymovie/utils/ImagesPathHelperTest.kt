package arch.kay.kaymovie.utils

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
class ImagesPathHelperTest {

    @Test
    fun toPosterPath() {
        assertEquals("https://image.tmdb.org/t/p/w92/abcd.jpeg",ImagesPathHelper.toPosterPath(50,"/abcd.jpeg"))
        assertEquals("https://image.tmdb.org/t/p/w154/abcd.jpeg",ImagesPathHelper.toPosterPath(120,"/abcd.jpeg"))
        assertEquals("https://image.tmdb.org/t/p/w185/abcd.jpeg",ImagesPathHelper.toPosterPath(185,"/abcd.jpeg"))
        assertEquals("https://image.tmdb.org/t/p/w342/abcd.jpeg",ImagesPathHelper.toPosterPath(340,"/abcd.jpeg"))
        assertEquals("https://image.tmdb.org/t/p/w500/abcd.jpeg",ImagesPathHelper.toPosterPath(400,"/abcd.jpeg"))
        assertEquals("https://image.tmdb.org/t/p/w780/abcd.jpeg",ImagesPathHelper.toPosterPath(680,"/abcd.jpeg"))
        assertEquals("https://image.tmdb.org/t/p/original/abcd.jpeg",ImagesPathHelper.toPosterPath(900,"/abcd.jpeg"))
    }

    @Test
    fun toBackDropPath() {
        assertEquals("https://image.tmdb.org/t/p/w300/abcd.jpeg",ImagesPathHelper.toBackDropPath(250,"/abcd.jpeg"))
        assertEquals("https://image.tmdb.org/t/p/w780/abcd.jpeg",ImagesPathHelper.toBackDropPath(400,"/abcd.jpeg"))
        assertEquals("https://image.tmdb.org/t/p/w1280/abcd.jpeg",ImagesPathHelper.toBackDropPath(800,"/abcd.jpeg"))
        assertEquals("https://image.tmdb.org/t/p/original/abcd.jpeg",ImagesPathHelper.toBackDropPath(1300,"/abcd.jpeg"))

    }

    @Test
    fun toProfilePath() {
        assertEquals("https://image.tmdb.org/t/p/w45/abcd.jpeg",ImagesPathHelper.toProfilePath(30,"/abcd.jpeg"))
        assertEquals("https://image.tmdb.org/t/p/w185/abcd.jpeg",ImagesPathHelper.toProfilePath(100,"/abcd.jpeg"))
        assertEquals("https://image.tmdb.org/t/p/original/abcd.jpeg",ImagesPathHelper.toProfilePath(200,"/abcd.jpeg"))
    }
}