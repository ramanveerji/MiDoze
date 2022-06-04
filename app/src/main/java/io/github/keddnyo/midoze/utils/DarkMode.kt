package io.github.keddnyo.midoze.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager

class DarkMode(context: Context) {

    private val sharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    private val editor = sharedPreferences.edit()

    private val propertyName = "settings_dark_mode"

    private fun getDarkModeValue(): Boolean {
        return sharedPreferences.getBoolean(propertyName, false)
    }

    private fun setDarkModeValue(state: Boolean) {
        editor.putBoolean(propertyName, state)
        editor.apply()
    }

    private fun getDarkModeIndex(): Int {
        return if (getDarkModeValue()) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
    }

    fun setDarkModeState() {
        AppCompatDelegate.setDefaultNightMode(getDarkModeIndex())
    }

    fun switchDarkModeState() {
        setDarkModeValue(!getDarkModeValue())
        AppCompatDelegate.setDefaultNightMode(getDarkModeIndex())
    }

}