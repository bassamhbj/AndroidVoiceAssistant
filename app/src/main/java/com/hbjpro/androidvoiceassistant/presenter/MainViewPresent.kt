package com.hbjpro.androidvoiceassistant.presenter

import com.hbjpro.androidvoiceassistant.fragment.AppFragment
import com.hbjpro.androidvoiceassistant.fragment.MessageFragment
import com.hbjpro.androidvoiceassistant.fragment.NewsFeedFragment
import com.hbjpro.androidvoiceassistant.model.ModelSpeech
import com.hbjpro.androidvoiceassistant.speech.SpeechResult
import com.hbjpro.androidvoiceassistant.common.tools.Tools

class MainViewPresent(val view: MainViewListener){

    var _modelSpeech = ModelSpeech()

    fun doSpeechRecognition(languageCode: Tools.LanguageCode){
        _modelSpeech.startSpeech(object: ModelSpeech.ModelSpeechCallback{
            override fun onSuccess(speechResult: SpeechResult) {
                view.onSpeechResultSuccess(speechResult.message)

                if(speechResult.isKeyWord){
                    when(speechResult.commandTy){
                        Tools.CommandTy.OPEN_APP ->{
                            view.onFragmentInit(AppFragment.newInstance(speechResult.commandArgument))
                        }
                        Tools.CommandTy.NEWS_FEED -> {
                            view.onFragmentInit(NewsFeedFragment())
                        }
                        Tools.CommandTy.SEARCH_INTERNET -> {}
                        Tools.CommandTy.MESSAGE ->{
                            view.onFragmentInit(MessageFragment.newInstance("", false))
                        }
                        Tools.CommandTy.NEW_MESSAGE ->{
                            view.onFragmentInit(MessageFragment.newInstance(speechResult.commandArgument, true))
                        }
                        Tools.CommandTy.INVALID -> {
                            view.onError("Invalid Command")
                        }
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