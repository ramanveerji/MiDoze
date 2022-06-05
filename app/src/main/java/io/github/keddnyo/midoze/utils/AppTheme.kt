package io.github.keddnyo.midoze.utils

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager as PM
import io.github.keddnyo.midoze.AppTheme
import io.github.keddnyo.midoze.AppTheme.SETTING_DARK_MODE_NAME

class AppTheme(context: Context) {

    private val sharedPreferences = PM.getDefaultSharedPreferences(context)

    private val isDarkMode = sharedPreferences.getBoolean(SETTING_DARK_MODE_NAME, false)

    private val appThemeIndex = if (isDarkMode) AppTheme.MODE_NIGHT_YES else AppTheme.MODE_NIGHT_NO

    fun setTheme() {
        AppCompatDelegate.setDefaultNightMode(appThemeIndex)
    }

    fun swapTheme() {
        sharedPreferences.edit().putBoolean(SETTING_DARK_MODE_NAME, !isDarkMode).apply()
        setTheme()
    }

}