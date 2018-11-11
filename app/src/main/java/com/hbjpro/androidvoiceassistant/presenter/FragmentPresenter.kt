package com.hbjpro.androidvoiceassistant.presenter

import android.content.Intent
import com.hbjpro.androidvoiceassistant.common.data.Article
import com.hbjpro.androidvoiceassistant.common.data.MessageData
import com.hbjpro.androidvoiceassistant.model.ModelCommand

class FragmentPresenter {

    fun doExecuteGetNewsFeed(view: ViewListener<List<Article>>){
        ModelCommand().apply {
            executeGetNewsFeed(object: ModelCommand.CallbackCommand<List<Article>>{
                override fun onSuccess(result: List<Article>) {
                    view.onSuccess(result)
                }

                override fun onError(errorMsg: String) {
                    view.onError(errorMsg)

                }
            })
        }
    }

    fun doExecuteOpenApp(appName: String, view: ViewListener<Intent>){
        ModelCommand().apply {
            executeOpenApp(appName, object: ModelCommand.CallbackCommand<Intent>{
                override fun onSuccess(result: Intent) {
                    view.onSuccess(result)
                }

                override fun onError(errorMsg: String) {
                    view.onError(errorMsg)
                }
            })
        }
    }

    fun doExecuteCreateNewMessage(messageData: MessageData, view: ViewListener<String>){
        ModelCommand().apply {
            executeCreateNewMessage(messageData, object: ModelCommand.CallbackCommand<String>{
                override fun onSuccess(result: String) {
                    view.onSuccess(result)
                }

                override fun onError(errorMsg: String) {
                    view.onError(errorMsg)
                }
            })
        }
    }

    fun doExecuteMessageListener(view: ViewListener<List<MessageData?>>){
        ModelCommand().apply {
            executeMessageListener(object: ModelCommand.CallbackCommand<List<MessageData?>>{
                override fun onSuccess(result: List<MessageData?>) {
                    view.onSuccess(result)
                }

                override fun onError(errorMsg: String) {
                    view.onError(errorMsg)
                }
            })
        }
    }

    interface ViewListener<T>{
        fun onSuccess(result: T)
        fun onError(errorMsg: String)
    }
}