package io.github.keddnyo.midoze.utils

import android.content.Context
import android.util.DisplayMetrics
import android.widget.Toast

class UiUtils {

    fun getGridLayoutIndex(
        context: Context,
        columnWidthDp: Int,
    ): Int {
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        return (screenWidthDp / columnWidthDp.toString().toFloat() + 0.5).toInt()
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}