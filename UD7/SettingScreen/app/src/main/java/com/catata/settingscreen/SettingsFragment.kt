package com.catata.settingscreen

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.Preference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences (savedInstanceState: Bundle ?, rootKey: String?) {
        setPreferencesFromResource(R.xml.my_settings, rootKey)
        preferenceFragmentCompat=this

        /*preferenceManager.findPreference<PreferenceCategory>("category1")?.let {
            preferenceScreen.removePreference(it)
        }*/




    }




    companion object {
        var preferenceFragmentCompat:PreferenceFragmentCompat?=null
    }
}