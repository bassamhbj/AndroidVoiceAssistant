package com.hbjpro.androidvoiceassistant.Interface;

import android.content.Intent;

/**
 * Created by bassa on 06/05/2018.
 */

public interface ViewListener {
    void setResultText(String text);
    void onOpenAppSuccess(Intent appLaunchIntent);
}
