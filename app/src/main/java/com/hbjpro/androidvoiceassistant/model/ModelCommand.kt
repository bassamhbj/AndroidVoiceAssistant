package com.hbjpro.androidvoiceassistant.model

import android.content.Intent
import com.hbjpro.androidvoiceassistant.command.FirebaseManager
import com.hbjpro.androidvoiceassistant.command.ModuleApp
import com.hbjpro.androidvoiceassistant.command.ModuleNews
import com.hbjpro.androidvoiceassistant.data.Article
import com.hbjpro.androidvoiceassistant.data.MessageData
import com.hbjpro.androidvoiceassistant.data.NewsData

class ModelCommand {

    fun executeOpenApp(appName: String, callback: CallbackCommand<Intent, Any>){
        ModuleApp().apply {
            var intent = getAppLaunchIntent(appName)
            if(intent != null){
                callback.onSuccess(intent)
            }else{
                callback.onError("")
            }
        }

    }

    fun executeGetNewsFeed(callback: CallbackCommand<NewsData, Article>){
        ModuleNews().apply {
            getNewsFeed(object: ModuleNews.NewsModuleCallback{
                override fun onSuccess(newsData: NewsData) {
                    callback.onSuccessList(newsData.articles)
                }

                override fun onError(errorMsg: String) {
                    callback.onError("")
                }
            })
        }
    }

    fun executeCreateNewMessage(messageData: MessageData, callback: CallbackCommand<String, MessageData?>){
        FirebaseManager().apply {
            createNewMessage(messageData, object: FirebaseManager.FirebaseCallback<String, MessageData?>{
                override fun onSuccess(result: String) {
                    onSuccess(result)
                }

                override fun onError(errorMsg: String) {
                    callback.onError(errorMsg)
                }
            })
        }
    }

    fun executeNotesListener(callback: CallbackCommand<String, MessageData?>){
        FirebaseManager().apply {
            listenForNewData(object: FirebaseManager.FirebaseCallback<String, MessageData?>{
                override fun onSuccess(result: List<MessageData?>) {
                    callback.onSuccessList(result)
                }

                override fun onError(errorMsg: String) {
                    callback.onError(errorMsg)
                }
            })
        }
    }

    // ADD GENERIC INTERFACE
    interface CallbackCommand<T, A>{
        fun onSuccess(result: T) { /* Default implementation - Do Nothing */ }
        fun onSuccessList(result: List<A>) { /* Default implementation - Do Nothing */ }
        fun onError(errorMsg: String)
    }
}