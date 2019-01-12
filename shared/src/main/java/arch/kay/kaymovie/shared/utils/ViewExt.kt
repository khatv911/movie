package arch.kay.kaymovie.shared.utils

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment


fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}


fun getScreenWidth(): Int {
    return Resources.getSystem().displayMetrics.widthPixels
}