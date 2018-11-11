package com.hbjpro.androidvoiceassistant.model

import android.content.Intent
import com.hbjpro.androidvoiceassistant.command.FirebaseManager
import com.hbjpro.androidvoiceassistant.command.ModuleApp
import com.hbjpro.androidvoiceassistant.command.ModuleNews
import com.hbjpro.androidvoiceassistant.common.data.Article
import com.hbjpro.androidvoiceassistant.common.data.MessageData
import com.hbjpro.androidvoiceassistant.common.data.NewsData

class ModelCommand {

    fun executeOpenApp(appName: String, callback: CallbackCommand<Intent>){
        ModuleApp().apply {
            getAppLaunchIntent(appName, object: ModuleApp.AppCallback<Intent>{
                override fun onSuccess(result: Intent) {
                    callback.onSuccess(result)
                }

                override fun onError(errorMsg: String) {
                    callback.onError(errorMsg)
                }
            })
        }

    }

    fun executeGetNewsFeed(callback: CallbackCommand<List<Article>>){
        ModuleNews().apply {
            getNewsFeed(object: ModuleNews.NewsModuleCallback{
                override fun onSuccess(newsData: NewsData) {
                    callback.onSuccess(newsData.articles)
                }

                override fun onError(errorMsg: String) {
                    callback.onError(errorMsg)
                }
            })
        }
    }

    fun executeCreateNewMessage(messageData: MessageData, callback: CallbackCommand<String>){
        FirebaseManager().apply {
            createNewMessage(messageData, object: FirebaseManager.FirebaseCallback<String>{
                override fun onSuccess(result: String) {
                    callback.onSuccess(result)
                }

                override fun onError(errorMsg: String) {
                    callback.onError(errorMsg)
                }
            })
        }
    }

    fun executeMessageListener(callback: CallbackCommand<List<MessageData?>>){
        FirebaseManager().apply {
            listenForNewData(object: FirebaseManager.FirebaseCallback<List<MessageData?>>{
                override fun onSuccess(result: List<MessageData?>) {
                    callback.onSuccess(result)
                }

                override fun onError(errorMsg: String) {
                    callback.onError(errorMsg)
                }
            })
        }
    }

    interface CallbackCommand<T>{
        fun onSuccess(result: T)
        fun onError(errorMsg: String)
    }
}