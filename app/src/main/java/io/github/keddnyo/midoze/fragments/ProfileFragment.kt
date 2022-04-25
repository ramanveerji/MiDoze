package io.github.keddnyo.midoze.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import io.github.keddnyo.midoze.R
import io.github.keddnyo.midoze.activities.ExtrasRequestActivity
import io.github.keddnyo.midoze.activities.SettingsActivity

class ProfileFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.fragment_profile, rootKey)
    }

    override fun onResume() {
        super.onResume()

        requireActivity().title = getString(R.string.app_name)

        val favorites = findPreference<Preference>("extras_favorites")
        val downloads = findPreference<Preference>("extras_downloads")
        val shares = findPreference<Preference>("extras_shares")

        val customRequest = findPreference<Preference>("extras_custom_request")
        val settings = findPreference<Preference>("extras_settings")

        if (customRequest != null && favorites != null && downloads != null && shares != null && settings != null) {
            val prefs: SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(requireActivity())

            favorites.summary =
                "${prefs.getInt("favoriteCount", 0)} ${getString(R.string.profile_items)}"
            downloads.summary =
                "${prefs.getInt("downloadCount", 0)} ${getString(R.string.profile_times)}"
            shares.summary = "${prefs.getInt("shareCount", 0)} ${getString(R.string.profile_times)}"

            customRequest.setOnPreferenceClickListener {
                startActivity(Intent(requireContext(), ExtrasRequestActivity::class.java))
                true
            }

            settings.setOnPreferenceClickListener {
                startActivity(Intent(context, SettingsActivity::class.java))
                true
            }
        }
    }
}