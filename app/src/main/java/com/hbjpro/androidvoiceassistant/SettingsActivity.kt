package com.hbjpro.androidvoiceassistant

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hbjpro.androidvoiceassistant.fragment.SettingsFragment

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, SettingsFragment())
                .commit()
    }
}
