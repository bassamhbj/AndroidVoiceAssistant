package com.hbjpro.androidvoiceassistant.Tools

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log

@SuppressLint("StaticFieldLeak")
object GlobalState2 {
    var context:Context = null
    fun setGlobalContext(contexta: Context){
        context = contexta
    }
    fun getGlobalContext(): Context = context
}

class GlobalState3 private constructor(val context: Context){
    init {
        Log.i("GlobalState", "Singleton created")
    }

    private object Holder { val INSTANCE = GlobalState3() }

    companion object {
        val instance: GlobalState3 by lazy { Holder.INSTANCE }
    }

    fun getGlobalContext(): Context = context


    fun getInsatance() = _state

    fun getInstanceWithContext(context: Context):GlobalState2{
        if(_state == null)
            _state = GlobalState2(context)
        return _state
    }
}