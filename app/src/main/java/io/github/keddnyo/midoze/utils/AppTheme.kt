package io.github.keddnyo.midoze.utils

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager as PM
import io.github.keddnyo.midoze.objects.AppTheme

class AppTheme(context: Context) {

    private val sp = PM.getDefaultSharedPreferences(context)

    fun isDarkMode(): Boolean = sp.getBoolean(AppTheme.SETTING_MODE_DARK, false)

    private fun themeIndex() = if (isDarkMode()) AppTheme.NIGHT else AppTheme.LIGHT

    fun setTheme() {
        AppCompatDelegate.setDefaultNightMode(themeIndex())
    }

    fun swapTheme() {
        sp.edit().putBoolean(AppTheme.SETTING_MODE_DARK, !isDarkMode()).apply()
        AppCompatDelegate.setDefaultNightMode(themeIndex())
    }

}