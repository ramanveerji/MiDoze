package io.github.keddnyo.midoze.utils

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager as PM
import io.github.keddnyo.midoze.AppTheme

class AppTheme(context: Context) {

    private val sp = PM.getDefaultSharedPreferences(context)

    private val isDarkMode = sp.getBoolean(AppTheme.SETTING_MODE_DARK, false)

    private val themeIndex = if (isDarkMode) AppTheme.LIGHT else AppTheme.NIGHT

    fun setTheme() {
        AppCompatDelegate.setDefaultNightMode(themeIndex)
    }

    fun swapTheme() {
        sp.edit().putBoolean(AppTheme.SETTING_MODE_DARK, !isDarkMode).apply()
        setTheme()
    }

}