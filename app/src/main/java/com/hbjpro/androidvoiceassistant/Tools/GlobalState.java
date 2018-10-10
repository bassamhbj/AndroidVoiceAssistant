package com.hbjpro.androidvoiceassistant.Tools;

import android.content.Context;

public class GlobalState{
    private Context _context;
    private static GlobalState _state;

    private GlobalState(Context context){
        this._context = context;
    }

    public static GlobalState getInstance(){
        return _state;
    }

    public static GlobalState getInstanceWithContext(Context context){
        if(_state == null){
            _state = new GlobalState(context);
        }
        return _state;
    }

    public Context getContext(){
        return _context;
    }
}