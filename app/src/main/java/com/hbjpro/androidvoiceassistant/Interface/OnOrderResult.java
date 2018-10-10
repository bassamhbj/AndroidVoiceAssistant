package com.hbjpro.androidvoiceassistant.Interface;

import android.content.Intent;

public interface OnOrderResult {
    void onGetLaunchIntentSuccess(Intent launchIntent);
    void onGetLaunchIntentError();
    //void onSearchInternetSuccess();
    //void onSearchInternetError();
}
