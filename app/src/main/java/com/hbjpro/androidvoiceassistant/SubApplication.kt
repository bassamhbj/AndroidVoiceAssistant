package com.hbjpro.androidvoiceassistant

import android.app.Application
import android.content.Context

class SubApplication : Application() {

    //object SubApplication

    companion object {
        lateinit var instance: SubApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}