package com.hbjpro.androidvoiceassistant.presenter

import android.content.Intent
import com.hbjpro.androidvoiceassistant.Data.NewsData
import com.hbjpro.androidvoiceassistant.model.ModelSpeech
import com.hbjpro.androidvoiceassistant.Speech.SpeechResult
import com.hbjpro.androidvoiceassistant.Tools.Tools
import com.hbjpro.androidvoiceassistant.model.ModelCommand

class MainViewPresent(val view: MainViewListener){

    var _modelSpeech = ModelSpeech()
    var _modelCommand = ModelCommand()

    fun doSpeechRecognition(languageCode: Tools.LanguageCode){
        _modelSpeech.startSpeech(object: ModelSpeech.ModelSpeechCallback{
            override fun onSuccess(speechResult: SpeechResult) {
                when(speechResult.commandTy){
                    Tools.CommandTy.OPEN_APP ->{
                        doExecuteOpenApp(speechResult.commandArgument)
                    }
                    Tools.CommandTy.NEWS_FEED -> {
                        doExecuteGetNewsFeed()
                    }
                    Tools.CommandTy.SEARCH_INTERNET -> {}
                    Tools.CommandTy.INVALID -> {
                        view.onError("")
                    }
                }
            }

            override fun onError(errorMsg: String) {
                view.onError(errorMsg)
            }
        }, languageCode)
    }

    fun doExecuteOpenApp(appName: String){
        _modelCommand.executeOpenApp(object: ModelCommand.CallbackOpenApp{
            override fun onSuccess(intent: Intent) {
                view.onOpenAppSuccess(intent)
            }

            override fun onError(errorMsg: String) {
                view.onError("")
            }
        }, appName)
    }

    fun doExecuteGetNewsFeed(){
        _modelCommand.executeGetNewsFeed(object: ModelCommand.CallbackNewsFeed{
            override fun onSuccess(newsData: NewsData) {

            }

            override fun onError(errorMsg: String) {
                view.onError("")
            }
        })
    }

    interface MainViewListener{
        // hacerlo con genericos

        fun onSpeechResultSuccess(speechText: String)

        fun onOpenAppSuccess(intent: Intent)

        fun onGetNewsFeedSuccess(newsData: NewsData)

        fun onError(errorMsg: String)
    }
}