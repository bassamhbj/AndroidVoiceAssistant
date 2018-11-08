package com.hbjpro.androidvoiceassistant

import android.app.Application
import android.content.Context

class SubApplication : Application() {

    companion object {
        lateinit var context: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}