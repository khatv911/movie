package arch.kay.kaymovie.entity

import androidx.room.Entity


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
@Entity(tableName = "People", primaryKeys = ["id"])
data class Person(var page: Int,
//                  @Embedded var personDetail: PersonDetail? = null,
                  val profile_path: String?,
                  val adult: Boolean,
                  val id: Int,
                  val name: String,
                  val popularity: Float)