package arch.kay.kaymovie.features.movie

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import arch.kay.kaymovie.R


/**
 * Created by Kay Tran on 12/1/19.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
class MovieItemDecoration(
    val context: Context,
    val paddingLeft: Int,
    val paddingRight: Int


) : RecyclerView.ItemDecoration() {

    private var mDivider: Drawable? = null

    init {
        mDivider = ContextCompat.getDrawable(context, R.drawable.divider_item)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {

        val left = parent.paddingLeft + paddingLeft
        val right = parent.width - parent.paddingRight + paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + (mDivider?.intrinsicHeight ?: 0)

            mDivider?.let {
                it.setBounds(left, top, right, bottom)
                it.draw(c)
            }

        }
    }
}