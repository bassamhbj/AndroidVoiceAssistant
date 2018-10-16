package com.hbjpro.androidvoiceassistant.Interface

import android.content.Intent

interface ViewListener {
    fun setResultText(text: String)
    fun onOpenAppSuccess(appLaunchIntent: Intent?)
}
