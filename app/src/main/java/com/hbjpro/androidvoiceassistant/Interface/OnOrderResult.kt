package com.hbjpro.androidvoiceassistant.Interface

import android.content.Intent

interface OnOrderResult {
    fun onGetLaunchIntentSuccess(launchIntent: Intent?)
    fun onGetLaunchIntentError()
    //void onSearchInternetSuccess();
    //void onSearchInternetError();
}
