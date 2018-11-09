package com.hbjpro.androidvoiceassistant.presenter

import android.app.Fragment
import android.content.Intent
import com.hbjpro.androidvoiceassistant.NewsFeedFragment
import com.hbjpro.androidvoiceassistant.data.NewsData
import com.hbjpro.androidvoiceassistant.model.ModelSpeech
import com.hbjpro.androidvoiceassistant.speech.SpeechResult
import com.hbjpro.androidvoiceassistant.tools.Tools
import com.hbjpro.androidvoiceassistant.model.ModelCommand

class MainViewPresent(val view: MainViewListener){

    var _modelSpeech = ModelSpeech()
    var _modelCommand = ModelCommand()

    fun doSpeechRecognition(languageCode: Tools.LanguageCode){
        _modelSpeech.startSpeech(object: ModelSpeech.ModelSpeechCallback{
            override fun onSuccess(speechResult: SpeechResult) {
                when(speechResult.commandTy){
                    Tools.CommandTy.OPEN_APP ->{

                    }
                    Tools.CommandTy.NEWS_FEED -> {
                        view.onFragmentInit(NewsFeedFragment())
                    }
                    Tools.CommandTy.SEARCH_INTERNET -> {}
                    Tools.CommandTy.NOTES ->{

                    }
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

    interface MainViewListener{
        fun onSpeechResultSuccess(speechText: String)

        fun onFragmentInit(fragment: Any)

        fun onError(errorMsg: String)
    }
}