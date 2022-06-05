package io.github.keddnyo.midoze.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import io.github.keddnyo.midoze.AppTheme

class AppTheme(context: Context) {

    private val sharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    private val editor = sharedPreferences.edit()

    private val propertyName = "settings_dark_mode"

    private val darkModeValue = sharedPreferences.getBoolean(propertyName, false)

    private fun setDarkModeValue(state: Boolean) {
        editor.putBoolean(propertyName, state)
        editor.apply()
    }

    private val appThemeIndex: Int =
        if (darkModeValue) {
            AppTheme.MODE_NIGHT_YES
        } else {
            AppTheme.MODE_NIGHT_NO
        }

    fun setDarkModeState() {
        AppCompatDelegate.setDefaultNightMode(appThemeIndex)
    }

    fun switchDarkModeState() {
        setDarkModeValue(!darkModeValue)
        AppCompatDelegate.setDefaultNightMode(appThemeIndex)
    }

}