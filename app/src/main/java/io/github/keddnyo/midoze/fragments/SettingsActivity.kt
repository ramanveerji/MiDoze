package io.github.keddnyo.midoze.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import io.github.keddnyo.midoze.BuildConfig
import io.github.keddnyo.midoze.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onResume() {
        super.onResume()

        requireActivity().title = getString(R.string.settings_title)

        val about = findPreference<Preference>("settings_app_info")
        val cloud = findPreference<Preference>("settings_server_info")

        if (about != null && cloud != null) {
            about.title = getString(R.string.app_name) + " " + BuildConfig.VERSION_NAME

            about.setOnPreferenceClickListener {
                startActivity(
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Keddnyo"))
                )
                true
            }

            cloud.setOnPreferenceClickListener {
                startActivity(
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://4pda.to/forum/index.php?showuser=243484"))
                )
                true
            }
        }
    }
}