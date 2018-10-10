package com.hbjpro.androidvoiceassistant.Interface;

import android.content.Intent;

/**
 * Created by bassa on 08/07/2018.
 */

public interface OnOrderResult {
    void onGetLaunchIntentSuccess(Intent launchIntent);
    void onGetLaunchIntentError();
    //void onSearchInternetSuccess();
    //void onSearchInternetError();
}
