package com.hbjpro.androidvoiceassistant.model

import android.content.Intent
import com.hbjpro.androidvoiceassistant.Command.CommandManager
import com.hbjpro.androidvoiceassistant.Command.ModuleApp
import com.hbjpro.androidvoiceassistant.Command.ModuleNews
import com.hbjpro.androidvoiceassistant.Data.NewsData
import com.hbjpro.androidvoiceassistant.Speech.SpeechResult

class ModelCommand {

    fun executeCommand(speechResult: SpeechResult){
        var commandManager = CommandManager()
        commandManager.executeCommand(speechResult)
    }

    fun executeOpenApp(callbackOpenApp: CallbackOpenApp ,appName: String){
        var moduleApp = ModuleApp()
        var intent = moduleApp.getAppLaunchIntent(appName)
        if(intent != null){
            // SubApplication.instance.startActivity(intent)
            // callback
        }
    }

    fun executeGetNewsFeed(callback: CallbackNewsFeed){
        ModuleNews().apply {
            getNewsFeed(object: ModuleNews.NewsModuleCallback{
                override fun onSuccess(newsData: NewsData) {
                    callback.onSucess(newsData)
                }

                override fun onError() {
                    callback.onError()
                }
            })
        }
    }

    interface CallbackOpenApp{
        fun onSucess(intent: Intent)
        fun onError()
    }

    interface CallbackNewsFeed{
        fun onSucess(newsData: NewsData)
        fun onError()
    }
}