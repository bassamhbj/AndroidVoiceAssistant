package com.hbjpro.androidvoiceassistant.presenter

import android.content.Intent
import com.hbjpro.androidvoiceassistant.data.Article
import com.hbjpro.androidvoiceassistant.data.MessageData
import com.hbjpro.androidvoiceassistant.data.NewsData
import com.hbjpro.androidvoiceassistant.model.ModelCommand

class FragmentPresenter {

    fun doExecuteGetNewsFeed(view: ViewListener<NewsData, Article>){
        ModelCommand().apply {
            executeGetNewsFeed(object: ModelCommand.CallbackCommand<NewsData, Article>{
                override fun onSuccess(result: NewsData) {
                    super.onSuccess(result)
                }

                override fun onSuccessList(result: List<Article>) {
                    super.onSuccessList(result)
                }

                override fun onError(errorMsg: String) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
        }
    }

    fun doExecuteOpenApp(appName: String, view: ViewListener<Intent, Any>){
        ModelCommand().apply {
            executeOpenApp(appName, object: ModelCommand.CallbackCommand<Intent, Any>{
                override fun onSuccess(result: Intent) {
                    view.onSuccess(result)
                }

                override fun onError(errorMsg: String) {
                    view.onError(errorMsg)
                }
            })
        }
    }

    fun doExecuteCreateNewMessage(messageData: MessageData, view: ViewListener<String, MessageData?>){
        ModelCommand().apply {
            executeCreateNewMessage(messageData, object: ModelCommand.CallbackCommand<String, MessageData?>{
                override fun onSuccess(result: String) {
                    view.onSuccess(result)
                }

                override fun onError(errorMsg: String) {
                    view.onError(errorMsg)
                }
            })
        }
    }

    fun doExecuteNotesListener(view: ViewListener<String, MessageData?>){
        ModelCommand().apply {
            executeNotesListener(object: ModelCommand.CallbackCommand<String, MessageData?>{
                override fun onSuccessList(result: List<MessageData?>) {
                    view.onSuccessList(result)
                }

                override fun onError(errorMsg: String) {
                    view.onError(errorMsg)
                }
            })
        }
    }

    interface ViewListener<T, A>{
        fun onSuccess(result: T) { /* Default implementation - Do Nothing */ }
        fun onSuccessList(result: List<A>) { /* Default implementation - Do Nothing */ }
        fun onError(errorMsg: String)
    }
}