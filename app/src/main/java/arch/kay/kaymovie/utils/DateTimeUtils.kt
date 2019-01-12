package arch.kay.kaymovie.utils

import java.text.DateFormat
import java.util.*


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */

fun Date.year():Int{
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.YEAR)
}

fun Date.toDayMonYear(): String{
    return DateFormat.getDateInstance(DateFormat.SHORT).format(this)
}
