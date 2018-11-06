package com.hbjpro.androidvoiceassistant.model

import android.content.Intent
import com.hbjpro.androidvoiceassistant.command.ModuleApp
import com.hbjpro.androidvoiceassistant.command.ModuleNews
import com.hbjpro.androidvoiceassistant.data.NewsData

class ModelCommand {

    fun executeOpenApp(callbackOpenApp: CallbackOpenApp ,appName: String){
        var moduleApp = ModuleApp()
        var intent = moduleApp.getAppLaunchIntent(appName)
        if(intent != null){
            callbackOpenApp.onSuccess(intent)
        }else{
            callbackOpenApp.onError("")
        }
    }

    fun executeGetNewsFeed(callback: CallbackNewsFeed){
        ModuleNews().apply {
            getNewsFeed(object: ModuleNews.NewsModuleCallback{
                override fun onSuccess(newsData: NewsData) {
                    callback.onSuccess(newsData)
                }

                override fun onError(errorMsg: String) {
                    callback.onError("")
                }
            })
        }
    }

    interface CallbackOpenApp{
        fun onSuccess(intent: Intent)
        fun onError(errorMsg: String)
    }

    interface CallbackNewsFeed{
        fun onSuccess(newsData: NewsData)
        fun onError(errorMsg: String)
    }
}