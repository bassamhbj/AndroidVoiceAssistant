package com.hbjpro.androidvoiceassistant

import android.app.Application
import com.hbjpro.androidvoiceassistant.Tools.GlobalState

/**
 * Created by bassa on 13/08/2018.
 */
class SubApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalState.getInstanceWithContext(applicationContext)
    }
}