package com.hbjpro.androidvoiceassistant.fragment

import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import com.hbjpro.androidvoiceassistant.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}
