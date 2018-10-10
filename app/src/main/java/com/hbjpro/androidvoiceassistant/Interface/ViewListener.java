package com.hbjpro.androidvoiceassistant.Interface;

import android.content.Intent;

public interface ViewListener {
    void setResultText(String text);
    void onOpenAppSuccess(Intent appLaunchIntent);
}
