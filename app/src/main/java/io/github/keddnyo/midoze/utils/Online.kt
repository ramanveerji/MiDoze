package io.github.keddnyo.midoze.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class Online(context: Context) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val info = connectivityManager.allNetworkInfo

    fun getState(): Boolean {
        for (i in info.indices) {
            if (info[i].state == NetworkInfo.State.CONNECTED) {
                return true
            }
        }
        return false
    }

}